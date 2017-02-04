package utilities;
/**
 * represents the position of a call in its Cell[][] grid
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
}
