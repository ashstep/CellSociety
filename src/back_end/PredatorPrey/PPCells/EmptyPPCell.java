package back_end.PredatorPrey.PPCells;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.PredatorPrey.ActionByPPSim;
import back_end.PredatorPrey.PredatorPreyCell;
/**
 * Cell extends PredatorPreyCell because the simulation involves various kinds of cells
 */

public class EmptyPPCell extends PredatorPreyCell{
	
	/**
	 * default constructor
	 */
	public EmptyPPCell() {
		super(0);
	}

	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		return new ActionByPPSim(false, false, false, false, 0);
	}
}
