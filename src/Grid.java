
public class Grid {

	private String[][] myGrid;

	public int getRow(){
		return myGrid.length;
	}

	public int getColumn(){
		return myGrid[0].length;
	}

	public String getItem(int x, int y){
		return myGrid[x][y];
	}
	
	
	//error checking


}
