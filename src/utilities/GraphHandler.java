package utilities;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.BorderPane;



public class GraphHandler {
	
	private BorderPane root;
	private HashMap<String, Series<Number, Number>> seriesContainer;
	private NumberAxis xAxis, yAxis;
	private LineChart<Number,Number> lineChart;
	int counter;
	

	public Scene buildScene()
	{
		root = new BorderPane();
		return new Scene(root, 400,300);
	}
	
	public void buildGraph(Grid grid)
	{
		counter = 0;
		xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(30);
        
        lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        root.setCenter(lineChart);
        
        seriesContainer = new HashMap<String, Series<Number, Number>>();
        for (String x : grid.getCellTypes())
        {
        	seriesContainer.put(x, new Series<Number, Number>());
        	lineChart.getData().add(seriesContainer.get(x));
        	seriesContainer.get(x).setName(x);
        }
	}
	
	public void renderGraph(Grid grid)
	{
		for (String x : seriesContainer.keySet())
		{
			seriesContainer.get(x).getData().add(new Data<Number, Number>(counter, grid.getCountByType(x)));
		}
		checkRange(grid);
		counter++;
	}

	private void checkRange(Grid grid)
	{
		if (seriesContainer.get(grid.getCellTypes().get(0)).getData().size() > 30)
		{
			xAxis.setLowerBound(seriesContainer.get(grid.getCellTypes().get(0)).getData().get(0).getXValue().doubleValue());
			xAxis.setUpperBound(counter);
			for (String x : seriesContainer.keySet())
			{
				seriesContainer.get(x).getData().remove(0);
			}
		}
		
	}

	
}
