package back_end.PredatorPrey;

import java.util.ArrayList;
import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.PredatorPrey.PPCells.EmptyPPCell;
import back_end.PredatorPrey.PPCells.FishCell;
import back_end.PredatorPrey.PPCells.SharkCell;
import javafx.scene.paint.Color;

public abstract class PredatorPreyCell extends Cell{
	/*
	 * note regarding myType in Cell class
	 * 1: fish
	 * 2: shark
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
	
	public PredatorPreyCell(int type, int timeSinceBreeding) {
		super(type);
		timeSinceBreed=timeSinceBreeding;
	}
	
	@Override
	public abstract ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) ;
	
	/**
	 * kills a cell (fish or shark)
	 */
	public void die(){
		super.setMyType(EMPTY);
	}
	
	/**
	 * @return true if cell is fish
	 */
	public boolean isFish(){
		return super.getMyType()==FISH;
	}
	
	/**
	 * @return true if cell is shark
	 */
	public boolean isShark(){
		return super.getMyType()==SHARK;
	}
	
	/**
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
	protected void resetTimeSinceBreed(){
		System.out.println("breed t reset");
		timeSinceBreed=0;
	}
	
	/**
	 * setter for timeSinceBreed
	 */
	protected void incrementTimeSinceBreed(){
		timeSinceBreed+=1;
	}
	
	@Override
	public Cell makeEmptyCell() {
		return new EmptyPPCell();
	}
	
	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException{
		if(type==FISH){
			return new FishCell();
		} else if(type==SHARK){
			return new SharkCell();
		} else if(type==EMPTY){
			return new EmptyPPCell();
		} else {
			throw new IllegalArgumentException("Invalid PPCell type");
		}
	}
	
	@Override
	public Cell makeNextStateCell() {
		try{
			return makeCellofType(getMyType()+1);
		} catch (IllegalArgumentException e){
			return makeCellofType(EMPTY);
		}
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
	
	public Collection<String> getTypeNames()
	{
		Collection<String> nameList = new ArrayList<String>();
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