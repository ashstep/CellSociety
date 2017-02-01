package back_end.Segregation;

import java.util.ArrayList;
import java.util.Random;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;

public class SegregationSim extends Simulation{
	
	private final int[] ROW_OFFSET={-1, -1, -1,  0, 0,   1, 1, 1};
	private final int[] COL_OFFSET ={-1,   0,  1, -1, 1, -1, 0, 1};
	private final int TYPE_ONE=1;
	private final int TYPE_TWO=2;
	private final int TYPE_EMPTY=0;
	private SegregationSimInfo myInfo;
	
	
	public SegregationSim(int[][] typeGrid, int threshold){
		myInfo=new SegregationSimInfo(threshold);
		
		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		SegregationCell[][] cellGrid=new SegregationCell[numRows][numCols];
		
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				cellGrid[row][col]=new SegregationCell(typeGrid[row][col]);
			}
		}
		setGrid(cellGrid);
	}
	
	
	/**
	 * updates the grid and returns the new Cell[][] grid
	 */
	@Override
	public Cell[][] updateGrid() {
		Cell[][] newGrid=new Cell[getGrid().length][getGrid()[0].length];
		for(int row=0; row<getGrid().length; row++){
			for(int col=0; col<getGrid()[0].length; col++){
				Cell cell=getGrid()[row][col];
				boolean isMoving=cell.checkAndTakeAction(getNeighbors(row, col), myInfo);
				if(isMoving){
					moveToNewSpot(newGrid, row, col, cell);
				} else {
					newGrid[row][col]=cell;
				}
			}
		}
		setGrid(newGrid);
		return newGrid;
	}

	/**
	 * given the current position of the cell, move it to a new position in newGrid
	 * @param newGrid
	 * @param row current row
	 * @param col current column
	 * @param cell the cell
	 */
	private void moveToNewSpot(Cell[][] newGrid, int row, int col, Cell cell) {
		int[] newPos=move(newGrid);
		newGrid[row][col]=new SegregationCell(TYPE_EMPTY);
		newGrid[newPos[0]][newPos[1]]=cell;
	}

	
	/**
	 * get the neighbors
	 * top, down, left, right
	 * top-left, top-right
	 * bottom-left, bottom-right
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output=new ArrayList<Cell>();	
		for(int i=0; i<ROW_OFFSET.length; i++){
			int resultant_row=row+ROW_OFFSET[i], resultant_col=col+COL_OFFSET[i];
			if(isValidPosition(resultant_row, resultant_col)){
				output.add(getGrid()[resultant_row][resultant_col]);
			}
		}
		return output;
	}

	/**
	 * generates a position where newGrid contains null, or an empty type cell
	 */
	@Override
	protected int[] move(Cell[][] newGrid) {
		int row=0, col=0;
		Random rn=new Random();
		while( !  (newGrid[row][col]==null || newGrid[row][col].getMyType()==TYPE_EMPTY)   ){
			row=rn.nextInt(newGrid.length);
			col=rn.nextInt(newGrid[0].length);
		}
		return new int[]{row, col};
	}
	
	/**
	 * updates the threshold for the cells to be satisfied
	 * @param newInfo the new SegregationSimInfo object to be set to myInfo
	 */
	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		myInfo=(SegregationSimInfo) newInfo;
	}

	
	/**
	 * updates the threshold for the cells to be satisfied. Overloads setSimInfo(SimulationInfo newInfo)
	 * @param newThreshold new value for threshold
	 */
	public void setSimInfo(int newThreshold) {
		myInfo.setThreshold(newThreshold);
	}

}
