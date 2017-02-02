package back_end.PredatorPrey;

import java.util.ArrayList;
import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;

public class FishCell extends PredatorPreyCell{
	private final int MY_TYPE=1;
	private final int EMPTY=0;
	
	/**
	 * default constructor
	 */
	public FishCell() {
		super(1);
	}

	/**
	 * makes a copy of another FishCell
	 * @param anotherFishCell
	 */
	public FishCell(FishCell anotherFishCell){
		this();
	}
	
	@Override
	public ActionBySim checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) {
		super.incrementTimeSinceBreed();
		int breedTime=((PredatorPreySimInfo) simInfo).getFishBreedTime();
		int emptyNeighbors=0;
		for(Cell neighbor: neighbors){
			if(neighbor.getMyType()==EMPTY){
				emptyNeighbors++;
			}
		}
		boolean moving=emptyNeighbors>0;
		boolean reproducing=super.getTimeSinceBreed()>breedTime;
		return new ActionByPPSim(reproducing, moving, false, false, MY_TYPE);
	}

}
