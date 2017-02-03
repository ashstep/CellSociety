package back_end.PredatorPrey;

import java.util.ArrayList;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import back_end.PredatorPrey.PPCells.EmptyPPCell;
import back_end.PredatorPrey.PPCells.FishCell;
import back_end.PredatorPrey.PPCells.SharkCell;
import back_end.gameOfLifePack.GameOfLifeCell;

/**
 * @author Yuxiang He
 *
 */
public class PredatorPreySim extends Simulation {
	private PredatorPreySimInfo myInfo;
	private final int FISH=1;
	private final int SHARK=2;
	private final int EMPTY=0;
	private final int[] ROW_OFFSET={1, -1, 0, 0};
	private final int[] COL_OFFSET ={0,  0, 1, -1};
	
	
	/**
	 * constructor
	 * @param typeGrid
	 * @param sharkBreedTime
	 * @param sharStarveTime
	 * @param fishBreedTime
	 */
	public PredatorPreySim(int[][] typeGrid, int sharkBreedTime, int sharStarveTime, int fishBreedTime){
		myInfo=new PredatorPreySimInfo(sharkBreedTime, sharStarveTime, fishBreedTime);
		setCellGrid(typeGrid);
	}
	
	
	/**
	 * helper method for constructor, sets the cellGrid from typeGrid
	 * @param typeGrid
	 */
	private void setCellGrid(int[][] typeGrid) {
		int numRows=typeGrid.length, numCols=typeGrid[0].length;
		PredatorPreyCell[][] cellGrid=new PredatorPreyCell[numRows][numCols];
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				if(typeGrid[row][col]==FISH){
					cellGrid[row][col]=new FishCell();
				} else if(typeGrid[row][col]==SHARK){
					cellGrid[row][col]=new SharkCell();
				} else {
					cellGrid[row][col]=new EmptyPPCell();
				}
			}
		}
		super.setArrayGrid(cellGrid);
	}
	
	
	
	/**
	 * updates grid
	 */
	@Override
	public Cell[][] updateGrid() {
		int numRows = super.getNumRows(),  numCols = super.getNumCols();
		Cell[][] newGrid=new Cell[numRows][numCols];
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				if (super.getGrid()[row][col].getMyType()==SHARK){
					SharkCell newSharkCell=new SharkCell((SharkCell)  super.getGrid()[row][col]);
					ActionByPPSim furtherActions=(ActionByPPSim) newSharkCell.checkAndTakeAction(getNeighbors(row, col), myInfo);
					
					
					if(furtherActions.toDie()){
						newGrid[row][col]=new EmptyPPCell();
					}else if(furtherActions.toEat()){
						//Each turn if there is a fish adjacent to a shark the shark eats it.
						//If there are multiple adjacent fish the shark eats one at random
						
					} else if(furtherActions.toMove()){
						//If there are no adjacent fish the shark moves in the same manner as fish
					}
					
					if(furtherActions.wantsToReproduce()){
						//After eating or moving if the shark has survived the number of turns necessary to breed it produces a new shark
					}
					
					
				}  else {
					continue;
				}
			}
		}
		
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				if (super.getGrid()[row][col].getMyType()==FISH){
					FishCell newFishCell=new FishCell(  (FishCell)  super.getGrid()[row][col]);
					ActionByPPSim furtherActions=(ActionByPPSim) newFishCell.checkAndTakeAction(getNeighbors(row, col), myInfo);
					
				}  else {
					continue;
				}
			}
		}
		
		setArrayGrid(newGrid);
		return newGrid;
	}
	


	/**
	 * get the neighbors from the original grid.
	 * top, down, left, right
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output=new ArrayList<Cell>();	
		for(int i=0; i<ROW_OFFSET.length; i++){
			int resultant_row=row+ROW_OFFSET[i], resultant_col=col+COL_OFFSET[i];
			if(super.isValidPosition(resultant_row, resultant_col)){
				output.add(super.getGrid()[resultant_row][resultant_col]);
			}
		}
		return output;
	}
	
	/**
	 * 
	 * @param cellRow row of the cell
	 * @param cellCol column of the cell
	 * @param neighborType type of the neighbor the cell is looking for
	 * @return a random position that specifies the neighbor with the desired neighborType
	 */
	private int[] findRandomNeighborPos(int cellRow, int cellCol, int neighborType){
		for(int i=0; i<ROW_OFFSET.length; i++){
			int resultant_row=row+ROW_OFFSET[i], resultant_col=col+COL_OFFSET[i];
			if(super.isValidPosition(resultant_row, resultant_col)){
				output.add(super.getGrid()[resultant_row][resultant_col]);
			}
		}
		return null;
	}

	@Override
	protected int[] move(Cell[][] newGrid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		// TODO Auto-generated method stub
		
	}
}
