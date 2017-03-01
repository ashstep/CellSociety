package back_end.SugarScape;

import back_end.ActionBySim;
import utilities.GridLocation;

/**
 *  @author Yuxiang He
 */

public class ActionBySugarSim extends ActionBySim {

	private GridLocation myDestination;
	
	public ActionBySugarSim(boolean toMove, GridLocation destination) {
		super(toMove);
		myDestination=destination;
	}
	
	/**
	 * @return grid location that specifies the destination of the sugar agent cell
	 */
	public GridLocation getDestination(){
		return myDestination;
	}

}
