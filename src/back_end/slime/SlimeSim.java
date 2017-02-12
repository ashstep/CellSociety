package back_end.slime;

import java.util.ArrayList;
import java.util.function.Consumer;

import Grids.Grid;
import Grids.RectangleGrids.RectangleToroidalGrid;
import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import back_end.Fire.FireCell;
import back_end.Fire.FireSimInfo;
import back_end.slime.cells.AgentCell;
import utilities.GridLocation;

public class SlimeSim extends Simulation {
	private SlimeSimInfo myInfo;
	private final Cell TYPE_CELL = new AgentCell();

	
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


	
	
	
	@Override
	public Grid updateGrid() {
		// TODO Auto-generated method stub
		return null;
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
