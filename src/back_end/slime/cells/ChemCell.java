package back_end.slime.cells;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.slime.SlimeCell;
import back_end.slime.SlimeSimInfo;
import javafx.scene.paint.Color;

public class ChemCell extends SlimeCell{
	private double campPercentage;
	private int timeElapsed;		
	private final Color TYPE_CHEM_COLOR = Color.YELLOW;
	private final Color TYPE_EMPTY_COLOR = Color.TRANSPARENT;
	private final int TYPE_CHEM = 2;
	private final int TYPE_EMPTY = 0;

	
	/*
	 * all will start off as present -> the percentage of chemicals they have will differ
	 * once time elapsed reaches threshold then that cell is emptied
	 */
	
	/**
	 * default constructor
	 */

	public ChemCell() {
		super(2);
		timeElapsed = 0;
		campPercentage = 0;
	}

	/**
	 * makes a copy
	 */
	public ChemCell(ChemCell another){
		super(2);
		this.timeElapsed= another.timeElapsed;
		this.campPercentage= another.campPercentage;
	}

	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		super.incrementTime();
		int diffusionTime = ((SlimeSimInfo) simInfo).getChemicalDiffusionTime(); //getting time elapsed
		int emptyNeighbors=0;
		int totalNeigbors=0; 
		for(Cell neighbor: neighbors){
			totalNeigbors++;
			if(neighbor.getMyType() == TYPE_EMPTY){
				emptyNeighbors++;
			}
		}
		//remove hard coded value of 3
		boolean allIsDissolved = diffusionTime > 3;
		if(allIsDissolved){
			resetTimeElapsed();
		}
		if(allIsDissolved && ((totalNeigbors-emptyNeighbors)/totalNeigbors > .5)){
			return new ActionBySim(allIsDissolved);
		}
		return new ActionBySim(!allIsDissolved);
	}


	public void resetTimeElapsed(){
		timeElapsed = 0;
	}
	
	public void resetcampPercentage(){
		campPercentage = 0;
	}
	
	/**
	 * Given the chemical threshold, determine if the shark is starved to death
	 * @param starveThreshold
	 * @return true if the shark has died
	 */
	private boolean isDetectingChem(int chemThreshold){
		return campPercentage>chemThreshold;
	}
	
	public void incrementTime(){
		timeElapsed++;
	}

	@Override
	public Cell makeEmptyCell() {
		// TODO Auto-generated method stub
		return null;
	}
}
