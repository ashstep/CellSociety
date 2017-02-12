package back_end.slime.cells;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.slime.SlimeCell;
import javafx.scene.paint.Color;

public class AgentCell extends SlimeCell {

	/**
	 *  this cell moves in direction of higher CHEMIAL concentrario
	 *  
	 */
	private final int TYPE_AGENT = 1;
	private final int TYPE_EMPTY = 0;
	private final Color TYPE_AGENT_COLOR = Color.GREEN;
	private final Color TYPE_EMPTY_COLOR = Color.TRANSPARENT;
	private double myWiggleProb;
	private int myWiggleAngle;
	private int sniffThreshold;
	private int sniffAngle;
	private boolean hasReleasedChem;		//if it hasnt realeased it then will be false, if true, cannot realease

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
	 * checks in direction of sniff
	 * checks ground grid
	 * if ground grid is
	 * on the OTHER CHEMICAL GRID
	 * 
	 */
		@Override
		public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
			// TODO Auto-generated method stub
			return null;
		}

		//get the ground grid -> increase chemical in that one
		public void releaseChemical(){
			if(!hasReleasedChem){
				
			}
			//cannot release anymore
			hasReleasedChem = true;
		}


		
}
