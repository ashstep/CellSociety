package front_end;

import java.util.function.Consumer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Grid;

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
	
	public Scene buildScene()
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
	
	public void renderGrid(Grid cellGrid)
	{
		for (int x = 0; x <cellGrid.getNumRows(); x++)
		{
			for (int y = 0; y < cellGrid.getNumCols(); y++)
			{
				grid[x][y].setFill(cellGrid.getColorAt(x, y));
			}
		}
	}
	public void initSimParameterInterface(Runnable playMethod, Runnable pauseMethod, Runnable stepMethod)
	{
		panel.setPlay(playMethod);
		panel.setPause(pauseMethod);
		panel.setStep(stepMethod);
	}	
	public void initNewSimButton(Runnable newSimMethod)
	{
		panel.setNewSim(newSimMethod);
	}

	public void initSpeedSlider(Consumer<Number> r)
	{
		panel.setSlider(r);
	}
	public double getSpeedSliderValue()
	{
		return panel.getSliderDouble();
	}
	public void createSimSlider(Consumer<Number> r, String sliderName, double d, double e, double f)
	{
		panel.addSimSlider(r, sliderName, d, e, f);
	}
	public void clearSimSliders()
	{
		panel.clearSimSliders();
	}
}
