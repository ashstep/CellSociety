package back_end.PredatorPrey;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import Grids.Grid;
import Grids.RectangleGrids.*;
import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import back_end.PredatorPrey.PPCells.EmptyPPCell;
import back_end.PredatorPrey.PPCells.FishCell;
import back_end.PredatorPrey.PPCells.SharkCell;
import utilities.GridLocation;

/**
 * @author Yuxiang He
 *
 */
public class PredatorPreySim extends Simulation {
	private PredatorPreySimInfo myInfo;
	private final int FISH = 1;
	private final int SHARK = 2;
	private final int EMPTY = 0;
	private final int NEIGHBOR_FLAG=1;
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
				createPPCellAt(cellGrid, new GridLocation(row, col), typeGrid[row][col]);
			}
		}
		super.setGrid(new RectangleFiniteGrid(cellGrid));
	}

	
	
	/**
	 * updates grid
	 */
	@Override
	public Grid updateGrid() {
		Grid oldGridCopy=new RectangleFiniteGrid((PredatorPreyCell[][]) copyArray(super.getGrid().getContainer()));
		updateSharks(oldGridCopy);	
		updateFish(oldGridCopy);
		super.setGrid(oldGridCopy);
		return oldGridCopy;
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
				makeCellCopyAt(copiedArray, new GridLocation(row, col), (PredatorPreyCell)oldArray[row][col]);
			}
		}
		return copiedArray;
	}


	/**
	 * update the grid's sharks
	 * @param grid
	 */
	private void updateSharks(Grid grid) {
		int numRows = super.getNumRows(), numCols = super.getNumCols();
		Grid oldGrid=super.getGrid();
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				GridLocation currentLocation = new GridLocation(row, col);
				if (oldGrid.getCellAt(currentLocation).getMyType() == SHARK) {
					SharkCell newSharkCell = new SharkCell((SharkCell) oldGrid.getCellAt(currentLocation));
					ActionByPPSim furtherActions = (ActionByPPSim) newSharkCell.checkAndTakeAction(oldGrid.getNeighbors(currentLocation, NEIGHBOR_FLAG), myInfo);
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
	private void takeActionsForCell(Grid grid, GridLocation currentLocation, PredatorPreyCell ppCell, ActionByPPSim furtherActions) {
		GridLocation newLoc=currentLocation;
		if (furtherActions.toDie()) {
			killCell(grid, currentLocation);
		} else if (furtherActions.toEat() && ppCell.getMyType()==SHARK) {
			ArrayList<GridLocation> fishNeighborLocations = (ArrayList<GridLocation>) grid.getNeighborLocationByType(currentLocation, FISH, NEIGHBOR_FLAG);
			if (fishNeighborLocations.size() != 0) {
				int randLoc=new Random().nextInt(fishNeighborLocations.size());
				GridLocation fishToEatLocation = fishNeighborLocations.get(randLoc);
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

	private void updateFish(Grid oldGridCopy) {
		int numRows = oldGridCopy.getNumRows(), numCols = oldGridCopy.getNumCols();
		Grid oldGrid=super.getGrid();
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				GridLocation currentLocation = new GridLocation(row, col);
				if (oldGridCopy.getCellAt(currentLocation).getMyType()  == FISH) {
					FishCell newFishCell = new FishCell((FishCell) oldGridCopy.getCellAt(currentLocation));
					ActionByPPSim furtherActions = (ActionByPPSim) newFishCell.checkAndTakeAction(oldGrid.getNeighbors(currentLocation, NEIGHBOR_FLAG), myInfo);
					takeActionsForCell(oldGridCopy, currentLocation, newFishCell, furtherActions);
				}
			}
		}
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
	private void killCell(Grid grid, GridLocation location) {
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
	private void createPPCellAt(Grid grid, GridLocation location, int cellType) {
		if (cellType == FISH) {
			grid.setCellAt(location, new FishCell());
		} else if (cellType == SHARK) {
			grid.setCellAt(location, new SharkCell());
		} else {
			grid.setCellAt(location, new EmptyPPCell());
		}
	}
	
	private void createPPCellAt(Cell[][] grid, GridLocation location, int cellType) {
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
	private void makeCellCopyAt(PredatorPreyCell[][] grid, GridLocation location, PredatorPreyCell cell) {
		if (cell.getMyType() == FISH) {
			grid[location.getRow()][location.getCol()] = new FishCell((FishCell) cell);
		} else if (cell.getMyType() == SHARK) {
			grid[location.getRow()][location.getCol()] = new SharkCell((SharkCell) cell);
		} else {
			grid[location.getRow()][location.getCol()] = new EmptyPPCell();
		}
	}
	
	private void makeCellCopyAt(Grid grid, GridLocation location, PredatorPreyCell cell) {
		if (cell.getMyType() == FISH) {
			grid.setCellAt(location, new FishCell((FishCell) cell));
		} else if (cell.getMyType() == SHARK) {
			grid.setCellAt(location, new SharkCell((SharkCell) cell));
		} else {
			grid.setCellAt(location, new EmptyPPCell());
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
	private GridLocation move(Grid grid, GridLocation currentLocation, PredatorPreyCell cell) {
		createPPCellAt(grid, currentLocation, EMPTY);
		GridLocation newLoc=copyCellInVincinity(grid, currentLocation, cell);
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
	private void reproduce(Grid grid, GridLocation currentLocation, PredatorPreyCell cell) {
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
	private boolean createCellInVincinity(Grid grid, GridLocation currentLocation, int cellType) {
		int row = currentLocation.getRow(), col = currentLocation.getCol();
		GridLocation newPos = findEmptySpots(grid, row, col);
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
	private GridLocation copyCellInVincinity(Grid grid, GridLocation currentLocation, PredatorPreyCell cell) {
		int row = currentLocation.getRow(), col = currentLocation.getCol();
		GridLocation newPos = findEmptySpots(grid, row, col);
		makeCellCopyAt(grid, newPos, cell);
		return newPos;
	}

	/**
	 * generates a position where there is an empty type cell around a location.
	 * If not, return the current location
	 * 
	 */
	@Override
	protected GridLocation findEmptySpots(Grid grid, int currentRow, int currentCol) {
		ArrayList<GridLocation> emptySpaces = (ArrayList<GridLocation>) grid.getNeighborLocationByType(new GridLocation(currentRow, currentCol), EMPTY, NEIGHBOR_FLAG);
		GridLocation location;
		Random rn=new Random();
		if (emptySpaces.size() != 0) {
			location = emptySpaces.get(rn.nextInt(emptySpaces.size()));
		} else {
			location = new GridLocation(currentRow, currentCol);
		}
		return location;
	}

	@Override
	public ArrayList<String> getParameterList()
	{
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add("SharkBreedTime");
		parameterList.add("FishBreedTime");
		parameterList.add("SharkStarveTime");
		return parameterList;
	}

	@Override
	public Consumer<Number> getChangeMethod(String x)
	{
		Consumer<Number> r = (Number n) -> {};
		if (x.equals("SharkBreedTime")) r = (Number n) -> {myInfo.setSharkBreedTime(n.intValue());};
		else if (x.equals("FishBreedTime")) r = (Number n) -> {myInfo.setFishBreedTime(n.intValue());};
		else if (x.equals("SharkStarveTime")) r = (Number n) -> {myInfo.setSharkStarveTime(n.intValue());};
		return r;
	}

	@Override
	public double getSliderLowerBound(String x)
	{
		return 1.0;
	}

	@Override
	public double getSliderUpperBound(String x)
	{
		return 100.0;
	}

	@Override
	public double getCurrentValue(String x)
	{
		int r = 0;
		if (x.equals("SharkBreedTime")) r = myInfo.getSharkBreedTime();
		else if (x.equals("FishBreedTime")) r = myInfo.getFishBreedTime();
		else if (x.equals("SharkStarveTime")) r = myInfo.getSharkStarveTime();
		return r;
	}

}
