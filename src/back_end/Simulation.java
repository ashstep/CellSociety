package back_end;

public abstract class Simulation{
	
	private Cell[][] myGrid;
	private SimulationInfo myInfo;
	
	/**
	 * update the grid based on the cells' current state
	 * @return the updated myGrid
	 */
	public abstract Cell[][] updateGrid();
	
	
	/**
	 * 
	 * @param row the row of the current cell
	 * @param col the col of the current cell
	 * @return the Cell neighbors of the current cell
	 */
	protected abstract Cell[] getNeighbors(int row, int col);
	
	/**
	 * generates a [row, column] pair such that newGrid[row][column] is empty for putting a new cell
	 * @return int[]. 0 position is row,1 position is column
	 */
	protected abstract int[] move(Cell[][] newGrid);
	
	/**
	 * getter method
	 * @return myGrid
	 */
	public Cell[][] getGrid(){
		return myGrid;
	}
	
	
	/**
	 * setter method for myGrid
	 */
	public void setGrid(Cell[][] newGrid){
		myGrid=newGrid;
	}
	
	/**
	 * getter method
	 * @return myInfo
	 */
	public SimulationInfo getSimInfo(){
		return myInfo;
	}
	
	public abstract void setSimInfo();
}
