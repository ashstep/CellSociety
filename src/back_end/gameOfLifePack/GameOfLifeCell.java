package back_end.gameOfLifePack;
import java.util.ArrayList;
import java.util.Collection;

import back_end.ActionBySim;
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
	private final int ALIVE_TYPE=1;
	private final int DEAD_TYPE=0;
	private final Color DEAD_COLOR=Color.WHITE;
	private final Color ALIVE_COLOR=Color.GREEN;
	
	public GameOfLifeCell(GameOfLifeCell anotherCell){
		this(anotherCell.getMyType());
	}
	
	public GameOfLifeCell(int type) {
		super(type);
	}
	/**
	 * only need to check status of neighbors, simInfo is unused
	 * @return false because game of life cells do not move
	 */
	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
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
		} else if(aliveNeighbors==3 && isDead()){
			revive();
		}
		return new ActionBySim(false);
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
	
	/**
	 *
	 * @return true if cell is alive
	 */
	public boolean isAlive(){
		return getMyType()==ALIVE_TYPE;
	}
	
	/**
	 * 
	 * @return true if cell is dead
	 */
	public boolean isDead(){
		return getMyType()==DEAD_TYPE;
	}

	@Override
	public Cell makeEmptyCell() {
		return new GameOfLifeCell(DEAD_TYPE);
	}

	@Override
	public Color getColor() {
		if(isAlive()){
			return ALIVE_COLOR;
		}  else {
			return DEAD_COLOR;
		}
	}

	@Override
	public Collection<String> getTypeNames()
	{
		Collection<String> nameList = new ArrayList<String>();
		nameList.add("Alive");
		return nameList;
	}

	@Override
	public String getTypeName()
	{
		if (getMyType() == 1) return "Alive";
		else return "";
	}

	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException{
		if(type==ALIVE_TYPE){
			System.out.println("making ALIVE GoL cell");
			return new GameOfLifeCell(ALIVE_TYPE);
		} else if(type==DEAD_TYPE){
			System.out.println("making DEAD GoL cell");
			return new GameOfLifeCell(DEAD_TYPE);
		} else {
			throw new IllegalArgumentException("Invalid GameOfLifeCell type");
		}
	}

	@Override
	public Cell makeNextStateCell() {
		try{
			return makeCellofType(getMyType()+1);
		} catch (IllegalArgumentException e){
			return makeCellofType(DEAD_TYPE);
		}
	}

}