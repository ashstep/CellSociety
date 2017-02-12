package back_end.slime;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

public abstract class SlimeCell extends Cell {
 /*
  * 
  */
	
	private final int TYPE_ALIVE = 1;
	private final int TYPE_EMPTY = 0;
	private final int TYPE_CHEMICAL = 2;
	private final Color TYPE_CHEMICAL_COLOR = Color.YELLOW;
	private final Color TYPE_ALIVE_COLOR = Color.GREEN;
	private final Color TYPE_EMPTY_COLOR = Color.TRANSPARENT;
	//each agent cell will have following properties
	private int wiggleAngle;
	private int probWiggle;
	private int sniffThreshold;
	private int sniffAngle;
	
	//for the ground grid with camp
	private double campPercentage;
	private int timeElapsed;		//when to disregard it


	
	/**
	 * default constructor
	 */
	public SlimeCell(int myType) {
		super(myType);
		timeElapsed = 0;
		campPercentage = 0;
		wiggleAngle= 0;
		probWiggle= 0;
		sniffThreshold= 0;
		sniffAngle= 0;
	}
	
	//create a copy
	//is this needed?????
	public SlimeCell(SlimeCell anotherCell){
		this(anotherCell.getMyType());
	}


	//different for updated grid and ground
	@Override
	public abstract ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) ;

	
	/**
	 * removes cell
	 */
	public void remove(){
		super.setMyType(TYPE_EMPTY);
	}
	
	/**
	 * @return true if cell is empty
	 */
	private boolean isEmpty(){
		return getMyType() == TYPE_EMPTY;
	}
	
	/**
	 * @return true if cell is a chemical cell
	 */
	private boolean isChemical(){
		return getMyType() == TYPE_CHEMICAL;
	}
	
	/**
	 * getter for the time elapsed since chemical released
	 */
	public int getChemicalDiffusionTime(){
		return timeElapsed;
	}
	
	
	/**
	 * resetting the time elapsed since chemical released
	 */
	public void resetChemicalDiffusionTime(){
		timeElapsed = 0;
	}
	
	/**
	 * increment
	 */
	protected void incrementTime(){
		timeElapsed++;
	}
	
	
	/**
	 * getter for the chemical percent
	 */
	public double getChemicalPercentage(){
		return campPercentage;
	}
	
	
	/**
	 * set the chemical percent
	 */
	public void setChemicalPercentage(double given){
		campPercentage = given;
	}

	
	
	
	
	

	campPercentage = 0;
	wiggleAngle= 0;
	probWiggle= 0;
	sniffThreshold= 0;
	sniffAngle= 0;

	
	
	
	
	
	
	
	
	
	
	
	

	
	@Override
	public Color getColor() {
		if(isAlive()){
			return TYPE_ALIVE_COLOR;
		} 
		if(isChemical()){
			return TYPE_CHEM_COLOR;
		}
		else {
			return TYPE_EMPTY_COLOR;
		}
	}

	@Override
	public Collection<String> getTypeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeNextStateCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeEmptyCell() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
