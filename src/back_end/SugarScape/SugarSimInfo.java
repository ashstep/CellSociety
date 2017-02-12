/**
 * 
 */
package back_end.SugarScape;

import back_end.SimulationInfo;
import utilities.GridLocation;

/**
 * @author Yuxiang He
 *
 */
public class SugarSimInfo extends SimulationInfo {
	private int maxSugarCapacity;
	private int sugarGrowBackRate;
	private int sugarGrowBackInterval;
	
	private int agentFertileLimits;
	private int agentVisionRange;
	private GridLocation myLocation;
	
	/**
	 * 
	 */
	public SugarSimInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public GridLocation getMyLocation(){
		return myLocation;
	}

}
