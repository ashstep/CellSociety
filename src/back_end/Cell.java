package back_end;

/**
 * Abstract super class that describes the properties and actions (APIs) shared by cells of all simulations
 * @author Yuxiang He
 */
import java.util.Collection;
import javafx.scene.paint.Color;

public abstract class Cell {
	
	/**
	 * represents the type of the cell in the simulation. 
	 * The number representing each of type of cell is up to the implementation of sub-classes
	 */
	private int myType;

	/**
	 * Constructor. sets the type of the cell
	 * @param type
	 */
	public Cell(int type) {
		myType=type;
	}

	/**
	 * checks the information about 
	 * @param true if the cell wants to move
	 */
	public abstract ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo);
	
	
	/**
	 * Just for testing and debugging
	 * Easier to see the type of the cell in debug mode (instead of seeing a chain of hex numbers)
	 */
	@Override
	public String toString(){
		return ""+getMyType();
	}
	
	
	/**
	 * getter
	 * @return myType
	 */
	public int getMyType() {
		return myType;
	}
	
	/**
	 * setter
	 * @param type
	 */
	public void setMyType(int newType) {
		myType=newType;
	}
	
	/**
	 * @return an empty/dead GameOfLifeCell
	 */
	public abstract Cell makeEmptyCell();
	public abstract Cell makeCellofType(int type) throws IllegalArgumentException;
	public abstract Cell makeNextStateCell();
	public abstract Color getColor();
	public abstract Collection<String> getTypeNames();
	public abstract String getTypeName();
}