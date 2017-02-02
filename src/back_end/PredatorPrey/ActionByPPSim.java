package back_end.PredatorPrey;

import back_end.ActionBySim;
/**
 * object contains direction to Simulation after Cell has made a decision
 * @author Yuxiang He
 *
 */
public class ActionByPPSim extends ActionBySim{
	
	private boolean reproduce;
	private boolean eat;

	public ActionByPPSim(boolean toReproduce, boolean toMove, boolean toEat){
		super(toMove);
		reproduce=toReproduce;
		eat=toEat;
	}
	
	/**
	 * 
	 * @return true if cell is reproducing
	 */
	public boolean toReproduce(){
		return reproduce;
	}
	
	
	/**
	 * 
	 * @return true if cell is to eat another adjacent cell 
	 */
	public boolean toEat(){
		return eat;
	}
}
