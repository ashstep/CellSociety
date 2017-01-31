package front_end;

import back_end.Cell;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUI
{
	private Group root;
	private int sceneWidth, sceneHeight;
	private Rectangle[][] grid;
	private ControlPanel panel;
	
	public GUI (int sceneWidth, int sceneHeight)
	{
		this.sceneWidth = sceneWidth;
		this.sceneHeight = sceneHeight;
		root = new Group();
	}
	
	public Scene setScene()
	{
		Scene myScene = new Scene(root, sceneWidth, sceneHeight, Color.WHITE);
		panel = new ControlPanel(root);
		return myScene;
	}
	
	public void initGrid(int gridWidth, int gridLength, int cellSize)
	{
		grid = new Rectangle[gridLength][gridWidth];
		for (int x = 0; x < gridLength; x++)
		{
			for (int y = 0; y < gridWidth; y++)
			{
				grid[x][y] = new Rectangle(cellSize, cellSize);
				grid[x][y].setX(y*cellSize);
				grid[x][y].setY(x*cellSize);
				root.getChildren().add(grid[x][y]);
			}
		}
	}
	
	public void renderGrid(Cell[][] cells)
	{
		for (int x = 0; x < cells.length; x++)
		{
			for (int y = 0; y < cells[0].length; y++)
			{
				grid[x][y].setFill(cells[x][y].getColor());
			}
		}
	}
	public void initButtons(Runnable playMethod, Runnable pauseMethod, Runnable stepMethod)
	{
		panel.setPlay(playMethod);
		panel.setPause(pauseMethod);
		panel.setStep(stepMethod);
	}
}
