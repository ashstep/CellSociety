package back_end.slime;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.PredatorPrey.PPCells.EmptyPPCell;
import back_end.PredatorPrey.PPCells.FishCell;
import back_end.PredatorPrey.PPCells.SharkCell;
import back_end.slime.cells.AgentCell;
import back_end.slime.cells.ChemCell;
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
	
	
	private boolean isAlive(){
		return getMyType() == TYPE_ALIVE;
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
	public double getChemicalPercent(){
		return campPercentage;
	}
	
	/**
	 * set the chemical percent
	 */
	public void setChemicalPercent(double given){
		campPercentage = given;
	}

	/**
	 * getter for the chemical percent
	 */
	public int getWiggleAngle(){
		return wiggleAngle;
	}
	
	/**
	 * set the chemical percent
	 */
	public void setWiggleAngle(int given){
		wiggleAngle = given;
	}

	/**
	 * getter 
	 */
	public int getWiggleProb(){
		return probWiggle;
	}
	
	/**
	 * setter
	 */
	public void setWiggleProb(int given){
		probWiggle = given;
	}

	/**
	 * getter
	 */
	public int getSniffThresh(){
		return sniffThreshold;
	}
	
	/**
	 * setter
	 */
	public void setSniffThresh(int given){
		sniffThreshold = given;
	}

	/**
	 * getter
	 */
	public int getSniffAngle(){
		return sniffAngle;
	}

	/**
	 * setter
	 */
	public void setSniffAngle(int given){
		sniffAngle = given;
	}

	
	@Override
	public Color getColor() {
		if(isAlive()){
			return TYPE_ALIVE_COLOR;
		} 
		if(isChemical()){
			return TYPE_CHEMICAL_COLOR;
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
		if(type==TYPE_ALIVE){
			return new AgentCell();
		} else if(type==TYPE_CHEMICAL){
			return new ChemCell();
		} else {
			throw new IllegalArgumentException("Invalid Cell Type");
		}	}

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

	
}
