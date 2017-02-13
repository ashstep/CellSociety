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
	public SugarSimInfo(int cmaxSugarCapcity, int csugarGrowBackRate, int csugarGrowBackInterval, int cagentFertileLimits, int cagentVisionRange) {
		maxSugarCapacity=cmaxSugarCapcity;
		sugarGrowBackRate=csugarGrowBackRate;
		sugarGrowBackInterval=csugarGrowBackInterval;
		agentFertileLimits=cagentFertileLimits;
		agentVisionRange=cagentVisionRange;
	}
	

	/**
	 * 
	 * @return
	 */
	public int getMaxSugarCapacity() {
		return maxSugarCapacity;
	}

	public void setMaxSugarCapacity(int maxSugarCapacity) {
		this.maxSugarCapacity = maxSugarCapacity;
	}

	/**
	 * 
	 * @return
	 */
	public int getSugarGrowBackRate() {
		return sugarGrowBackRate;
	}

	public void setSugarGrowBackRate(int sugarGrowBackRate) {
		this.sugarGrowBackRate = sugarGrowBackRate;
	}

	/**
	 * 
	 * @return
	 */
	public int getSugarGrowBackInterval() {
		return sugarGrowBackInterval;
	}

	public void setSugarGrowBackInterval(int sugarGrowBackInterval) {
		this.sugarGrowBackInterval = sugarGrowBackInterval;
	}

	/**
	 * 
	 * @return
	 */
	public int getAgentFertileLimits() {
		return agentFertileLimits;
	}

	public void setAgentFertileLimits(int agentFertileLimits) {
		this.agentFertileLimits = agentFertileLimits;
	}

	/**
	 * 
	 * @return
	 */
	public int getAgentVisionRange() {
		return agentVisionRange;
	}

	public void setAgentVisionRange(int agentVisionRange) {
		this.agentVisionRange = agentVisionRange;
	}

	/**
	 * 
	 * @return
	 */
	public GridLocation getMyLocation(){
		return myLocation;
	}
	
	public void setMyLocation(GridLocation myLocation) {
		this.myLocation = myLocation;
	}

}
