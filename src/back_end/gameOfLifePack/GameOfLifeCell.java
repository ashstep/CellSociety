package back_end.gameOfLifePack;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell{
	/*
	 * note regarding myType in Cell class
	 * 1: dead
	 * 2: alive
	 * 0: empty
	 */
	private final int ALIVE_TYPE=2;
	private final int DEAD_TYPE=1;
	private final int EMPTY_TYPE=0;
	private final Color DEAD_COLOR=Color.RED;
	private final Color ALIVE_COLOR=Color.GREEN;
	private final Color EMPTY_COLOR=Color.WHITE;
	
	@Override
	public boolean checkAndTakeAction(Cell[] neighbors, SimulationInfo simInfo) {
		int aliveNeighbors=0;
		for(Cell neighbor: neighbors){
			if(neighbor.getMyType()==ALIVE_TYPE) aliveNeighbors++;
		}
		if(isAlive()){
			if(aliveNeighbors<2 || aliveNeighbors>3){
				die();
			} else{
				liveOn();
			}
		} else if(aliveNeighbors==3){
			revive();
		}
		return false;
	}
	
	/**
	 * this cell becomes alive again
	 */
	public void revive(){
		setMyType(ALIVE_TYPE);
	}
	
	/**
	 * this cell dies
	 */
	public void die(){
		setMyType(DEAD_TYPE);
	}
	
	/**
	 * this cell 'lives on' i.e. does nothing
	 */
	public void liveOn(){
		
	}
	
	
	public boolean isAlive(){
		return getMyType()==ALIVE_TYPE;
	}
	
	public boolean isEmpty(){
		return getMyType()==EMPTY_TYPE;
	}

	@Override
	public Color getColor() {
		if(isAlive()){
			return ALIVE_COLOR;
		} else if(isEmpty()){
			return EMPTY_COLOR;
		} else{
			return DEAD_COLOR;
		}
	}

}
