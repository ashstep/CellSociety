package back_end.PredatorPrey.PPCells;

import java.util.ArrayList;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.PredatorPrey.ActionByPPSim;
import back_end.PredatorPrey.PredatorPreyCell;

public class EmptyPPCell extends PredatorPreyCell{

	public EmptyPPCell() {
		super(0);
	}

	@Override
	public ActionBySim checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) {
		return new ActionByPPSim(false, false, false, false, 0);
	}

}
