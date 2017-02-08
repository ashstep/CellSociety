package back_end.PredatorPrey;

import java.util.ArrayList;
import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

public abstract class PredatorPreyCell extends Cell{
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
	private int timeSinceBreed;
	
	/**
	 * default constructor
	 * @param type type of the cell
	 */
	public PredatorPreyCell(int type) {
		super(type);
		timeSinceBreed=0;
	}
	
//	/**
//	 * makes a copy of another PredatorPreyCell 
//	 * @param anotherCell
//	 */
//	//refactor to abstract class?
//	public PredatorPreyCell(PredatorPreyCell anotherCell) {
//		this(anotherCell.getMyType());
//	}
	
	
	@Override
	public abstract ActionBySim checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) ;
	
	
	
	/**
	 * kills a cell (fish or shark)
	 */
	public void die(){
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
	
	/**
	 * getter for timeSinceBreed
	 * @return
	 */
	protected int getTimeSinceBreed(){
		return timeSinceBreed;
	}
	
	/**
	 * re-setter for timeSinceBreed
	 */
	public void resetTimeSinceBreed(){
		timeSinceBreed=0;
	}
	
	/**
	 * setter for timeSinceBreed
	 */
	protected void incrementTimeSinceBreed(){
		timeSinceBreed++;
	}
	
	/**
	 * calculates color of the cell based on type
	 */
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
	
	public ArrayList<String> getTypeNames()
	{
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("Fish");
		nameList.add("Shark");
		return nameList;
	}
	public String getTypeName()
	{
		if (getMyType() == 1) return "Fish";
		else if (getMyType() == 2) return "Shark";
		else return "";
	}
}