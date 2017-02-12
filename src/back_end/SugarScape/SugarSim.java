/**
 * 
 */
package back_end.SugarScape;

import java.util.ArrayList;
import java.util.function.Consumer;

import Grids.Grid;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.GridLocation;

/**
 * @author YuxiangHe
 *
 */
public class SugarSim extends Simulation {

	
	
	/**
	 * 
	 */
	public SugarSim() {
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see back_end.Simulation#updateGrid()
	 */
	@Override
	public Grid updateGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#findEmptySpots(Grids.Grid, int, int)
	 */
	@Override
	protected GridLocation findEmptySpots(Grid grid, int currentRow, int currentCol) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#setSimInfo(back_end.SimulationInfo)
	 */
	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getSimInfo()
	 */
	@Override
	public SimulationInfo getSimInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getParameterList()
	 */
	@Override
	public ArrayList<String> getParameterList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getChangeMethod(java.lang.String)
	 */
	@Override
	public Consumer<Number> getChangeMethod(String x) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getSliderLowerBound(java.lang.String)
	 */
	@Override
	public double getSliderLowerBound(String x) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getSliderUpperBound(java.lang.String)
	 */
	@Override
	public double getSliderUpperBound(String x) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see back_end.Simulation#getCurrentValue(java.lang.String)
	 */
	@Override
	public double getCurrentValue(String x) {
		// TODO Auto-generated method stub
		return 0;
	}

}
