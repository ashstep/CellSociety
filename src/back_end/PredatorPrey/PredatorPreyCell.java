package back_end.PredatorPrey;

import java.util.ArrayList;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

public class PredatorPreyCell extends Cell{
	/*
	 * note regarding myType in Cell class
	 * 1: type 1
	 * 2: type 2
	 * 0: empty
	 */
	private final int FISH=1;
	private final int SHARK=2;
	private final int EMPTY=0;
	private final Color FISH_COLOR=Color.GREEN;
	private final Color SHARK_COLOR=Color.YELLOW;
	private final Color EMPTY_COLOR=Color.BLUE;
	
	private int age;
	
	/**
	 * default constructor
	 * @param type type of the cell
	 */
	public PredatorPreyCell(int type) {
		super(type);
		age=0;
	}
	
	/**
	 * makes a copy of another PredatorPreyCell 
	 * @param anotherCell
	 */
	//refactor to abstract class?
	public PredatorPreyCell(PredatorPreyCell anotherCell) {
		this(anotherCell.getMyType());
	}
	
	
	@Override
	public ActionBySim checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) {
		if(isEmpty()){
			return new ActionByPPSim(false, false, false);
		}
		
		
		return false;
	}
	
	
	
	/**
	 * kills a cell (fish or shark)
	 */
	public void kill(){
		super.setMyType(EMPTY);
	}
	
	
	
	
	
	/**
	 * 
	 * @return true if cell is fish
	 */
	public boolean isFish(){
		return super.getMyType()==FISH;
	}
	
	/**
	 * 
	 * @return true if cell is shark
	 */
	public boolean isShark(){
		return super.getMyType()==SHARK;
	}
	
	/**
	 * 
	 * @return true if cell is empty
	 */
	public boolean isEmpty(){
		return super.getMyType()==EMPTY;
	}
	
	
	@Override
	public Color getColor() {
		if(isFish()){
			return FISH_COLOR;
		} else if(isShark()){
			return SHARK_COLOR;
		} else {
			return EMPTY_COLOR;
		}
	}

}