package back_end.slime;

import java.util.ArrayList;
import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.slime.cells.AgentCell;
import back_end.slime.cells.ChemCell;
import javafx.scene.paint.Color;
/**
 * Slime Cell to be extended by two types of cells in simulation on different grids
 * @author Ashka Stephen
 */

public abstract class SlimeCell extends Cell {	
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
	//for the ground grid with chemical (camp)
	private double campPercentage;
	private int timeElapsed;

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

	/**
	 * create a copy
	 */
	public SlimeCell(Cell anotherCell){
		this(anotherCell.getMyType());
	}

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
	protected boolean isEmpty(){
		return getMyType() == TYPE_EMPTY;
	}

	/**
	 * @return true if cell is a chemical cell
	 */
	private boolean isChemical(){
		return getMyType() == TYPE_CHEMICAL;
	}

	/**
	 * @return true if cell is alive
	 */
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
	 * increment time since chemical released
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
	 * getter for the wiggle angle 
	 */
	public int getWiggleAngle(){
		return wiggleAngle;
	}

	/**
	 * set the wiggle angle
	 */
	public void setWiggleAngle(int given){
		wiggleAngle = given;
	}

	/**
	 * getter for wiggle probability/percentage
	 */
	public int getWiggleProb(){
		return probWiggle;
	}

	/**
	 * setter for wiggle probability/percentage
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
	public Collection<String> getTypeNames(){
		Collection<String> nameList = new ArrayList<String>();
		nameList.add("Agent");
		nameList.add("Chem");
		return nameList;}

	@Override
	public String getTypeName() {
		if (getMyType() == TYPE_ALIVE) return "Agent";
		else if (getMyType() == TYPE_CHEMICAL) return "Chem"; 
		else return "";
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
		try{
			return makeCellofType(getMyType()+1);
		} catch (IllegalArgumentException e){
			return makeCellofType(TYPE_EMPTY);
		}	
	}
}
