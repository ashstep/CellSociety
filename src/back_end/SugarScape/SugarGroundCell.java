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
public class SugarGroundCell extends SugarCell{

	/**
	 * @param type
	 */
	public SugarGroundCell(GridLocation location, int sugar) {
		super(0);
		
	}

	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
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
	 * 
	 * @return true if the ground is vacant
	 */
	public boolean isVacant(){
		return isVacant;
	}
	
	public boolean isOccupied(){
		return !isVacant;
	}
	
	/**
	 * set the ground to be vacant
	 */
	public void setVacant(){
		 isVacant=true;
	}
	
	/**
	 * set the ground to be vacant
	 */
	public void setOccupied(){
		 isVacant=false;
	}
	
	/**
	 * setters for myPosition
	 * @param location
	 */
	public void setMyPosition(GridLocation location){
		myLocation=location;
	}
	public void setMyPosition(int row, int col){
		myLocation=new GridLocation(row, col);
	}

	/**
	 * getters for myPosition
	 */
	public GridLocation getMyPosition(){
		return myLocation;
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
