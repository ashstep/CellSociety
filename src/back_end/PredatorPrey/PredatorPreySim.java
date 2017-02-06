package back_end.PredatorPrey;

import java.util.ArrayList;
import java.util.Random;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import back_end.PredatorPrey.PPCells.EmptyPPCell;
import back_end.PredatorPrey.PPCells.FishCell;
import back_end.PredatorPrey.PPCells.SharkCell;
import back_end.Segregation.SegregationCell;
import utilities.ArrayLocation;
import utilities.Grid;

/**
 * @author Yuxiang He
 *
 */
public class PredatorPreySim extends Simulation {
	private PredatorPreySimInfo myInfo;
	private final int FISH = 1;
	private final int SHARK = 2;
	private final int EMPTY = 0;
	private final int[] ROW_OFFSET = { 1, -1, 0, 0 };
	private final int[] COL_OFFSET = { 0, 0, 1, -1 };
	/**
	 * constructor
	 * 
	 * @param typeGrid
	 * @param sharkBreedTime
	 * @param sharStarveTime
	 * @param fishBreedTime
	 */
	public PredatorPreySim(int[][] typeGrid, int sharkBreedTime, int sharStarveTime, int fishBreedTime) {
		myInfo = new PredatorPreySimInfo(sharkBreedTime, sharStarveTime, fishBreedTime);
		setCellGrid(typeGrid);
	}

	/**
	 * helper method for constructor, sets the cellGrid from typeGrid
	 * 
	 * @param typeGrid
	 */
	private void setCellGrid(int[][] typeGrid) {
		int numRows = typeGrid.length, numCols = typeGrid[0].length;
		PredatorPreyCell[][] cellGrid = new PredatorPreyCell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				createPPCellAt(cellGrid, new ArrayLocation(row, col), typeGrid[row][col]);
			}
		}
		super.setArrayGrid(cellGrid);
	}

	
	
	/**
	 * updates grid
	 */
	@Override
	public Grid updateGrid() {
		PredatorPreyCell[][] oldGridCopy = (PredatorPreyCell[][]) copyArray(super.getArrayGrid());
		updateSharks(oldGridCopy);	
		updateFish(oldGridCopy);
		setArrayGrid(oldGridCopy);
		return new Grid(oldGridCopy);
	}

	
	/**
	 * makes a copy of an old array. Each element is also points to a new copy
	 * 
	 * @param oldArray
	 * @return newArray
	 */
	protected PredatorPreyCell[][] copyArray(Cell[][] oldArray) {
		int numRows = oldArray.length;
		int numCols = oldArray[0].length;
		PredatorPreyCell[][] copiedArray = new PredatorPreyCell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				makeCellCopyAt(copiedArray, new ArrayLocation(row, col), (PredatorPreyCell)oldArray[row][col]);
			}
		}
		return copiedArray;
	}


	/**
	 * update the grid's sharks
	 * @param grid
	 */
	private void updateSharks(PredatorPreyCell[][] grid) {
		int numRows = super.getNumRows(), numCols = super.getNumCols();
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				ArrayLocation currentLocation = new ArrayLocation(row, col);
				if (super.getArrayGrid()[row][col].getMyType() == SHARK) {
					SharkCell newSharkCell = new SharkCell((SharkCell) super.getArrayGrid()[row][col]);
					ActionByPPSim furtherActions = (ActionByPPSim) newSharkCell.checkAndTakeAction(getNeighbors(row, col), myInfo);
					takeActionsForCell(grid, currentLocation, newSharkCell, furtherActions);
				}
			}
		}
	}

	/**
	 * take actions specified by furtherActions
	 * @param grid
	 * @param currentLocation
	 * @param cell the cell that simulation needs to take actions for
	 * @param furtherActions
	 */
	private void takeActionsForCell(PredatorPreyCell[][] grid, ArrayLocation currentLocation, PredatorPreyCell ppCell, ActionByPPSim furtherActions) {
		ArrayLocation newLoc=currentLocation;
		if (furtherActions.toDie()) {
			killCell(grid, currentLocation);
		} else if (furtherActions.toEat() && ppCell.getMyType()==SHARK) {
			ArrayList<ArrayLocation> fishNeighborLocations = getNeighborLocationByType(currentLocation.getRow(), currentLocation.getCol(), FISH);
			if (fishNeighborLocations.size() != 0) {
				int randLoc=new Random().nextInt(fishNeighborLocations.size());
				ArrayLocation fishToEatLocation = fishNeighborLocations.get(randLoc);
				killCell(grid, fishToEatLocation);
				((SharkCell) ppCell).resetTimeSinceDinner();
			}
		}else if (furtherActions.toMove()) {
			newLoc=move(grid, currentLocation, ppCell);
		}
		if (furtherActions.wantsToReproduce()) {
			reproduce(grid, newLoc, ppCell);
		}
	}

	private void updateFish(PredatorPreyCell[][] oldGridCopy) {
		int numRows = super.getNumRows(), numCols = super.getNumCols();
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				ArrayLocation currentLocation = new ArrayLocation(row, col);
				if (super.getArrayGrid()[row][col].getMyType() == FISH) {
					FishCell newFishCell = new FishCell((FishCell) super.getArrayGrid()[row][col]);
					ActionByPPSim furtherActions = (ActionByPPSim) newFishCell.checkAndTakeAction(getNeighbors(row, col), myInfo);
					takeActionsForCell(oldGridCopy, currentLocation, newFishCell, furtherActions);
				}
			}
		}
	}
	
	
	/**
	 * get the neighbors of A CERTAIN TYPE from the original grid. top, down,
	 * left, right
	 */
	private ArrayList<ArrayLocation> getNeighborLocationByType(int row, int col, int neighborType) {
		ArrayList<ArrayLocation> output = new ArrayList<ArrayLocation>();
		for (int i = 0; i < ROW_OFFSET.length; i++) {
			int resultant_row = row + ROW_OFFSET[i], resultant_col = col + COL_OFFSET[i];
			if (super.isValidPosition(resultant_row, resultant_col)
					&& super.getArrayGrid()[resultant_row][resultant_col].getMyType() == neighborType) {
				output.add(new ArrayLocation(resultant_row, resultant_col));
			}
		}
		return output;
	}

	/**
	 * get the neighbors from the original grid. top, down, left, right
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output = new ArrayList<Cell>();
		for (int i = 0; i < ROW_OFFSET.length; i++) {
			int resultant_row = row + ROW_OFFSET[i], resultant_col = col + COL_OFFSET[i];
			if (super.isValidPosition(resultant_row, resultant_col)) {
				output.add(super.getArrayGrid()[resultant_row][resultant_col]);
			}
		}
		return output;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		if(newInfo instanceof PredatorPreySimInfo){
			myInfo=(PredatorPreySimInfo) newInfo;
		} else {
			throw new Error("Information must be PredatorPreySimInfo");
		}
	}
	
	@Override
	public SimulationInfo getSimInfo() {
		return myInfo;
	}

	/**
	 * kills a cell at a location of a Cell[][]
	 * 
	 * @param grid
	 * @param location
	 *            location of the cell to kill
	 */
	private void killCell(PredatorPreyCell[][] grid, ArrayLocation location) {
		createPPCellAt(grid, location, EMPTY);
	}

	/**
	 * puts a PPCell of type cellType at a specified location at
	 * PredatorPreyCell[][] grid
	 * 
	 * @param grid
	 * @param location
	 * @param cellType
	 */
	private void createPPCellAt(PredatorPreyCell[][] grid, ArrayLocation location, int cellType) {
		if (cellType == FISH) {
			grid[location.getRow()][location.getCol()] = new FishCell();
		} else if (cellType == SHARK) {
			grid[location.getRow()][location.getCol()] = new SharkCell();
		} else {
			grid[location.getRow()][location.getCol()] = new EmptyPPCell();
		}
	}

	/**
	 * makes a copy of the cell at a specified location at PredatorPreyCell[][]
	 * grid
	 * 
	 * @param grid
	 * @param location
	 * @param cell
	 */
	private void makeCellCopyAt(PredatorPreyCell[][] grid, ArrayLocation location, PredatorPreyCell cell) {
		if (cell.getMyType() == FISH) {
			grid[location.getRow()][location.getCol()] = new FishCell((FishCell) cell);
		} else if (cell.getMyType() == SHARK) {
			grid[location.getRow()][location.getCol()] = new SharkCell((SharkCell) cell);
		} else {
			grid[location.getRow()][location.getCol()] = new EmptyPPCell();
		}
	}

	/**
	 * given the current position of the cell, move it to a new empty neighbor
	 * position, if one is available
	 * 
	 * @param currentLocation
	 *            current column
	 * @param cell
	 *            the cell
	 * @param newGrid
	 * @return the new location it has moved to
	 */
	private ArrayLocation move(PredatorPreyCell[][] grid, ArrayLocation currentLocation, PredatorPreyCell cell) {
		createPPCellAt(grid, currentLocation, EMPTY);
		ArrayLocation newLoc=copyCellInVincinity(grid, currentLocation, cell);
		return newLoc;
	}

	/**
	 * mimics reproduction, which is essentially createCellInVincinity
	 * 
	 * @param currentLocation
	 *            current column
	 * @param cell
	 *            the cell
	 * @param newGrid
	 */
	private void reproduce(PredatorPreyCell[][] grid, ArrayLocation currentLocation, PredatorPreyCell cell) {
		boolean successful=createCellInVincinity(grid, currentLocation, cell.getMyType());
		if(successful){
			cell.resetTimeSinceBreed();
		}
	}

	/**
	 * given the current position of the cell, create another cell
	 * 
	 * @param currentLocation
	 *            current column
	 * @param cell
	 *            the cell
	 * @param newGrid
	 * @return true if there is empty space to create a cell
	 */
	private boolean createCellInVincinity(PredatorPreyCell[][] grid, ArrayLocation currentLocation, int cellType) {
		int row = currentLocation.getRow(), col = currentLocation.getCol();
		ArrayLocation newPos = findEmptySpots(grid, row, col);
//		System.out.printf("moving type %d from %d, %d to %d, %d\n", cellType, currentLocation.getRow(),
//				currentLocation.getCol(), newPos.getRow(), newPos.getCol());
		createPPCellAt(grid, newPos, cellType);
		return newPos.equals(currentLocation);
	}

	/**
	 * given the current position of the cell, create another cell
	 * 
	 * @param currentLocation
	 *            current column
	 * @param cell
	 *            the cell
	 * @param newGrid
	 * @return the new location of the cell 
	 */
	private ArrayLocation copyCellInVincinity(PredatorPreyCell[][] grid, ArrayLocation currentLocation, PredatorPreyCell cell) {
		int row = currentLocation.getRow(), col = currentLocation.getCol();
		ArrayLocation newPos = findEmptySpots(grid, row, col);
		System.out.printf("moving type %d from %d, %d to %d, %d\n", cell.getMyType(), currentLocation.getRow(),
				currentLocation.getCol(), newPos.getRow(), newPos.getCol());
		makeCellCopyAt(grid, newPos, cell);
		return newPos;
	}

	/**
	 * generates a position where there is an empty type cell around a location.
	 * If not, return the current location
	 * 
	 */
	@Override
	protected ArrayLocation findEmptySpots(Cell[][] grid, int currentRow, int currentCol) {
		ArrayList<ArrayLocation> emptySpaces = getNeighborLocationByType(currentRow, currentCol, EMPTY);
		ArrayLocation location;
		Random rn=new Random();
		if (emptySpaces.size() != 0) {
			location = emptySpaces.get(rn.nextInt(emptySpaces.size()));
		} else {
			location = new ArrayLocation(currentRow, currentCol);
		}
		return location;
	}

}
