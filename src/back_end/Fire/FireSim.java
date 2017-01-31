package back_end.Fire;

import java.util.ArrayList;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;

public class FireSim extends Simulation {

	@Override
	public Cell[][] updateGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> allNeighbors = new ArrayList<Cell>();
		Cell neighbor1 = null, neighbor2 = null, neighbor3 = null, neighbor4 = null;

		//NOTE need to check if the "neighbor" is out of bounds of the grid or not
		neighbor1.setCellX(row + 1);
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
	public void setSimInfo(SimulationInfo newInfo) {
		// TODO Auto-generated method stub
		
	}





}
