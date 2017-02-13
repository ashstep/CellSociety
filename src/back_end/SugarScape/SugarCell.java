package back_end.SugarScape;


import back_end.Cell;
/**
 * @author Yuxiang He
 *
 */
public abstract class SugarCell extends Cell{

	private final int AGENT_TYPE=1;
	private final int GROUND_TUPE=0;
	
	/**
	 * @param type
	 */
	public SugarCell(int type) {
		super(type);
	}


	@Override
	public Cell makeEmptyCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Cell makeNextStateCell() {
		// TODO Auto-generated method stub
		return null;
	}
}
