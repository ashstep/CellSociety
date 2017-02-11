package front_end;

import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Grids.Grid;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import utilities.GridLocation;

public class GUI
{
	private BorderPane root;
	private int sceneWidth, sceneHeight;
	private Shape[][] grid;
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
	
	public void initGrid(Grid gridObject)
	{
		int gridHeight = gridObject.getNumRows();
		int gridWidth = gridObject.getNumCols();
		
		grid = new Shape[gridHeight][gridWidth];
		Group gridContainer = new Group();
		root.setCenter(gridContainer);
		int cellSize = Math.min((sceneWidth-100)/gridWidth, (sceneHeight-100)/gridHeight);
		
		for (int x = 0; x < gridHeight; x++)
		{
			for (int y = 0; y < gridWidth; y++)
			{
				createShape(cellSize, x, y, gridObject);
				grid[x][y].setStroke(Color.BLACK);
				setClickAction(x, y, gridObject);
				gridContainer.getChildren().add(grid[x][y]);
			}
		}
		renderGrid(gridObject);
	}

	private void createShape(int cellSize, int x, int y, Grid gridObject)
	{
		if (gridObject.getClass().toString().contains("Rectangle"))
		{
			grid[x][y] = new Rectangle(cellSize, cellSize);
			((Rectangle) grid[x][y]).setX(y*cellSize);
			((Rectangle) grid[x][y]).setY(x*cellSize);
		}
		
	}

	private void setClickAction(int x, int y, Grid gridObject)
	{
		grid[x][y].setOnMouseClicked(new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent t)
		    {
		        gridObject.nextState(new GridLocation(x,y));
		        renderCell(x, y, gridObject);
		    }
		});
	}
	private void renderCell(int x, int y, Grid gridObject)
	{
		grid[x][y].setFill(gridObject.getColorAt(x, y));
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
	public void initNewWinButton(Runnable r)
	{
		panel.setNewWindow(r);
	}
}