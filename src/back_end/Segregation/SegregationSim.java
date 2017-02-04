package back_end.Segregation;

import java.util.ArrayList;
import java.util.Random;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.Grid;

public class SegregationSim extends Simulation{
	
	private final int[] ROW_OFFSET={-1, -1, -1,  0, 0,   1, 1, 1};
	private final int[] COL_OFFSET ={-1,   0,  1, -1, 1, -1, 0, 1};
	private final int TYPE_EMPTY=0;
	private SegregationSimInfo myInfo;
	private final int RANDOM_ITER_LIMIT=10000;
	
	/**
	 * 
	 * @param typeGrid int[][] that specifies the type of SegregationCell at the corresponding position in myGrid
	 * @param threshold satisfaction threshold for each cell, environment attribute
	 */
	//repeated code for setting up Grid?
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
		super.setArrayGrid(cellGrid);
	}
	
	/**
	 * makes a copy of an old array. Each element is also points to a new copy
	 * @param oldArray
	 * @return newArray
	 */
	private SegregationCell[][] copyArray(Cell[][] oldArray){
		int numRows = oldArray.length;
		int numCols = oldArray[0].length;
		SegregationCell[][] copiedArray=new SegregationCell[numRows][numCols];
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				copiedArray[row][col]=new SegregationCell((SegregationCell)oldArray[row][col]);
			}
		}
		return copiedArray;
	}
	
	/**
	 * updates the grid and returns the new Cell[][] grid
	 * be careful about the casting
	 */
	@Override
	public Grid updateGrid() {
		int numRows = super.getNumRows(),  numCols = super.getNumCols();
		SegregationCell[][] oldGridCopy=copyArray( super.getArrayGrid());
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				if((super.getArrayGrid()[row][col]).getMyType()==TYPE_EMPTY) {
					continue;
				}
				SegregationCell cell=new SegregationCell(oldGridCopy[row][col]);
				boolean isMoving=cell.checkAndTakeAction(getNeighbors(row, col), myInfo).toMove();
				if(isMoving){
					moveToNewSpot(oldGridCopy, row, col, cell);
				} else if( !isMoving && oldGridCopy[row][col].getMyType()==TYPE_EMPTY){
					relocateToNewSpot(oldGridCopy, row, col, cell);
				} else {
					oldGridCopy[row][col]=cell;
				}
			}
		}
		super.setArrayGrid(oldGridCopy);
		return new Grid(oldGridCopy);
	}

	
	
	
	private void relocateToNewSpot(SegregationCell[][] newGrid, int row, int col, SegregationCell cell) {
		int[] newPos=move(newGrid, row, col);
		System.out.printf("relocating type %d from %d, %d to %d, %d\n", cell.getMyType(), row, col, newPos[0], newPos[1]);
		newGrid[newPos[0]][newPos[1]]=new SegregationCell(cell);
	}


	/**
	 * given the current position of the cell, move it to a new position in newGrid
	 * @param newGrid
	 * @param row current row
	 * @param col current column
	 * @param cell the cell
	 */
	private void moveToNewSpot(SegregationCell[][] newGrid, int row, int col, SegregationCell cell) {
		int[] newPos=move(newGrid, row, col);
		System.out.printf("moving type %d from %d, %d to %d, %d\n", cell.getMyType(), row, col, newPos[0], newPos[1]);
		newGrid[row][col]=new SegregationCell(TYPE_EMPTY);
		newGrid[newPos[0]][newPos[1]]=new SegregationCell(cell.getMyType());
	}


	/**
	 * generates a position where newGrid contains null, or an empty type cell
	 */
	protected int[] move(Cell[][] newGrid, int currentRow, int currentCol) {
		int row=0, col=0, iter=0;
		Random rn=new Random();
		while( !(newGrid[row][col].getMyType()==TYPE_EMPTY && row!=currentRow && col!=currentCol) && iter<RANDOM_ITER_LIMIT){
			iter++;
			System.out.println("finding space...");
			row=rn.nextInt(newGrid.length);
			col=rn.nextInt(newGrid[0].length);
		}
		if(!(newGrid[row][col].getMyType()==TYPE_EMPTY && row!=currentRow && col!=currentCol) ){
			int nR=0, nC=0;
			for(int r=0; r<newGrid.length; r++){
				for(int c=0; c<newGrid[0].length; c++){
					if(newGrid[r][c].getMyType()==TYPE_EMPTY && r!=currentRow && c!=currentCol){
						System.out.printf("r: %d, c: %d\n", r,c );
						nR=r;
						nC=c;
						break;
					}
				}
			}
			return new int[]{nR, nC};
		} else {
			System.out.printf("row: %d, col: %d\n", row,col );
			return new int[]{row, col};
		}		
	}
	
	/**
	 * get the neighbors from the original grid
	 * top, down, left, right
	 * top-left, top-right
	 * bottom-left, bottom-right
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output=new ArrayList<Cell>();	
		for(int i=0; i<ROW_OFFSET.length; i++){
			int resultant_row=row+ROW_OFFSET[i], resultant_col=col+COL_OFFSET[i];
			if(super.isValidPosition(resultant_row, resultant_col)){
				output.add(super.getArrayGrid()[resultant_row][resultant_col]);
			}
		}
		return output;
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
	 * updates the threshold for the cells to be satisfied.
	 * @param newThreshold new value for threshold
	 */
	public void setThreshold(int newThreshold) {
		myInfo.setThreshold(newThreshold);
	}


}