package utilities;
import back_end.Cell;
import javafx.scene.paint.Color;
/**
 * 
 * @author Ashka Stephen
 */
public class Grid {

	private Cell[][] myGrid;

	public Grid(Cell[][] cellGrid){
		myGrid=cellGrid;
	}
	
	/**
	 * 
	 * @return number of rows in grid
	 */
	public int getNumRows(){
		return myGrid.length;
	}

	/**
	 * 
	 * @return number of columns in grid
	 */
	public int getNumCols(){
		return myGrid[0].length;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Color getColorAt(int x, int y){
		if(x>=0 && x<getNumRows()
				&& y>=0 && y<getNumCols()){
			return myGrid[x][y].getColor();
		} else {
			return Color.WHITE;
		}
		
	}

	public Number getNumEmptyCells()
	{
		int counter = 0;
		for (int x = 0; x < getNumRows(); x++)
		{
			for (int y = 0; y < getNumCols(); y++)
			{
				if (myGrid[x][y].getMyType() == 0) counter++;
			}
		}
		return counter;
	}
}
