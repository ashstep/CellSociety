package back_end.slime.cells;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
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
	
	
	
	public ChemCell() {
		super(2);
	}
	/**
	 * makes a copy
	 */
	public ChemCell(AgentCell another){
		this();
	}


	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		return null;
	}



}
