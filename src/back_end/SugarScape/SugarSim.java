/**
 * 
 */
package back_end.SugarScape;

import java.util.ArrayList;
import java.util.function.Consumer;

import Grids.Grid;
import Grids.RectangleGrids.RectangleFiniteGrid;
import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import back_end.PredatorPrey.PPCells.EmptyPPCell;
import back_end.PredatorPrey.PPCells.FishCell;
import back_end.PredatorPrey.PPCells.SharkCell;
import back_end.Segregation.SegregationCell;
import utilities.GridLocation;

/**
 * @author YuxiangHe
 *
 */
public class SugarSim extends Simulation {

	
	private SugarSimInfo myInfo;
	private final int AGENT = 1;
	private final int GROUND = 2;
	/**
	 * 
	 */
	public SugarSim(int[][] typeGrid, AgentCellInfo[][] agentCellInfo, GroundCellInfo[][] groundCellInfo, SugarSimInfo info) {
		myInfo=info;
		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		SugarCell[][] cellGrid = new SugarCell[numRows][numCols];

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				cellGrid[row][col] = new SugarCell(typeGrid[row][col]);
			}
		}
		super.setGrid(new RectangleFiniteGrid(cellGrid));
	}

	
	/* (non-Javadoc)
	 * @see back_end.Simulation#updateGrid()
	 */
	@Override
	public Grid updateGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#findEmptySpots(Grids.Grid, int, int)
	 */
	@Override
	protected GridLocation findEmptySpots(Grid grid, int currentRow, int currentCol) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#setSimInfo(back_end.SimulationInfo)
	 */
	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getSimInfo()
	 */
	@Override
	public SimulationInfo getSimInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getParameterList()
	 */
	@Override
	public ArrayList<String> getParameterList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getChangeMethod(java.lang.String)
	 */
	@Override
	public Consumer<Number> getChangeMethod(String x) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getSliderLowerBound(java.lang.String)
	 */
	@Override
	public double getSliderLowerBound(String x) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getSliderUpperBound(java.lang.String)
	 */
	@Override
	public double getSliderUpperBound(String x) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getCurrentValue(java.lang.String)
	 */
	@Override
	public double getCurrentValue(String x) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void createSugarCellAt(SugarAgentCell[][] agentGrid, SugarGroundCell[][] groundGrid, 
			AgentCellInfo[][] agentCellInfo, GroundCellInfo[][] groundCellInfo, GridLocation location, int cellType) {
		
		int row=location.getRow(), col=location.getCol();
		if (cellType == AGENT) {
			agentGrid[location.getRow()][location.getCol()] = new SugarAgentCell(agentCellInfo[row][col]);
		} else if (cellType == GROUND) {
			groundGrid[location.getRow()][location.getCol()] = new SugarGroundCell(location, groundCellInfo[row][col]);
		} else {
			throw new IllegalArgumentException("SugarCell type is unknown");
		}
	}

}
