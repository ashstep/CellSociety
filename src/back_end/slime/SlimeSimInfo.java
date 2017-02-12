package back_end.slime;

import back_end.SimulationInfo;


/**
 * Sets specifics of the cell 
 * 	-wiggle probability        -> 0 is straight, negative is left, positive is right
 * @author Ashka Stephen
 *
 */
public class SlimeSimInfo extends SimulationInfo{
	private double myWiggleProb;
	private int myWiggleAngle;
	//ground grid
	private int sniffThreshold;
	private int sniffAngle;

	
	public SlimeSimInfo(double wiggleProb) {
		myWiggleProb = wiggleProb;
	}
	
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
	
	/////////////
	
	
	
	
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
	
	
	
	
}
