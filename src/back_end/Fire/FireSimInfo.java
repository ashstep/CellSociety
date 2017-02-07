package back_end.Fire;

import back_end.SimulationInfo;
/**
 * Class that deals with fire probablity
 * @author Ashka Stephen
 *
 */
public class FireSimInfo extends SimulationInfo{
	private double myProbCatch;
	
	public FireSimInfo(double probCatch) {
		myProbCatch = probCatch;
	}
	
	public double getProbCatch(){
		return myProbCatch;
	}
	
	public double setProbCatch(double newProbCatch){
		this.myProbCatch = newProbCatch;
		return newProbCatch;
	}

}
