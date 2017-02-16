package front_end;

import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Grids.Grid;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import utilities.GridLocation;

/**class that handles the front end of the game. uses an instance of ControlPanel to take
 *care of buttons and sliders; uses a grid of shapes to handle rendering the grid object
 *as its passed in from the back end.
 *
 * 
 * @author Juan
 *
 */
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
				checkStroke(x, y, gridObject);
				setClickAction(x, y, gridObject);
				gridContainer.getChildren().add(grid[x][y]);
			}
		}
		renderGrid(gridObject);
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
	
	private void checkStroke(int x, int y, Grid gridObject)
	{
		if (gridObject.hasLines()) grid[x][y].setStroke(Color.BLACK);
	}

	private void createShape(int cellSize, int x, int y, Grid gridObject)
	{
		if (gridObject.getClass().toString().contains("Rectangle"))
		{
			buildRectangle(cellSize, x, y);
			return;
		}
		grid[x][y] = new Polygon();
		if (gridObject.getClass().toString().contains("Triangular"))
		{
			buildTriangle(cellSize, x, y);
		}
		else if (gridObject.getClass().toString().contains("Hexagonal"))
		{
			buildHexagon(cellSize, x, y);
		}
		else throw new Error("Incorrect shape");
	}

	private void buildHexagon(int cellSize, int x, int y)
	{
		double hexHeight = cellSize;
		double hexWidth = hexHeight*Math.sqrt(3)/2;
		double hexHeightOffset = Math.tan(Math.PI/6)*hexWidth*.5;

		if (x%2 == 0)
		{
			((Polygon) grid[x][y]).getPoints().addAll(new Double[]
					{
							y*hexWidth, x*(hexHeight-hexHeightOffset) + hexHeightOffset,
							y*hexWidth + hexWidth/2, x*(hexHeight-hexHeightOffset),
							y*hexWidth + hexWidth, x*(hexHeight-hexHeightOffset) + hexHeightOffset,
							y*hexWidth + hexWidth, x*(hexHeight-hexHeightOffset) + hexHeight - hexHeightOffset,
							y*hexWidth + hexWidth/2, x*(hexHeight-hexHeightOffset) + hexHeight,
							y*hexWidth, x*(hexHeight-hexHeightOffset) + hexHeight - hexHeightOffset,
					});
		}
		else
		{
			((Polygon) grid[x][y]).getPoints().addAll(new Double[]
					{
							y*hexWidth + hexWidth/2, x*(hexHeight-hexHeightOffset) + hexHeightOffset,
							y*hexWidth + hexWidth, x*(hexHeight-hexHeightOffset),
							y*hexWidth + hexWidth*1.5, x*(hexHeight-hexHeightOffset) + hexHeightOffset,
							y*hexWidth + hexWidth*1.5, x*(hexHeight-hexHeightOffset) + hexHeight - hexHeightOffset,
							y*hexWidth + hexWidth, x*(hexHeight-hexHeightOffset) + hexHeight,
							y*hexWidth + hexWidth/2, x*(hexHeight-hexHeightOffset) + hexHeight - hexHeightOffset,
					});
		}
	}

	private void buildTriangle(int cellSize, int x, int y)
	{
		if ((x%2==0 && y%2==0) || (x%2==1 && y%2==1))
		{
			((Polygon) grid[x][y]).getPoints().addAll(new Double[]
					{
							(double) (y*cellSize/2), (double) (x*cellSize + cellSize),
							(double) (y*cellSize/2 + cellSize/2), (double) (x*cellSize),
							(double)(y*cellSize/2 + cellSize), (double)(x*cellSize + cellSize)
					});
		}
		else
		{
			((Polygon) grid[x][y]).getPoints().addAll(new Double[]
					{
							(double) (y*cellSize/2), (double) (x*cellSize),
							(double) (y*cellSize/2 + cellSize), (double) (x*cellSize),
							(double)(y*cellSize/2 + cellSize/2), (double)(x*cellSize + cellSize)
					});
		}
	}

	private void buildRectangle(int cellSize, int x, int y)
	{
		grid[x][y] = new Rectangle(cellSize, cellSize);
		((Rectangle) grid[x][y]).setX(y*cellSize);
		((Rectangle) grid[x][y]).setY(x*cellSize);
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
}