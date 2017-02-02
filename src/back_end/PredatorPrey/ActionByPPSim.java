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
	private boolean die;
	private int creatorType;
	private final int SHARK=2;

	/**
	 * 
	 * @param toReproduce
	 * @param toMove
	 * @param toEat
	 * @param toDie
	 * @param creatorType type of PredatorPreyCell that created this object
	 */
	public ActionByPPSim(boolean toReproduce, boolean toMove, boolean toEat, boolean toDie, int creatorType){
		super(toMove);
		reproduce=toReproduce;
		eat=toEat;
		die=toDie;
	}
	
	/**
	 * @return true if this cell has died
	 */
	public boolean toDie(){
		return die;
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
	 * @return true if cell is to eat another adjacent cell AND the cell is a shark
	 */
	public boolean toEat(){
		return eat && creatorType==SHARK;
	}
}
