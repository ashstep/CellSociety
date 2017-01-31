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

	
	
	@Override
	public boolean checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo) {
		for(){
			
		}
		return false;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
