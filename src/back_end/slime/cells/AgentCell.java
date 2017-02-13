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
	 *  this cell moves in direction of higher CHEMIAL concentrario
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
	//if it hasnt realeased it then will be false, if true, cannot realease
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
	 */
	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		return null;
/*
		double chemicalThreshold = ((SlimeSimInfo) simInfo).getSniffThreshold();
		double wiggleAngle = ((SlimeSimInfo) simInfo).getWiggleAngle();
		double wiggleProb = ((SlimeSimInfo) simInfo).getProbWiggle();
		double sniffAngle = ((SlimeSimInfo) simInfo).getSniffAngle();
		double sniffThreshold = ((SlimeSimInfo) simInfo).getSniffThreshold();
		double maxChemical = 0.0;
		Cell maxNeighbor = null;

		//if it has mold new will have mold
		if(!isEmpty()){
			new ActionBySim(false);
		}

		else {
			for(Cell neighbor : neighbors) {
				SlimeCell currentNeighbor = (SlimeCell) neighbor;
				//int currentNeighborX = currentNeighbor.getX();
				Cell a = currentGrid.getCellAt(new GridLocation(currentNeighbor.get, resultant_col)).;
				if ( a >= maxChemical) {
					maxChemical = groundGrid[x][y].getChemicalPercent();
					maxNeighbor = currentNeighbor;
				}
				return new ActionBySim((maxChemical >= chemicalThreshold) );	
			}
		}			
		return new ActionBySim(false);*/
	}

	//get the ground grid -> increase chemical in that one
	public void releaseChemical(){
		if(!hasReleasedChem){

		}
		//cannot release 
		hasReleasedChem = true;
	}
}
