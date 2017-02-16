package back_end.Fire;

import back_end.SimulationInfo;

/**
* Purpose: Class that implements the unique properties THAT ALL CELLS SHARE in the fire simulation, specifically the probability of a cell catching on fire.
* Assumptions: All cells in the simulation have the same probability of catching on fire. If the simulation were extended in such a way
* that various groups of trees have different probabilities of catching fire, this would need to be extended.
* Dependencies: Dependent on input from XML file since that holds the probability.
* Example of use: Each cell's state is determined and that leads to how it reacts to its neighbors. The most important method is checkAndTakeAction().
* @author Ashka Stephen
*/
public class FireSimInfo extends SimulationInfo{
	private double myProbCatch;
	
	/**
	 * Default Constructor
	 * @param the probability that the cell catches on fire --an inherent weakness in this setup is that ALL cells will have the same probability.
	 * This is a beneficial implementation since it allows us to not have to go to each cell and implement the same parameter, but also restricts our flexibility.
	 */
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