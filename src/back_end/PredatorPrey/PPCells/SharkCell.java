package back_end.PredatorPrey.PPCells;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.PredatorPrey.ActionByPPSim;
import back_end.PredatorPrey.PredatorPreyCell;
import back_end.PredatorPrey.PredatorPreySimInfo;

public class SharkCell extends PredatorPreyCell{
	
	private int timeSinceDinner;
	private final int MY_TYPE=2;
	private final int FISH=1;
	private final int EMPTY=0;
	
	/**
	 * default constructor
	 */
	public SharkCell() {
		super(2);
		timeSinceDinner=0;
	}
	
	/**
	 * makes a copy of another SharkCell
	 */
	public SharkCell(SharkCell anotherSharkCell) {
		super(2);
		this.timeSinceDinner=anotherSharkCell.timeSinceDinner;
	}

	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		timeSinceDinner++;
		super.incrementTimeSinceBreed();	
		int starveThreshold=((PredatorPreySimInfo) simInfo).getSharkStarveTime(),
				breedTime=((PredatorPreySimInfo) simInfo).getSharkBreedTime();
		if(isStarvedToDeath(starveThreshold)){
			super.die();
			return new ActionByPPSim(false, false, false, true, MY_TYPE);
		} else {
			int fishNeighbors=0;
			int emptyNeighbors=0;
			for(Cell neighbor: neighbors){
				if(neighbor.getMyType()==FISH){
					fishNeighbors++;
				}
				if(neighbor.getMyType()==EMPTY){
					emptyNeighbors++;
				}
			}
			boolean reproducing = checkThenReproduce(breedTime);
			boolean moving=false;
			boolean eating=false;	
			//if there is a fish adjacent to a shark the shark eats it
			//If there are no adjacent fish the shark moves
			if(fishNeighbors>0){
				eating=true;
				timeSinceDinner=0;
				moving=false;
			} else if(emptyNeighbors>0){
				moving=true;
			}
			return new ActionByPPSim(reproducing, moving, eating, false, MY_TYPE);
		}
	}

	/**
	 * 
	 * @param breedTime time between breeding of a shark
	 * @param emptyNeighbors  number of empty neighbors
	 * @return true if wants to breeding
	 */
	private boolean checkThenReproduce(int breedTime) {
		boolean reproducing=super.getTimeSinceBreed()>breedTime;
		return reproducing;
	}

	
	/**
	 * Given the starve threshold, determine if the shark is starved to death
	 * @param starveThreshold
	 * @return true if the shark has died
	 */
	private boolean isStarvedToDeath(int starveThreshold){
		return timeSinceDinner>starveThreshold;
	}
	
	
	public void resetTimeSinceDinner(){
		timeSinceDinner=0;
	}
	
	@Override
	public Cell makeEmptyCell() {
		return new EmptyPPCell();
	}
	
}
