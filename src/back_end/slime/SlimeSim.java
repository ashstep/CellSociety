package back_end.slime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import Grids.Grid;
import Grids.RectangleGrids.RectangleFiniteGrid;
import Grids.RectangleGrids.RectangleToroidalGrid;
import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import back_end.slime.cells.AgentCell;
import back_end.slime.cells.ChemCell;
import utilities.GridLocation;
/**
 * The slime simulation extends the format of previous simulations. However, there are two grids that are implemented.
 * One is the grid of interacting slime cells while the second is the grid of chemicals. The "top" grid will use information from the bottom grid to take action
 * This format was chosen to maintain the extendability of the code. Additional simulations could use this format of doubling grids to hold different information.
 * @author Ashka Stephen
 *
 */

public class SlimeSim extends Simulation {
	private SlimeSimInfo myInfo;
	private final int TYPE_AGENT = 1;
	private final int TYPE_EMPTY = 0;
	private final int TYPE_CHEM = 2;
	private final int NEIGHBOR_FLAG=1;
	
	/**
	 * Constructor
	 * @param typeGrid - grid on which the agent slime cells will interact with each other 
	 * @param groundGrid - grid on which the chemical CAMP cells will interact with each other (and later affect the above grid of slime)
	 * @param wiggleProb - probability of the slime wiggling in a given direction
	 * @param wiggleAngle - the given direction 0-360
	 * @param thisSniffThreshold - how much chemical is needed to be detected 0-100 scale
	 * @param thisSniffAngle - angle in which the agent is looking (cannot register otherwise) -> on top grid
	 * @param shapeType - visual representation of this class
	 * @param boundsType - finite / infinite
	 * @return object that takes in information and will be used to implement the changing display
	 */
	public SlimeSim(int[][] typegrid, int[][] groundGrid,  double wiggleProb,int wiggleAngle, int thisSniffThreshold, int thisSniffAngle,
			String boundsType, String shapeType)
	{
		myInfo = new SlimeSimInfo(wiggleProb, wiggleAngle, thisSniffThreshold, thisSniffAngle);
		setCellGrid(typegrid, groundGrid, boundsType, shapeType);
	}

	/**
	 * Helper method for constructor, sets the cellGrid from typeGrid, which is the top grid that the user will see and interact with 
	 * @param typeGrid - grid on which the agent slime cells will interact with each other 
	 * @return visual representation of the cell grid
	 */
	private void setCellGrid(int[][] typeGrid, int[][] groundTypeGrid, String boundsType, String shapeType) {
		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		SlimeCell[][] cellGrid = new SlimeCell[numRows][numCols];
		SlimeCell[][] groundGrid = new SlimeCell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				createCell(cellGrid, new GridLocation(row, col), typeGrid[row][col]);
				createCell(groundGrid, new GridLocation(row, col), groundTypeGrid[row][col]);
			}
		}
		makeGrid(boundsType, shapeType, cellGrid);
		super.setGroundGrid(new RectangleFiniteGrid(groundGrid));
	}

	/**
	 * Function: Updates cell grid that is seen by the user. 
	 */
	@Override
	public Grid updateGrid() {
		int numRows=super.getNumRows(), numCols=super.getNumCols();
		Cell[][] cellarr = super.getCellGrid().getContainer();
		Cell[][] second = super.deepCopyCellArray(cellarr);
		Grid copyCell = createCellGrid(second);
		Grid oldCellGrid = super.getCellGrid();
		for(int row=0; row < numRows; row++){
			for(int col=0; col < numCols; col++){		
				GridLocation location=new GridLocation(row, col);
				oldCellGrid.setCellAt(location, new AgentCell((AgentCell)copyCell.getCellAt(location)));
				oldCellGrid.getCellAt(location).checkAndTakeAction(copyCell.getNeighbors(location, NEIGHBOR_FLAG), myInfo);
			}
		}
		updateGroundGrid();
		super.setCellGrid(oldCellGrid);
		return oldCellGrid;
	}


	/**
	 * Function: Updates ground grid not seen by the user.
	 * Shortcoming: A shortcoming is that this uses some duplicated code from the function above.
	 */
	public Grid updateGroundGrid() {
		int numRows=super.getNumRows(), numCols=super.getNumCols();
		Grid copyGround = createCellGrid(this.deepCopyCellArray(super.getGroundGrid().getContainer()));
		Grid oldGroundGrid = super.getGroundGrid();

		for(int row=0; row < numRows; row++){
			for(int col=0; col < numCols; col++){		
				GridLocation location = new GridLocation(row, col);
				oldGroundGrid.setCellAt(location, new ChemCell((ChemCell)copyGround.getCellAt(location)));
				oldGroundGrid.getCellAt(location).checkAndTakeAction(copyGround.getNeighbors(location, NEIGHBOR_FLAG), myInfo);
			}
		}
		super.setGroundGrid(oldGroundGrid);
		return oldGroundGrid;
	}

	
	/**
	 * creates a cell of type specified
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
	 * FunctionL Creates a cell of type specified
	 * @param cellType - type of cell given by integer
	 * @param grid
	 * @param location of the grid
	 */
	@Override
	protected GridLocation findEmptySpot(Grid grid, int currentRow, int currentCol) {
		return null;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
	}

	
	/**
	 * The following are implementation of visuals as discussed in abstracted class:
	 */
	@Override
	public Collection<String> getParameterList() {
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add("probWiggle");
		parameterList.add("wiggleAngle");
		parameterList.add("sniffThreshold");
		parameterList.add("sniffAngle");
		return parameterList;
	}

	@Override
	public Consumer<Number> getChangeMethod(String x) {
		Consumer<Number> r = (Number n) -> {};
		if (x.equals("probWiggle")) r = (Number n) -> {myInfo.setProbWiggle(n.intValue());};
		else if (x.equals("wiggleAngle")) r = (Number n) -> {myInfo.setWiggleAngle(n.intValue());};
		else if (x.equals("sniffThreshold")) r = (Number n) -> {myInfo.setSniffThreshold(n.intValue());};
		else if (x.equals("sniffAngle")) r = (Number n) -> {myInfo.setSniffAngle(n.intValue());};
		return r;
	}
	
	@Override
	public double getSliderLowerBound(String x) {
		if (x.equals("probWiggle")) return -1;
		if (x.equals("wiggleAngle")) return 0;
		if (x.equals("sniffThreshold")) return 0;
		if (x.equals("sniffAngle")) return 0;
		return 0;
	}

	@Override
	public double getSliderUpperBound(String x) {
		if (x.equals("probWiggle")) return 1;
		if (x.equals("wiggleAngle")) return 360;
		if (x.equals("sniffThreshold")) return 100;
		if (x.equals("sniffAngle")) return 360;
		return 0;

	}

	@Override
	public double getCurrentValue(String x) {
		if (x.equals("probWiggle")) return myInfo.getProbWiggle();
		if (x.equals("wiggleAngle")) return myInfo.getWiggleAngle();
		if (x.equals("sniffThreshold")) return myInfo.getSniffThreshold();
		if (x.equals("sniffAngle")) return myInfo.getSniffAngle();
		return 0;
	}
	
	@Override
	public SimulationInfo getSimInfo() {
		return myInfo;
	}
}
