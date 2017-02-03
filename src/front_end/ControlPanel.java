package front_end;
import java.util.concurrent.Callable;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class ControlPanel
{
	private Button play, pause, step, newSim;
	private ToolBar bar;
	private Slider slider;
	
	public ControlPanel(BorderPane inRoot)
	{
		bar = new ToolBar();
		play = new Button("Play");
		pause = new Button("Pause");
		step = new Button("Step");
		newSim = new Button("New Simulation");
		
		bar.getItems().addAll(play, pause, step, newSim);
        inRoot.setTop(bar);
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

	public void setNewSim(Runnable r)
	{
		newSim.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0) {
				r.run();
			}
			
		});
	}
	public void setSlider()
	{
		slider = new Slider(50, 1000, 300);
		slider.valueProperty().addListener((
	            ObservableValue<? extends Number> ov, Number old_val, 
	            Number new_val) -> {
	            	
	        });
		bar.getItems().add(slider);
	}
	
}
