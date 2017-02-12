import front_end.GUI;

import java.util.function.Consumer;

import back_end.Simulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilities.GraphHandler;
import utilities.SimulationBuilder;

public class Main extends Application
{
	public static final String TITLE = "Simulator";
	public static final int WIDTH = 700;
	public static final int HEIGHT = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private Simulation simulation;
	private GraphHandler graphHandler;

	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	public void start (Stage s)
	{	
		GUI container = new GUI(WIDTH,HEIGHT); 
		setMainStage(s, container);
		
		container.initNewWinButton(() ->
		{
			(new Main()).start(new Stage());
		});

		SimulationBuilder builder = new SimulationBuilder();
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(container));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.setRate(container.getSpeedSliderValue());
		
		Consumer<Number> sliderFunction = (Number n) -> animation.setRate(n.doubleValue());
		container.initSpeedSlider(sliderFunction);
		
		createGraph();
		
		container.initNewSimButton(() ->
		{
			animation.pause();
			builder.chooseFile(s);
			simulation = builder.getSimulation();
			container.initSimParameterInterface(
					() -> animation.play(),
					() -> animation.pause(),
					() -> 
					{
						if (animation.getStatus() == Animation.Status.PAUSED || animation.getStatus() == Animation.Status.STOPPED)
							step(container);
					});
			
			container.initGrid(simulation.getGrid());
			initializeSimSliders(container);
			graphHandler.initGraph(simulation.getGrid());
		});
		
	}

	private void setMainStage(Stage s, GUI container)
	{
		s.setScene(container.buildScene());
		s.setTitle(TITLE);
		s.setX(0);
		s.setY(0);
		s.show();
	}

	private void createGraph()
	{
		Stage graphStage = new Stage();
		graphHandler = new GraphHandler();
		graphStage.setScene(graphHandler.buildScene());
		graphStage.setX(WIDTH);
		graphStage.setY(0);
		graphStage.show();
	}

	private void initializeSimSliders(GUI container)
	{
		container.clearSimSliders();
		for (String x : simulation.getParameterList())
		{
			container.createSimSlider(simulation.getChangeMethod(x), 
					x,simulation.getSliderLowerBound(x), simulation.getSliderUpperBound(x),
					simulation.getCurrentValue(x));
		}
	}

	private void step (GUI inContainer)
	{
		inContainer.renderGrid(simulation.updateGrid());
		graphHandler.renderGraph(simulation.getGrid());
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args)
	{
		launch(args);
	}
}