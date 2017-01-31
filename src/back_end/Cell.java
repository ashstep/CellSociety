package back_end;

<<<<<<< HEAD
import javafx.scene.paint.Color;

/**
 * Abstract super class that describes the properties shared by cells of all simulations
 * @author Yuxiang He
 *
 */
public abstract class Cell {
	/**
	 * reprsents the type of the cell in the simulation, starts from 0
	 */
	private int myType;
	
	private Color myColor;
	
	/**
	 * checks the information about 
	 * @param neighbors
	 */
	public abstract void checkAndTakeAction(Cell[] neighbors, SimulationInfo simInfo);

	/**
	 * getter
	 * @return
	 */
	public int getMyType() {
		return myType;
=======
import javafx.scene.paint.Paint;

public abstract class Cell
{

	public Paint getColor() {
		// TODO Auto-generated method stub
		return null;
>>>>>>> 3a1daddffc05036da590e9bf6c37dd73f81399de
	}

	/**
	 * setter
	 * @param type
	 */
	public void setMyType(int newType) {
		myType=newType;
	}
	
	
	public abstract Color getColor();
	
	
}
