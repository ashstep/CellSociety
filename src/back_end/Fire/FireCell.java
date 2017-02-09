package back_end.Fire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

/**
 * Class that implements the unique properties of each cell in the fire simulation
 * @author Ashka Stephen
 *
 */
public class FireCell extends Cell {
	private final int STATE_EMPTY = 0;
	private final int STATE_TREE = 1;
	private final int STATE_BURNING = 2;
	private final Color BURNING_COLOR = Color.RED;
	private final Color TREE_COLOR = Color.GREEN;
	private final Color EMPTY_COLOR = Color.WHITE;
	
	/**
	 * default constructor
	 * @param type of the cell
	 */
	public FireCell(int type) {
		super(type);
	}

	/**
	 * makes copy
	 * @param type type of the cell
	 */
	public FireCell(FireCell anotherCell) {
		this(anotherCell.getMyType());
	}

	/**
	 * only need to check status of neighbors, simInfo is unused
	 * returns false because game of life cells do not move
	 */

	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		int burningNeighbors = 0;
		double randProbofFire = new Random().nextDouble();
		for(Cell neighbor: neighbors){
			if(neighbor.getMyType() == STATE_BURNING){
				burningNeighbors++;
			}
		}
		if (isEmpty()){
			return new ActionBySim(false);
		}

		if (isBurning()){
			setTreeEmpty();
			return new ActionBySim(false);
		}


		if((isAlive()) && (randProbofFire <= ((FireSimInfo)simInfo).getProbCatch()) && (burningNeighbors!=0)){
			setTreeBurning();
			return new ActionBySim(false);
		}
		return new ActionBySim(false);
	}


	/**
	 * Getters and setters for the state of the tree
	 *
	 */

	private boolean isAlive(){
		return getMyType() == STATE_TREE;
	}

	private void setTreeEmpty(){
		setMyType(STATE_EMPTY);
	}

	private boolean isEmpty(){
		return getMyType() == STATE_EMPTY;
	}

	private void setTreeBurning(){
		setMyType(STATE_BURNING);
	}

	private boolean isBurning(){
		return getMyType() == STATE_BURNING;
	}

	@Override
	public Color getColor() {
		if(isAlive()){
			return TREE_COLOR;
		} 
		if(isBurning()){
			return BURNING_COLOR;
		} 
		else {
			return EMPTY_COLOR;
		}
	}

	@Override
	public Collection<String> getTypeNames() {
		Collection<String> typeList = new ArrayList<String>();
		typeList.add("Tree");
		typeList.add("Burning");
		return typeList;
	}

	@Override
	public String getTypeName()
	{
		if (getMyType() == 1) return "Tree";
		else if (getMyType() == 2) return "Burning"; 
		else return "";
	}

	@Override
	public Cell makeEmptyCell() {
		return new FireCell(STATE_EMPTY);
	}
}
