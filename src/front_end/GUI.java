package front_end;

import back_end.Cell;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUI
{
	private BorderPane root;
	private int sceneWidth, sceneHeight;
	private Rectangle[][] grid;
	private ControlPanel panel;
	
	public GUI (int sceneWidth, int sceneHeight)
	{
		this.sceneWidth = sceneWidth;
		this.sceneHeight = sceneHeight;
		root = new BorderPane();
	}
	
	public Scene setScene()
	{
		Scene myScene = new Scene(root, sceneWidth, sceneHeight, Color.WHITE);
		panel = new ControlPanel(root);
		return myScene;
	}
	
	public void initGrid(int gridHeight, int gridWidth)
	{
		grid = new Rectangle[gridHeight][gridWidth];
		Group gridContainer = new Group();
		root.setCenter(gridContainer);
		int cellSize = Math.min((sceneWidth-100)/gridWidth, (sceneHeight-100)/gridHeight);
		
		for (int x = 0; x < gridHeight; x++)
		{
			for (int y = 0; y < gridWidth; y++)
			{
				grid[x][y] = new Rectangle(cellSize, cellSize);
				grid[x][y].setX(y*cellSize);
				grid[x][y].setY(x*cellSize);
				grid[x][y].setStroke(Color.BLACK);
				gridContainer.getChildren().add(grid[x][y]);
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
				grid[x][y].setStroke(Color.BLACK);
			}
		}
	}
	public void initPPSButtons(Runnable playMethod, Runnable pauseMethod, Runnable stepMethod)
	{
		panel.setPlay(playMethod);
		panel.setPause(pauseMethod);
		panel.setStep(stepMethod);
		panel.setSlider();
	}

	public void initNewSimButton(Runnable newSimMethod)
	{
		panel.setNewSim(newSimMethod);
	}
}
