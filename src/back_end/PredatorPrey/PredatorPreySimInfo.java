package back_end.PredatorPrey;

import back_end.SimulationInfo;

public class PredatorPreySimInfo extends SimulationInfo {
	
	private int mySharkBreedTime;
	private int mySharkStarveTime;
	private int myFishBreedTime;
	
	/**
	 * constructor
	 * @param sharkBreedTime
	 * @param sharkStarveTime
	 * @param fishBreedTime
	 */
	public PredatorPreySimInfo(int sharkBreedTime, int sharkStarveTime, int fishBreedTime){
		mySharkBreedTime=sharkBreedTime;
		mySharkStarveTime=sharkStarveTime;
		myFishBreedTime=fishBreedTime;
	}
	
	/**
	 * getter for sharkBreedTime
	 * @return sharkBreedTime
	 */
	public int getSharkBreedTime(){
		return mySharkBreedTime;
	}
	
	/**
	 * setter for sharkBreedTime
	 * @param newBreedTime
	 */
	public void setSharkBreedTime(int newBreedTime){
		mySharkBreedTime=newBreedTime;
	}
	
	/**
	 * getter for sharkStarveTime
	 * @return sharkStarveTime
	 */
	public int getSharkStarveTime(){
		return mySharkStarveTime;
	}
	
	/**
	 * setter for sharkStarveTime
	 * @param newStarveTime
	 */
	public void setSharkStarveTime(int newStarveTime){
		mySharkStarveTime=newStarveTime;
	}
	
	/**
	 * getter for fishBreedTime
	 * @return fishBreedTime
	 */
	public int getFishBreedTime(){
		return myFishBreedTime;
	}
	
	/**
	 * setter for fishBreedTime
	 * @param newBreedTime
	 */
	public void setFishBreedTime(int newBreedTime){
		myFishBreedTime=newBreedTime;
	}
}
