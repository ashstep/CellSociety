import front_end.GUI;
import back_end.Simulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilities.XMLReader;

public class Main extends Application
{
	public static final String TITLE = "Simulator";
	public static final int SIZE = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 20000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 20.0 / FRAMES_PER_SECOND;

	private Simulation simulation;

	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	public void start (Stage s)
	{	
		GUI container = new GUI(SIZE,SIZE);
		Scene scene = container.setScene();
		s.setScene(scene);
		s.setTitle(TITLE);
		s.show();

		XMLReader reader = new XMLReader();
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY, container));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		
		container.initButtons(
				() -> animation.play(),
				() -> animation.pause(),
				() -> 
				{
					if (animation.getStatus() == Animation.Status.PAUSED || animation.getStatus() == Animation.Status.STOPPED)
						step(SECOND_DELAY,container);
				},
				() ->
				{
						animation.pause();
						reader.chooseFile(s);
						simulation = reader.getSimulation();
						container.initGrid(simulation.getNumRows(), simulation.getNumCols());
						container.renderGrid(simulation.getGrid());
				});
	}    

	private void step (double elapsedTime, GUI inContainer)
	{
		inContainer.renderGrid(simulation.updateGrid());
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args)
	{
		launch(args);
	}
}