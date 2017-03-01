package back_end.slime;

import back_end.SimulationInfo;

/**
 * Purpose: Sets specifics of the cell --variable inputs.
 * @author Ashka Stephen
 */
public class SlimeSimInfo extends SimulationInfo{
	private double myWiggleProb;
	private int myWiggleAngle;
	private int sniffThreshold;
	private int sniffAngle;
	private int timeElapsed;
	
	/**
	 * Default Constructor
	 * @param wiggleProb - probability that the cell wiggles and does not move straight
	 * @param wiggleAngle - angle at which the cell will most likely move
	 * @param thisSniffThreshold - point at which th cell will be able to detect a chemical from ground grid
	 * @param thisSniffAngle - angle which it can "smell"
	 * This is a beneficial implementation since it allows us to not have to go to each cell and implement the same parameter, but also restricts our flexibility.
	 */
	public SlimeSimInfo(double wiggleProb,int wiggleAngle, int thisSniffThreshold, int thisSniffAngle) {
		myWiggleProb = wiggleProb;
		myWiggleAngle = wiggleAngle;
		sniffThreshold = thisSniffThreshold;
		sniffAngle = thisSniffAngle;}
	
	public double getProbWiggle(){
		return myWiggleProb;
	}
	public double setProbWiggle(double newWiggleProb){
		this.myWiggleProb = newWiggleProb;
		return newWiggleProb;
	}
	public double getWiggleAngle(){
		return myWiggleAngle;
	}
	public double setWiggleAngle(int newWiggleAngle){
		this.myWiggleAngle = newWiggleAngle;
		return newWiggleAngle;
	}
	public double getSniffThreshold(){
		return sniffThreshold;
	}
	public double setSniffThreshold(int newsniffThreshold){
		this.sniffThreshold = newsniffThreshold;
		return sniffThreshold;
	}
	public double getSniffAngle(){
		return sniffAngle;
	}
	public double setSniffAngle(int newsniffAngle){
		this.sniffAngle = newsniffAngle;
		return sniffAngle;
	}
	public int getChemicalDiffusionTime(){
		return timeElapsed;
	}
		
}
