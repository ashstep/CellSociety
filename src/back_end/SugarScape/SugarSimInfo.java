/**
 * 
 */
package back_end.SugarScape;

import back_end.SimulationInfo;
import utilities.GridLocation;

/**
 * @author Yuxiang He
 */
public class SugarSimInfo extends SimulationInfo {
	private int maxSugarCapacity;
	private int sugarGrowBackRate;
	private int sugarGrowBackInterval;
	private int agentFertileLimits;
	private int agentVisionRange;
	private GridLocation myLocation;
	
	/**
	 * constructor
	 */
	public SugarSimInfo(int cmaxSugarCapcity, int csugarGrowBackRate, int csugarGrowBackInterval, int cagentFertileLimits, int cagentVisionRange) {
		maxSugarCapacity=cmaxSugarCapcity;
		sugarGrowBackRate=csugarGrowBackRate;
		sugarGrowBackInterval=csugarGrowBackInterval;
		agentFertileLimits=cagentFertileLimits;
		agentVisionRange=cagentVisionRange;
	}
	
	/**
	 * @return maxSugarCapacity
	 */
	public int getMaxSugarCapacity() {
		return maxSugarCapacity;
	}

	public void setMaxSugarCapacity(int maxSugarCapacity) {
		this.maxSugarCapacity = maxSugarCapacity;
	}

	/**
	 * @return sugarGrowBackRate
	 */
	public int getSugarGrowBackRate() {
		return sugarGrowBackRate;
	}

	public void setSugarGrowBackRate(int sugarGrowBackRate) {
		this.sugarGrowBackRate = sugarGrowBackRate;
	}

	/**
	 * @return sugarGrowBackInterval
	 */
	public int getSugarGrowBackInterval() {
		return sugarGrowBackInterval;
	}

	public void setSugarGrowBackInterval(int sugarGrowBackInterval) {
		this.sugarGrowBackInterval = sugarGrowBackInterval;
	}

	/**
	 * @return agentFertileLimits
	 */
	public int getAgentFertileLimits() {
		return agentFertileLimits;
	}

	public void setAgentFertileLimits(int agentFertileLimits) {
		this.agentFertileLimits = agentFertileLimits;
	}

	/**
	 * @return agentVisionRange
	 */
	public int getAgentVisionRange() {
		return agentVisionRange;
	}

	public void setAgentVisionRange(int agentVisionRange) {
		this.agentVisionRange = agentVisionRange;
	}

	/**
	 * @return grid location
	 */
	public GridLocation getMyLocation(){
		return myLocation;
	}
	
	/**
	 * setter for grid location
	 */
	public void setMyLocation(GridLocation myLocation) {
		this.myLocation = myLocation;
	}

}
