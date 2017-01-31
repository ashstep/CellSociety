package back_end.gameOfLifePack;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;

public class GameOfLifeSim extends Simulation{
	
	private final int[] ROW_OFFSET={-1, -1, -1, 0, 0, 1, 1, 1};
	private final int[] COL_OFFSET={-1, 0, 1, -1, 1, -1, 0, 1};
	

	public Cell[][] updateGrid()
	{
		for(int row=0; row<getGrid().length; row++)
		{
			for(int col=0; col<getGrid()[0].length; col++)
			{
				getGrid()[row][col].checkAndTakeAction(getNeighbors(row, col), getSimInfo());
			}
		}
		return null;
	}

	@Override
	protected Cell[] getNeighbors(int row, int col)
	{
		Cell[]output = new Cell[ROW_OFFSET.length];
		
		for(int i=0; i<ROW_OFFSET.length; i++)
		{
				output[i]=getGrid()[row+ROW_OFFSET[i]][col+COL_OFFSET[i]];
		}
		return output;
	}
	
	

	@Override
	protected int[] move(Cell[][] newGrid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo)
	{
		
	}
	
}
