package back_end.slime.cells;

import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import back_end.slime.SlimeCell;
import javafx.scene.paint.Color;

public class AgentCell extends SlimeCell {
	 
	private final int TYPE_AGENT = 1;
	private final int TYPE_EMPTY = 0;
	private final Color TYPE_AGENT_COLOR = Color.GREEN;
	private final Color TYPE_EMPTY_COLOR = Color.TRANSPARENT;

	
	/**
	 *  constructor
	 */
	public AgentCell() {
		super(1);
	}

	/**
	 * makes a copy
	 */
	public AgentCell(AgentCell another){
		this(another.getMyType());
		}
	


	/**
	 * checks neighbors, makes actions
	 */
		@Override
		public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
			// TODO Auto-generated method stub
			return null;
		}

		

		/*
		 * getters and setters
		 */
		private void setEmpty(){
			setMyType(TYPE_EMPTY);
		}

		private boolean isEmpty(){
			return getMyType() == TYPE_EMPTY;
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
