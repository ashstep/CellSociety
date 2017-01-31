package back_end.Fire;

import java.util.ArrayList;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
/**
 * Class that implements the unique properties of the fire simulation
 * @author Ashka Stephen
 *
 */

public class FireSim extends Simulation {
	private static Cell stateEmpty;
	private static Cell stateBurning;
	private static Cell stateTree;
	
	private double probCatch;



	
	
	@Override
	public Cell[][] upDateGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	
	//check what is up and use similar idea to shorten code
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {

			ArrayList<Cell> allNeighbors = new ArrayList<Cell>();
			Cell neighbor1 = null, neighbor2 = null, neighbor3 = null, neighbor4 = null;			
			neighbor1.
			neighbor1.setCellX(originalX + 1);
			neighbor1.setCellY(originalY);
			allNeighbors.add(neighbor1);

			neighbor2.setCellX(originalX - 1);
			neighbor2.setCellY(originalY);
			allNeighbors.add(neighbor2);

			neighbor3.setCellX(originalX);
			neighbor3.setCellY(originalY + 1);
			allNeighbors.add(neighbor3);

			neighbor4.setCellX(originalX);
			neighbor4.setCellY(originalY - 1);
			allNeighbors.add(neighbor4);

			return allNeighbors;
		

	}

	@Override
	protected int[] move(Cell[][] newGrid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell[][] updateGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		// TODO Auto-generated method stub
		
	}

	
}
