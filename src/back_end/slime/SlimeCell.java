package back_end.slime;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.Segregation.SegregationCell;
import javafx.scene.paint.Color;

public class SlimeCell extends Cell {
 /*
  * 
  */
	
	private final int TYPE_ALIVE = 1;
	private final int TYPE_EMPTY = 0;
	private final Color TYPE_ALIVE_COLOR = Color.GREEN;
	private final Color TYPE_EMPTY_COLOR = Color.TRANSPARENT;

	
	public SlimeCell(int type) {
		super(type);
	}
	
	//create a copy
	public SlimeCell(SlimeCell anotherCell){
		this(anotherCell.getMyType());
	}

	/**
	 * @param true if the cell wants to move
	 * cell moves based on where concentration is highest
	 */
	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	

	/*
	 * getters and setters
	 */
	private void setTreeEmpty(){
		setMyType(TYPE_EMPTY);
	}

	private boolean isEmpty(){
		return getMyType() == TYPE_EMPTY;
	}
	
	private void setTreeAlive(){
		setMyType(TYPE_ALIVE);
	}

	private boolean isAlive(){
		return getMyType() == TYPE_ALIVE;
	}
	
	@Override
	public Color getColor() {
		if(isAlive()){
			return TYPE_ALIVE_COLOR;
		} 

		else {
			return TYPE_EMPTY_COLOR;
		}
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
	public Cell makeEmptyCell() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
