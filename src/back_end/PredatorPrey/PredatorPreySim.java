package back_end.PredatorPrey;

import java.util.ArrayList;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import back_end.PredatorPrey.PPCells.EmptyPPCell;
import back_end.PredatorPrey.PPCells.FishCell;
import back_end.PredatorPrey.PPCells.SharkCell;

public class PredatorPreySim extends Simulation {
	private PredatorPreySimInfo myInfo;
	
	private final int FISH=1;
	private final int SHARK=2;
	private final int EMPTY=0;
	
	/**
	 * constructor
	 * @param typeGrid
	 * @param sharkBreedTime
	 * @param sharStarveTime
	 * @param fishBreedTime
	 */
	public PredatorPreySim(int[][] typeGrid, int sharkBreedTime, int sharStarveTime, int fishBreedTime){
		myInfo=new PredatorPreySimInfo(sharkBreedTime, sharStarveTime, fishBreedTime);
		setCellGrid(typeGrid);
	}
	
	/**
	 * helper method for constructor, sets the cellGrid from typeGrid
	 * @param typeGrid
	 */
	private void setCellGrid(int[][] typeGrid) {
		int numRows=typeGrid.length, numCols=typeGrid[0].length;
		PredatorPreyCell[][] cellGrid=new PredatorPreyCell[numRows][numCols];
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				if(typeGrid[row][col]==FISH){
					cellGrid[row][col]=new FishCell();
				} else if(typeGrid[row][col]==SHARK){
					cellGrid[row][col]=new SharkCell();
				} else {
					cellGrid[row][col]=new EmptyPPCell();
				}
			}
		}
		super.setGrid(cellGrid);
	}
	
	
	
	
	@Override
	public Cell[][] updateGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		// TODO Auto-generated method stub
		return null;
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
