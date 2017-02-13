package back_end.SugarScape;

import utilities.GridLocation;

public class GroundCellInfo {
	private int maxSugarCapacity;
	private int sugarLevel;
	private GridLocation myLocation;
	private boolean isVacant;
	
	public GroundCellInfo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getMaxSugarCapacity() {
		return maxSugarCapacity;
	}

	public void setMaxSugarCapacity(int maxSugarCapacity) {
		this.maxSugarCapacity = maxSugarCapacity;
	}

	public int getSugarLevel() {
		return sugarLevel;
	}

	public void setSugarLevel(int sugarLevel) {
		this.sugarLevel = sugarLevel;
	}

	public GridLocation getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(GridLocation myLocation) {
		this.myLocation = myLocation;
	}

	public boolean isVacant() {
		return isVacant;
	}

	public void setVacant(boolean isVacant) {
		this.isVacant = isVacant;
	}

}
