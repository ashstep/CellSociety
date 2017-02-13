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
 * Basic ground extendable implementation.
 * @author Yuxiang He
 *
 */
public class SugarGroundCell extends SugarCell{

	private GroundCellInfo myInfo;
	
	/**
	 * @param type
	 */
	public SugarGroundCell(GridLocation location, GroundCellInfo info) {
		super(0);
		myInfo=info;
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
		return myInfo.getSugarLevel();
	}
	
	/**
	 * @return true if the ground is vacant
	 */
	public boolean isVacant(){
		return myInfo.isVacant();
	}
	
	public boolean isOccupied(){
		return !myInfo.isVacant();
	}
	
	/**
	 * set the ground to be vacant
	 */
	public void setVacant(){
		 myInfo.setVacant(true);
	}
	
	/**
	 * set the ground to be vacant
	 */
	public void setOccupied(){
		myInfo.setVacant(false);
	}
	
	/**
	 * setters for myPosition
	 * @param location
	 */
	public void setMyPosition(GridLocation location){
		myInfo.setMyLocation(location);
	}
	public void setMyPosition(int row, int col){
		GridLocation location=new GridLocation(row, col);
		myInfo.setMyLocation(location);
	}

	/**
	 * getters for myPosition
	 */
	public GridLocation getMyPosition(){
		return myInfo.getMyLocation();
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
