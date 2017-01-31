package front_end;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class ControlPanel
{
	private Button play, pause, step;
	private ToolBar bar;
	
	public ControlPanel(Group root)
	{
		bar = new ToolBar();
		play = new Button("Play");
		pause = new Button("Pause");
		step = new Button("Step");
		
		bar.getItems().addAll(play, pause, step);
		root.getChildren().add(bar);
	}

	public void setPause(Runnable r)
	{
		pause.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0) {
				r.run();
			}
		});
	}
	public void setStep(Runnable r)
	{
		step.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0) {
				r.run();
			}
			
		});
	}
	public void setPlay(Runnable r)
	{
		play.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0) {
				r.run();
			}
			
		});
	}
}
