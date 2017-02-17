package utilities;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import back_end.Simulation;
import back_end.Fire.FireSim;
import back_end.GameOfLife.GameOfLifeSim;
import back_end.PredatorPrey.PredatorPreySim;
import back_end.Segregation.SegregationSim;
import back_end.slime.SlimeSim;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * 
 * @author Juan
 * 
 * Handles construction of the simulation based on the XML file and the config file
 *
 */
//This entire file is part of my masterpiece 
//JUAN PHILIPPE
public class SimulationBuilder
{
	private FileChooser fileChooser;
	private Document doc;
	private ConfigHandler configuration;

	public SimulationBuilder ()
	{
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("XML Files", "*.xml"));
		fileChooser.setInitialDirectory(new File("./data"));

		configuration = new ConfigHandler();
	}
	/**
	 * builds the simulation and returns it to the main to be accessed and updated
	 * @return
	 */
	public Simulation getSimulation()
	{
		Simulation sim = chooseSimType();
		sim.setLines(configuration.getLineSetting());

		return sim;
	}
	/**
	 * opens a window to pick an XML file to base the simulation on
	 * @param s
	 */
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
			throw new Error("incorrect file type");
		}
	}
	/**
	 * uses the XML file to decide the type of simulation to build
	 * @return
	 */
	private Simulation chooseSimType()
	{
		try
		{
			if (doc.getDocumentElement().getAttribute("type").equals("Game of Life")) return createGameOfLifeSim();
			else if (doc.getDocumentElement().getAttribute("type").equals("Fire")) return createFireSim();
			else if (doc.getDocumentElement().getAttribute("type").equals("Predator Prey")) return createPredatorPreySim();
			else if (doc.getDocumentElement().getAttribute("type").equals("Segregation")) return createSegregationSim();
			else if (doc.getDocumentElement().getAttribute("type").equals("Slime")) return createSlimeSim();
			else throw new Error("Incorrect Simulation type");
		}
		catch (Exception e)
		{
			throw new Error("invalid simulation parameters");
		}
	}
	/*
	 * the following methods retrieve the parameters of their particular simulation 
	 * and then build a simulation object based on the parameters
	 */
	private Simulation createSegregationSim()
	{
		NodeList nList = doc.getElementsByTagName("threshold");
		int threshold = Integer.parseInt(nList.item(0).getTextContent());
		return new SegregationSim(createGrid(), threshold, configuration.getBoundsType(), configuration.getShapeType());
	}

	private Simulation createPredatorPreySim()
	{
		NodeList nList = doc.getElementsByTagName("SharkBreedTime");
		int sharkBreedTime = Integer.parseInt(nList.item(0).getTextContent());

		nList = doc.getElementsByTagName("SharkStarveTime");
		int sharkStarveTime = Integer.parseInt(nList.item(0).getTextContent());

		nList = doc.getElementsByTagName("FishBreedTime");
		int fishBreedTime = Integer.parseInt(nList.item(0).getTextContent());

		return new PredatorPreySim(createGrid(), sharkBreedTime, sharkStarveTime, fishBreedTime
				, configuration.getBoundsType(), configuration.getShapeType());
	}

	private Simulation createFireSim()
	{
		NodeList nList = doc.getElementsByTagName("FireProbability");
		double probFire = Double.parseDouble(nList.item(0).getTextContent());

		return new FireSim(createGrid(), probFire, configuration.getBoundsType(), configuration.getShapeType());
	}

	private Simulation createGameOfLifeSim()
	{	
		return new GameOfLifeSim(createGrid(), configuration.getBoundsType(), configuration.getShapeType());
	}

	private Simulation createSlimeSim()
	{
		NodeList nList = doc.getElementsByTagName("wiggleAngle");
		int wiggleAngle = Integer.parseInt(nList.item(0).getTextContent());

		nList = doc.getElementsByTagName("probWiggle");
		double probWiggle = Double.parseDouble(nList.item(0).getTextContent());

		nList = doc.getElementsByTagName("sniffThreshold");
		int sniffThreshold = Integer.parseInt(nList.item(0).getTextContent());

		nList = doc.getElementsByTagName("sniffAngle");
		int sniffAngle = Integer.parseInt(nList.item(0).getTextContent());

		return new SlimeSim(createGrid(), createGroundGrid(),  probWiggle, wiggleAngle, sniffThreshold, sniffAngle, 
				configuration.getBoundsType(), configuration.getShapeType());
	}
	/**
	 * uses the configuration file to decide how to set the initial state of the grid
	 * @return
	 */
	private int[][] createGrid()
	{

		if (configuration.getGridBuilderType().equals("Data")) return createDataGrid();
		else if (configuration.getGridBuilderType().equals("Random")) return createRandomGrid();
		else if (configuration.getGridBuilderType().equals("Probability")) return createProbabilityGrid();
		else throw new Error("Incorrect grid builder config");
	}
	/*
	 * didn't have time to implement other two, but am showing how it would be done
	 */
	private int[][] createProbabilityGrid()
	{
		return null;
	}

	private int[][] createRandomGrid()
	{
		return null;
	}
	/**
	 * builds the grid based on the values from the XML file
	 * @return
	 */
	private int[][] createDataGrid() 
	{
		Scanner scanner;
		NodeList nList = doc.getElementsByTagName("row");

		int[][] testGrid = new int[getNumRows()][getNumCols()];
		try
		{
			for (int i = 0; i < getNumRows(); i++)
			{
				scanner = new Scanner(nList.item(i).getTextContent());
				for (int j = 0; j < getNumCols(); j++)
				{
					testGrid[i][j] = scanner.nextInt();
				}
			}
		}
		catch (Exception e)
		{
			throw new Error("simulation bounds error");
		}
		return testGrid;
	}
	/**
	 * does the same thing as creategrid, but is used by simulations that need a ground grid
	 * @return
	 */
	private int[][] createGroundGrid()
	{
		Scanner scanner;
		NodeList nList = doc.getElementsByTagName("grow");

		int[][] testGrid = new int[getNumRows()][getNumCols()];
		for (int i = 0; i < getNumRows(); i++)
		{
			scanner = new Scanner(nList.item(i).getTextContent());
			for (int j = 0; j < getNumCols(); j++)
			{
				testGrid[i][j] = scanner.nextInt();
			}
		}
		return testGrid;
	}
	/**
	 * these retrieve the bounds of the grid as defined in the XML file
	 * @return
	 */
	private int getNumRows()
	{
		NodeList nList = doc.getElementsByTagName("GridHeight");
		return Integer.parseInt(nList.item(0).getTextContent());
	}
	private int getNumCols()
	{
		NodeList nList = doc.getElementsByTagName("GridWidth");
		return Integer.parseInt(nList.item(0).getTextContent());
	}

}