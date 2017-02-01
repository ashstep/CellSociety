package back_end.PredatorPrey;

import java.util.ArrayList;

import back_end.Cell;
import back_end.SimulationInfo;
import javafx.scene.paint.Color;

public class PredatorPreyCell extends Cell {
	private final int TYPE_ONE=1;
	private final int TYPE_TWO=2;
	private final int TYPE_EMPTY=0;
	private final Color TYPE_ONE_COLOR = Color.RED;
	private final Color TYPE_TWO_COLOR = Color.BLUE;
	private final Color TYPE_EMPTY_COLOR = Color.WHITE;
	
	public PredatorPreyCell(int myType){
		super();
	}

	@Override
	public boolean checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
