package back_end.slime.cells;

import java.util.Collection;

import Grids.RectangleGrids.RectangleFiniteGrid;
import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.slime.SlimeCell;
import back_end.slime.SlimeSimInfo;
import javafx.scene.paint.Color;
import utilities.GridLocation;

public class AgentCell extends SlimeCell {

	/**
	 *  This cell moves in direction of higher chemical concentration. 
	 *  The Agent Cell is the slime cell on the "top" grid that is displayed on the front end.
	 *  @author Ashka Stephen
	 */
	private final int TYPE_AGENT = 1;
	private final int TYPE_EMPTY = 0;
	private final Color TYPE_AGENT_COLOR = Color.GREEN;
	private final Color TYPE_EMPTY_COLOR = Color.TRANSPARENT;
	private double myWiggleProb;
	private int myWiggleAngle;
	private int sniffThreshold;
	private int sniffAngle;
	private boolean hasReleasedChem;		
	private RectangleFiniteGrid currentGrid;

	/**
	 *  constructor
	 */
	public AgentCell() {
		super(1);
		myWiggleProb = 0;
		myWiggleAngle = 0;
		sniffThreshold = 0;
		sniffAngle = 0;
		hasReleasedChem = false;

	}
	
	/**
	 * makes a copy
	 */
	public AgentCell(AgentCell another){
		super(1);
		this.myWiggleProb= another.myWiggleProb;
		this.myWiggleAngle= another.myWiggleAngle;
		this.sniffThreshold= another.sniffThreshold;
		this.sniffAngle= another.sniffAngle;
		this.hasReleasedChem = another.hasReleasedChem;
	}
	
	/**
	 * makes empty when i=0
	 */
	public AgentCell(int i){
		super(i);
		myWiggleProb = 0;
		myWiggleAngle = 0;
		sniffThreshold = 0;
		sniffAngle = 0;
		hasReleasedChem = true;
	}


	/**
	 * Chemicals do not interact with each other and thus a checkAndTakeAction method does not need to be developed
	 */
	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		return null;
	}

	public void releaseChemical(){
		if(!hasReleasedChem){
		}
		hasReleasedChem = true;
	}

	@Override
	public Cell makeEmptyCell() {
		// TODO Auto-generated method stub
		return null;
	}
}
