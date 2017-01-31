package back_end.gameOfLifePack;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell{
	/*
	 * note regarding myType in Cell class
	 * 0: dead
	 * 1: alive
	 */
	private final int ALIVE_TYPE=1;
	private final int DEAD_TYPE=0;
	private final Color DEAD_COLOR=Color.RED;
	private final Color ALIVE_COLOR=Color.GREEN;
	
	@Override
	public void checkAndTakeAction(Cell[] neighbors, SimulationInfo simInfo) {
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
		
	}
	
	/**
	 * this cell 'lives on' i.e. does nothing
	 */
	public void revive(){
		setMyType(ALIVE_TYPE);
	}
	
	/**
	 * this cell 'lives on' i.e. does nothing
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

	@Override
	public Color getColor() {
		if(isAlive()){
			return ALIVE_COLOR;
		} else {
			return DEAD_COLOR;
		}
	}

}
