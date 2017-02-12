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
	private final int TYPE_TWO = 2;
	private final int TYPE_EMPTY = 0;
	private final Color TYPE_ALIVE_COLOR = Color.GREEN;
	private final Color TYPE_TWO_COLOR = Color.YELLOW;
	private final Color TYPE_EMPTY_COLOR = Color.GRAY;

	
	public SlimeCell(int type) {
		super(type);
	}
	
	//create a copy
	public SlimeCell(SlimeCell anotherCell){
		this(anotherCell.getMyType());
	}

	/**
	 * @param true if the cell wants to move
	 */
	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeEmptyCell() {
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
