package back_end.Fire;

import java.util.ArrayList;

import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

/**
 * Class that implements the unique properties of each cell in the fire simulation
 * @author Ashka Stephen
 *
 *
 *
 *
 *
 *
 *
 */
public class FireCell extends Cell {
<<<<<<< HEAD

	
	
	@Override
	public boolean checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) {
		for(){
			
=======
	private final int STATE_EMPTY = 0;
	private final int STATE_TREE = 1;
	private final int STATE_BURNING = 2;
	private final Color BURNING_COLOR = Color.RED;
	private final Color TREE_COLOR = Color.GREEN;
	private final Color EMPTY_COLOR = Color.WHITE;

<<<<<<< HEAD
=======

>>>>>>> 58258192ed11197eb219b4490362c3710c3a5ab7
	//must input a 0 1 or 2 into cellType 
	public FireCell(double probCatch, int cellType) {
		this.probCatch = probCatch;
		this.setMyType(cellType);
	}
<<<<<<< HEAD
	
	
=======




>>>>>>> 58258192ed11197eb219b4490362c3710c3a5ab7
	/**
	 * Getters and setters for the state of the tree
	 *
	 */
	private void setTreeAlive(){
		setMyType(STATE_TREE);
	}

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

<<<<<<< HEAD
	
	
	
	
=======






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



	//gives a boolean and then changes the corresponding cell in the updated grid
>>>>>>> 58258192ed11197eb219b4490362c3710c3a5ab7
	@Override
	public boolean checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) {
		//if there is no tree or a dead tree, the cell remains empty
		if (isEmpty()){
			return false;
		}

		//if a tree was burning on the last time step, it will be empty on the following
		//corresponding next step action should be that the tree is empty 
		if (isBurning()){
			return true;
		}

		//if its alive then check the 4 direct neighbors
		if(isAlive()){
			ArrayList<Cell> directNeighbors = getDirectNeighbors(firecell);
			int burningCount = 0; 

			for(Cell eachNeighbor : directNeighbors){
				if(isBurning()){
					burningCount++;
				}
			}

			//fix the probCatch method
			//checks to see if it should be on fire
			if (burningCount/4 >= firecell.probCatch()){
				return true;
			}
			return false;
>>>>>>> 70d84eff6fe3310151a3c94bf5c95009db1cc5da
		}
		return false;
	}

<<<<<<< HEAD
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
=======
>>>>>>> 70d84eff6fe3310151a3c94bf5c95009db1cc5da
}
