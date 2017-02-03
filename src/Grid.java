import back_end.Cell;
import javafx.scene.paint.Color;

public class Grid {

	private Cell[][] myGrid;

	public int getRow(){
		return myGrid.length;
	}

	public int getColumn(){
		return myGrid[0].length;
	}

	public Color getColor(int x, int y){
		return myGrid[x][y].getColor();
	}

}
