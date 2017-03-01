package back_end.Fire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

/**
 * Purpose: Class that implements the unique properties of A SINGLE CELL in the fire simulation.
 * Assumptions: Each cell only deals with information pertinent to itself and does not look at other cells.
 * Each cell has three possible states (empty, tree present, or burning) and these states determine (in part) what action will be taken by each cell)
 * Dependencies: Dependent on FireSimInfo since that holds the probability each cell catches on fire.
 * Example of use: Each cell's state is determined and that leads to how it reacts to its neighbors. The most important method is checkAndTakeAction().
 * @author Ashka Stephen
 */

public class FireCell extends Cell {
	private final int STATE_EMPTY = 0;
	private final int STATE_TREE = 1;
	private final int STATE_BURNING = 2;
	private final Color BURNING_COLOR = Color.RED;
	private final Color TREE_COLOR = Color.GREEN;
	private final Color EMPTY_COLOR = Color.WHITE;
	
	/**
	 * Default Constructor
	 * Purpose: creation of each cell and its inherent properties
	 * May fail if there are more than 3 states (because type is only valid for 0/1/2.
	 * @param type of the cell -> specifics outlined above
	 */
	public FireCell(int type) {
		super(type);
	}

	/**
	 * Creates Copy
	 * This would be used to update the grid information. We copy over all cells and determine action from there.
	 * @param the copied cell
	 * @return same properties of previous cell
	 */
	public FireCell(FireCell anotherCell) {
		this(anotherCell.getMyType());
	}

	/**
	 * @param Collection of neighbors so that the cell determines action based on neighbors' states (inherent assumption here)
	 * @param SimulationInfo object to pass on properties shared by all cells
	 * Additional Notes:
	 * Only need to check status of neighbors, simInfo is unused
	 * Returns false because cells do not move
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
	 * The following are getters and setters for the state of the tree
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
		if (getMyType() == STATE_TREE) return "Tree";
		else if (getMyType() == STATE_BURNING) return "Burning"; 
		else return "";
	}

	@Override
	public Cell makeEmptyCell() {
		return new FireCell(STATE_EMPTY);
	}

	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException {
		if(type==STATE_EMPTY){
			return new FireCell(STATE_EMPTY);
		} else if(type==STATE_TREE){
			return new FireCell(STATE_TREE);
		} else if(type==STATE_BURNING){
			return new FireCell(STATE_BURNING);
		} else {
			throw new IllegalArgumentException("Invalid FireCell type");
		}
	}
	
	@Override
	public Cell makeNextStateCell() {
		try{
			return makeCellofType(getMyType()+1);
		} catch (IllegalArgumentException e){
			return makeCellofType(STATE_EMPTY);
		}
	}
}
