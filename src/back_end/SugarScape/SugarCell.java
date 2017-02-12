/**
 * 
 */
package back_end.SugarScape;


import back_end.Cell;


/**
 * @author Richard
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


	/* (non-Javadoc)
	 * @see back_end.Cell#makeEmptyCell()
	 */
	@Override
	public Cell makeEmptyCell() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Cell#makeCellofType(int)
	 */
	@Override
	public Cell makeCellofType(int type) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see back_end.Cell#makeNextStateCell()
	 */
	@Override
	public Cell makeNextStateCell() {
		// TODO Auto-generated method stub
		return null;
	}
}
