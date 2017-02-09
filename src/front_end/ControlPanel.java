package front_end;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.function.Consumer;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ControlPanel
{
	private Button play, pause, step, newSim, newWindow;
	private ToolBar bar;
	private Slider slider;
	private Properties appProps;
	private ArrayList<Node> simSliders;
	
	public ControlPanel(BorderPane inRoot)
	{
		bar = new ToolBar();
        inRoot.setTop(bar);
        simSliders = new ArrayList<Node>();
		
        appProps = new Properties();
		try {
			appProps.load(getClass().getClassLoader().getResourceAsStream("gameText.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pause = new Button(appProps.getProperty("pauseButton"));
		play = new Button(appProps.getProperty("playButton"));
		step = new Button(appProps.getProperty("stepButton"));
		newSim = new Button(appProps.getProperty("newSimButton"));
		newWindow = new Button(appProps.getProperty("newWinButton"));
		Text sliderLabel = new Text(appProps.getProperty("speedSliderLabel"));
		slider = new Slider(.005, .15, .05);
		
		bar.getItems().addAll(play,pause,step,newSim, newWindow, sliderLabel, slider);
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
	public void setNewWindow(Runnable r)
	{
		newWindow.setOnAction(new EventHandler<ActionEvent>()
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
	public void setSlider(Consumer<Number> r)
	{
		slider.valueProperty().addListener (
				(ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> r.accept(new_val));
	}

	public double getSliderDouble() 
	{
		return slider.getValue();
	}
	
	public void addSimSlider(Consumer<Number> r, String sliderName, double d, double e, double f)
	{
		Text sliderText = new Text(sliderName);
		Slider slider = new Slider();
		slider.setMin(d);
		slider.setMax(e);
		slider.setValue(f);
		slider.valueProperty().addListener (
				(ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> r.accept(new_val));
		
		bar.getItems().addAll(sliderText, slider);
		simSliders.add(slider);
		simSliders.add(sliderText);
	}
	public void clearSimSliders()
	{
		bar.getItems().removeAll(simSliders);
		simSliders.clear();
	}
}
