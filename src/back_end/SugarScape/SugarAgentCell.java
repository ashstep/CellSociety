package back_end.SugarScape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;
import utilities.GridLocation;

public class SugarAgentCell extends Cell{

	private boolean isMale;
	private int myAge;
	private int maxAge;
	private final int MAX_AGE_LOWER_BOUND=60;
	private final int MAX_AGE_UPPER_BOUND=100;
	
	private class GroundCellComparator implements Comparator<SugarGroundCell>{
		private GridLocation myAgentLocation;
		GroundCellComparator(GridLocation agentLocation){
			myAgentLocation=agentLocation;
		}
		/**
		 * compares priority of two patches of ground
		 * 1. more sugar is better
		 * 2. closer is better
		 */
		@Override
		public int compare(SugarGroundCell a, SugarGroundCell b) {
			if(b.getSugarLevel()-a.getSugarLevel()!=0){
				return b.getSugarLevel()-a.getSugarLevel();
			} else {
				return (int) (myAgentLocation.getDistance(a.getMyPosition())-myAgentLocation.getDistance(b.getMyPosition()));
			}
		}
		
	}
	
	public SugarAgentCell(int gender, int age, int fertileLimits) {
		super(1);
		isMale= gender==1? true: false;
		myAge=age;
		maxAge=new Random().nextInt(MAX_AGE_UPPER_BOUND-MAX_AGE_LOWER_BOUND+1)
				+MAX_AGE_LOWER_BOUND;
	}

	
	
	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		ArrayList<Cell>  neighborsAL=new ArrayList<>(neighbors);
		SugarSimInfo info=(SugarSimInfo) simInfo;
		Collections.sort(neighborsAL, new GroundCellComparator(info.getMyLocation()));
		return null;
	}

	
	@Override
	public Cell makeEmptyCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeNextStateCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

}
