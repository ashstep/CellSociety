package back_end.Segregation;

import back_end.SimulationInfo;

public class SegregationSimInfo extends SimulationInfo {
	private int myThreshold; //percentage i.e. myThreshold=56, threshold=56%
	
	public SegregationSimInfo(int threshold){
		myThreshold=threshold;
	}
	
	/**
	 * getter for myThreshold
	 * @return myThreshold
	 */
	public int getThreshold(){
		return myThreshold;
	}
	
	/**
	 * setter for newThreshold
	 * @param newThreshold
	 */
	public void setThreshold(int newThreshold){
		myThreshold=newThreshold;
	}
}
