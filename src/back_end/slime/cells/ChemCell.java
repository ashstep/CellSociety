package back_end.slime.cells;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.PredatorPrey.PPCells.SharkCell;
import back_end.slime.SlimeCell;
import javafx.scene.paint.Color;

public class ChemCell extends SlimeCell{
	private double campPercentage;
	private int timeElapsed;		
	private final Color TYPE_CHEM_COLOR = Color.YELLOW;
	private final Color TYPE_EMPTY_COLOR = Color.TRANSPARENT;
	private final int TYPE_CHEM = 2;
	private final int TYPE_EMPTY = 0;

	
	/*
	 * all will start off as present -> the pecentage of chemicas they have will differ
	 * once time elapsed reachez threshold then that cell is emptied
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
		return null;
	}


	public void resetTimeElapsed(){
		timeElapsed=0;
	}
	
	public void resetcampPercentage(){
		campPercentage=0;
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

	
	

}
