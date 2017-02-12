/**
 * 
 */
package back_end.SugarScape;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;
import utilities.GridLocation;

/**
 * @author Yuxiang He
 *
 */
public class SugarGroundCell extends Cell{
	private int maxSugarCapacity;
	private int sugarLevel;
	private GridLocation myPosition;
	
	
	/**
	 * @param type
	 */
	public SugarGroundCell() {
		super(0);
	}

	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * getter for sugarLevel
	 * @return sugarLevel
	 */
	public int getSugarLevel(){
		return sugarLevel;
	}
	
	
	/**
	 * setters for myPosition
	 * @param location
	 */
	public void setMyPosition(GridLocation location){
		myPosition=location;
	}
	public void setMyPosition(int row, int col){
		myPosition=new GridLocation(row, col);
	}

	/**
	 * getters for myPosition
	 */
	public GridLocation getMyPosition(){
		return myPosition;
	}
	
	@Override
	public Cell makeEmptyCell() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * 
	 */
	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeNextStateCell() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Collection<String> getTypeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return null;
	}
}
