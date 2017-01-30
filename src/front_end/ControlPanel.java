package front_end;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class ControlPanel
{
	private ToolBar bar;
	
	public ControlPanel()
	{
		bar = new ToolBar();
	}

	public void addPanel(Group root)
	{
		root.getChildren().add(bar);
	}
}
