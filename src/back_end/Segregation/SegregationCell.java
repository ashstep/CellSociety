package back_end.Segregation;

import java.util.ArrayList;
import java.util.Collection;

import back_end.ActionBySim;
import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

public class SegregationCell extends Cell {
	/*
	 * note regarding myType in Cell class
	 * 1: type 1
	 * 2: type 2
	 * 0: empty
	 */
	private final int TYPE_ONE=1;
	private final int TYPE_TWO=2;
	private final int TYPE_EMPTY=0;
	private final Color TYPE_ONE_COLOR=Color.RED;
	private final Color TYPE_TWO_COLOR=Color.BLUE;
	private final Color TYPE_EMPTY_COLOR=Color.WHITE;
	
	
	/**
	 * default constructor
	 * @param type
	 */
	public SegregationCell(int type){
		super(type);
	}
	
	/**
	 * makes a copy of another SegregationCell
	 * @param anotherCell
	 */
	//refactor to abstract class?
	public SegregationCell(SegregationCell anotherCell){
		this(anotherCell.getMyType());
	}
	
	/**
	 * Assumes simInfo is in fact a SegregationSimInfo object. 
	 * Also assumes neighbors is just an ArrayList of SegregationCell
	 */
	@Override
	public ActionBySim checkAndTakeAction(Collection<Cell> neighbors, SimulationInfo simInfo) {
		if(isTypeEmpty()){
			return new ActionBySim(false);
		}
		int threshold=((SegregationSimInfo) simInfo).getThreshold();
		int totalNeighbors=0;
		int myTypeCells=0;
		for(Cell neighborCell: neighbors){
			SegregationCell neighbor=(SegregationCell) neighborCell;
			if(neighbor.getMyType()==this.getMyType()) {
				myTypeCells++;
			} if (neighbor.getMyType()!=TYPE_EMPTY){
				totalNeighbors++;
			}
		}
		if(totalNeighbors!=0){
			int percentage=100*myTypeCells/totalNeighbors;
			return new ActionBySim(percentage<threshold);
		} else {
			//no neighbors, move to new place
			return new ActionBySim(true);
		}
		
	}
	
	
	/**
	 * 
	 * @return true if cell is type 1
	 */
	public boolean isTypeOne(){
		return getMyType()==TYPE_ONE;
	}
	
	/**
	 * 
	 * @return true if cell is type 2
	 */
	public boolean isTypeTwo(){
		return getMyType()==TYPE_TWO;
	}
	
	/**
	 * 
	 * @return true if cell is type empty
	 */
	public boolean isTypeEmpty(){
		return getMyType()==TYPE_EMPTY;
	}
	
	/**
	 * calculates the color depending on the myType
	 */
	@Override
	public Color getColor() {
		if(isTypeOne()){
			return TYPE_ONE_COLOR;
		} else if(isTypeTwo()){
			return TYPE_TWO_COLOR;
		}
		return TYPE_EMPTY_COLOR;
	}

	@Override
	public Collection<String> getTypeNames()
	{
		Collection<String> nameList = new ArrayList<String>();
		nameList.add("Population 1");
		nameList.add("Population 2");
		return nameList;
	}

	@Override
	public String getTypeName()
	{
		if (getMyType() == TYPE_ONE) return "Population 1";
		else if (getMyType() == TYPE_TWO) return "Population 2";
		else return "";
	}
	
	@Override
	public Cell makeEmptyCell() {
		return new SegregationCell(TYPE_EMPTY);
	}

	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException {
		if(type==TYPE_ONE){
			return new SegregationCell(TYPE_ONE);
		} else if(type==TYPE_TWO){
			return new SegregationCell(TYPE_TWO);
		} else if(type==TYPE_EMPTY){
			return new SegregationCell(TYPE_EMPTY);
		} else {
			throw new IllegalArgumentException("Invalid SegregationCell type");
		}
	}

	@Override
	public Cell makeNextStateCell() {
		try{
			return makeCellofType(getMyType()+1);
		} catch (IllegalArgumentException e){
			return makeCellofType(TYPE_EMPTY);
		}
	}
}
