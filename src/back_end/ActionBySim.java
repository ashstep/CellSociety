package back_end;

/**
 * to be extended by other simulations if more actions by Simulation is required
 * @author Yuxiang He
 *
 */
public class ActionBySim {
	private boolean move;
	/**
	 * constructor
	 * @param toMove true if the object is to tell Simulation to move the cell
	 */
	public ActionBySim(boolean toMove) {
		move=toMove;
	}
	/**
	 * 
	 * @return true if the cell is to move
	 */
	public boolean toMove(){
		return move;
	}
}
