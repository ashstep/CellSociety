package back_end.Segregation;

import java.util.ArrayList;
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
	
	public SegregationCell(int myType){
		super(myType);
	}
	
	
	/**
	 * Assumes simInfo is in fact a SegregationSimInfo object. 
	 * Also assumes neighbors is just an ArrayList of SegregationCell
	 */
	@Override
	public boolean checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) {
		if(getMyType()==TYPE_EMPTY){
			return false;
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
		int percentage=100*myTypeCells/totalNeighbors;
		return percentage<threshold;
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
	public boolean isTypeeEmpty(){
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
}
