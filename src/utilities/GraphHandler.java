package utilities;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.BorderPane;



public class GraphHandler {
	
	private BorderPane root;
	private Series<Number, Number> series;
	int counter;
	

	public Scene buildScene()
	{
		root = new BorderPane();
		return new Scene(root, 400,300);
	}
	
	public void buildGraph()
	{
		counter = 0;
		NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        series = new XYChart.Series<Number,Number>();
        
        lineChart.getData().add(series);
        root.setCenter(lineChart);
	}
	
	public void renderGraph(Grid grid)
	{
		series.getData().add(new Data<Number, Number>(counter, grid.getNumEmptyCells()));
		counter++;
	}

	
}
