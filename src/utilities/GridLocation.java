package utilities;
/**
 * represents the position of a call in its Cell[][] grid. 
 * IMMUTABLE once created
 * @author Yuxiang He
 *
 */
public class GridLocation {
	private int row;
	private int column;
	
	public GridLocation(int r, int c){
		row=r;
		column=c;
	}
	
	/**
	 * 
	 * @return row stored in the GridLocation
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * 
	 * @return column stored in the GridLocation
	 */
	public int getCol(){
		return column;
	}
	
	/**
	 * checks if two ArrayLocaiton specify the same location
	 * @param otherLocation
	 * @return
	 */
	public boolean equals(GridLocation otherLocation){
		return this.getRow()==otherLocation.getRow() && this.getCol()==otherLocation.getCol();
	}
}
