package back_end.slime;

import java.util.ArrayList;
import java.util.function.Consumer;

import Grids.Grid;
import Grids.RectangleGrids.RectangleFiniteGrid;
import Grids.RectangleGrids.RectangleToroidalGrid;
import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import back_end.Fire.FireCell;
import back_end.Fire.FireSimInfo;
import back_end.PredatorPrey.PredatorPreyCell;
import back_end.PredatorPrey.PredatorPreySimInfo;
import back_end.PredatorPrey.PPCells.EmptyPPCell;
import back_end.PredatorPrey.PPCells.FishCell;
import back_end.PredatorPrey.PPCells.SharkCell;
import back_end.slime.cells.AgentCell;
import back_end.slime.cells.ChemCell;
import utilities.GridLocation;

public class SlimeSim extends Simulation {
	private SlimeSimInfo myInfo;
	private final Cell TYPE_CELL = new AgentCell();
	private final int TYPE_AGENT = 1;
	private final int TYPE_EMPTY = 0;
	private final int TYPE_CHEM = 2;
//	private final int NEIGHBOR_FLAG=1;
	
	

	/**
	 * constructor
	 * 
	 * @param typeGrid
	 * @param sharkBreedTime
	 * @param sharStarveTime
	 * @param fishBreedTime
	 */
	public SlimeSim(int[][] typegrid, int[][] groundGrid,  double wiggleProb,int wiggleAngle, int thisSniffThreshold, int thisSniffAngle) {
		myInfo = new SlimeSimInfo(wiggleProb, wiggleAngle, thisSniffThreshold, thisSniffAngle);
		setCellGrid(typegrid);
		//setGroundGrid(groundGrid);
		
		
		int numRows = typegrid.length ;
		int numCols = typegrid[0].length ;
		SlimeCell[][] cellGrid = new SlimeCell[numRows][numCols];
		for(int row = 0; row < numRows ; row++){
			for(int col = 0 ; col < numCols; col++){
				cellGrid[row][col] = new SlimeCell(typegrid[row][col]);
			}
		}
		super.setGrid(new RectangleToroidalGrid(cellGrid, TYPE_CELL));

		
		
	}

	/**
	 * helper method for constructor, sets the cellGrid from typeGrid
	 * 
	 * @param typeGrid
	 */
	private void setCellGrid(int[][] typeGrid) {
		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		SlimeCell[][] cellGrid = new SlimeCell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				createCell(cellGrid, new GridLocation(row, col), typeGrid[row][col]);
			}
		}
		super.setGrid(new RectangleFiniteGrid(cellGrid, TYPE_CELL));
	}

	
	
	/**
	 * updates grid
	 */
	@Override
	public Grid updateGrid() {
		Grid oldGridCopy = new RectangleFiniteGrid((SlimeCell[][]) copyArray(super.getGrid().getContainer()), TYPE_CELL);
		super.setGrid(oldGridCopy);
		return oldGridCopy;
	}

	
	/**
	 * puts a PPCell of type cellType at a specified location at
	 * PredatorPreyCell[][] grid
	 * 
	 * @param grid
	 * @param location
	 * @param cellType
	 */
	
	private void createCell(Cell[][] grid, GridLocation location, int cellType) {
		if (cellType == TYPE_AGENT) {
			grid[location.getRow()][location.getCol()] = new AgentCell();
		} else if (cellType == TYPE_CHEM) {
			grid[location.getRow()][location.getCol()] = new ChemCell();
		} else {
			grid[location.getRow()][location.getCol()] = new AgentCell(0);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor
	 * @param probablilty of the tree catching on fire and a int[][] that holds the location 
	 * of each fire cell
	 */
	public SlimeSim(int[][] typeGrid, double probCatch){
		myInfo = new SlimeSimInfo(probCatch);

		int numRows = typeGrid.length ;
		int numCols = typeGrid[0].length;

		AgentCell[][] cellGrid = new AgentCell[numRows][numCols];
		for(int row = 0; row < numRows ; row++){
			for(int col = 0 ; col < numCols; col++){
				cellGrid[row][col] = new AgentCell(typeGrid[row][col]);
			}
		}
		super.setGrid(new RectangleToroidalGrid(cellGrid, TYPE_CELL));
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
		super.setGrid(new RectangleFiniteGrid(cellGrid, TYPE_CELL));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	


	@Override
	protected GridLocation findEmptySpots(Grid grid, int currentRow, int currentCol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ArrayList<String> getParameterList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consumer<Number> getChangeMethod(String x) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/*
	 * probWiggle is -1 to 1 (0 means it moves straight)
	 * wiggleAngle is 0 -> 360 (angle it faces)
	 */
	@Override
	public double getSliderLowerBound(String x) {
		if (x.equals("probWiggle")) return -1;
		if (x.equals("wiggleAngle")) return 0;

		return 0;
	}

	@Override
	public double getSliderUpperBound(String x) {
		if (x.equals("probWiggle")) return 1;
		if (x.equals("wiggleAngle")) return 360;
		return 0;

	}

	@Override
	public double getCurrentValue(String x) {
		if (x.equals("probWiggle")) return myInfo.getProbWiggle();
		if (x.equals("wiggleAngle")) return myInfo.getWiggleAngle();

		return 0;

	}
	
	@Override
	public SimulationInfo getSimInfo() {
		return myInfo;
	}
}
