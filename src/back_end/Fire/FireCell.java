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
 * kept for clarification as of now, once code is connected we can remove:
 *	 STATE_EMPTY = 0;
	 STATE_TREE = 1;
	 STATE_BURNING = 2;
 */
public class FireCell extends Cell {
	private final int STATE_EMPTY = 0;
	private final int STATE_TREE = 1;
	private final int STATE_BURNING = 2;
	private final Color BURNING_COLOR = Color.RED;
	private final Color TREE_COLOR = Color.GREEN;
	private final Color EMPTY_COLOR = Color.WHITE;
	private double probCatch;
	public static final double CELL_SIZE = 20;


	
	private String state;

	//must input a 0 1 or 2 into cellType 
	public FireCell(double probCatch, int cellType) {
		this.probCatch = probCatch;
		this.setMyType(cellType);
	}
	
	
	
	
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
	
	
	public ArrayList<Cell> getDirectNeighbors(Cell cell){
		ArrayList<Cell> allNeighbors = new ArrayList<Cell>();
		Cell neighbor1 = null, neighbor2 = null, neighbor3 = null, neighbor4 = null;
		
		double originalX = cell.getCellX();
		double originalY = cell.getCellY();
		
		//NOTE need to check if the "neighbor" is out of bounds of the grid or not
		//need to refactor
		neighbor1.setCellX(originalX + CELL_SIZE);
		neighbor1.setCellY(originalY);
		allNeighbors.add(neighbor1);
		
		neighbor2.setCellX(originalX - CELL_SIZE);
		neighbor2.setCellY(originalY);
		allNeighbors.add(neighbor2);

		neighbor3.setCellX(originalX);
		neighbor3.setCellY(originalY + CELL_SIZE);
		allNeighbors.add(neighbor3);

		neighbor4.setCellX(originalX);
		neighbor4.setCellY(originalY - CELL_SIZE);
		allNeighbors.add(neighbor4);
		
		return allNeighbors;
	}
	
	
	@Override
	public boolean checkAndTakeAction(Cell firecell, Cell[] neighbors, SimulationInfo simInfo) {
		
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
		}
		return false;
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

}
