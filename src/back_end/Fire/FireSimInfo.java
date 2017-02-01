package back_end.Fire;

import back_end.SimulationInfo;
/**
 * Class that deals with fire probablity
 * @author Ashka Stephen
 *
 */
public class FireSimInfo extends SimulationInfo{
	private double probCatch;
	
	public FireSimInfo(double probCatch) {
		probCatch = probCatch;
	}
	
	public double getSimInfo(){
		return probCatch;
	}
	
	public double setSimInfo(double newProbCatch){
		this.probCatch = newProbCatch;
		return newProbCatch;
	}

}
