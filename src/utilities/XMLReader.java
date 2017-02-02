package utilities;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import back_end.Simulation;
import back_end.Fire.FireSim;
import back_end.PredatorPrey.PredatorPreySim;
import back_end.gameOfLifePack.GameOfLifeSim;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class XMLReader
{
	private FileChooser fileChooser;
	private Document doc;

	public XMLReader ()
	{
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("XML Files", "*.xml"));
		fileChooser.setInitialDirectory(new File("./data"));
	}

	public Simulation getSimulation()
	{
		if (doc.getDocumentElement().getAttribute("type").equals("Game of Life"))
		{
			System.out.println("game of life file selected");
			return createGameOfLifeSim();
		}
		else if (doc.getDocumentElement().getAttribute("type").equals("Tree Fire"))
		{
			System.out.println("Tree Fire file selected");
			return createFireSim();
		}
		else if (doc.getDocumentElement().getAttribute("type").equals("Predator Prey"))
		{
			System.out.println("Predator Prey file selected");
			return createPredatorPreySim();
		}
		else if (doc.getDocumentElement().getAttribute("type").equals("Segregation"))
		{
			System.out.println("Segregation file selected");
			return createSegregationSim();
		}
		return null;
	}

	private Simulation createSegregationSim()
	{
		//SegregationSim simulation = new SegregationSim();
		
		//return simulation;
		return null;
	}

	private Simulation createPredatorPreySim()
	{
		PredatorPreySim simulation = new PredatorPreySim();
		
		return simulation;
	}

	private Simulation createFireSim()
	{
		FireSim simulation = new FireSim();
		
		return simulation;
	}

	private Simulation createGameOfLifeSim()
	{
		int[][] testGrid = new int[10][10];
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				testGrid[i][j] = j/4;
			}
		}
		GameOfLifeSim simulation = new GameOfLifeSim(testGrid);
		
		return simulation;
	}
	

	public void chooseFile(Stage s)
	{
		File selectedFile = fileChooser.showOpenDialog(s);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try 
		{
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(selectedFile);
			doc.getDocumentElement().normalize();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}