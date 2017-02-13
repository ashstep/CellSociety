package back_end.slime;

import back_end.SimulationInfo;

/**
 * Sets specifics of the cell --variable inputs
 * @author Ashka Stephen
 *
 */
public class SlimeSimInfo extends SimulationInfo{
	private double myWiggleProb;
	private int myWiggleAngle;
	private int sniffThreshold;
	private int sniffAngle;
	private int timeElapsed;
	
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
