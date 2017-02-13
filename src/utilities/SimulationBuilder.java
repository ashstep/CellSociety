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

	public Simulation getSimulation()
	{
		Simulation sim = null;
		if (doc.getDocumentElement().getAttribute("type").equals("Game of Life")) sim = createGameOfLifeSim();
		else if (doc.getDocumentElement().getAttribute("type").equals("Fire")) sim = createFireSim();
		else if (doc.getDocumentElement().getAttribute("type").equals("Predator Prey")) sim = createPredatorPreySim();
		else if (doc.getDocumentElement().getAttribute("type").equals("Segregation")) sim = createSegregationSim();
		else if (doc.getDocumentElement().getAttribute("type").equals("Slime")) sim = createSlimeSim();

		else throw new Error("Incorrect Simulation type");
		
		sim.setLines(configuration.getLineSetting());
		
		return sim;
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
			throw new Error("incorrect file");
		}
	}

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
	
	///////////////////////
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
	
		return new SlimeSim(createGrid(), createGroundGrid(),  probWiggle, wiggleAngle, sniffThreshold, sniffAngle);
	}
	///////////////////////

	
	
	
	
	private int[][] createGrid()
	{
		if (configuration.getGridBuilderType().equals("Data")) return createDataGrid();
		else if (configuration.getGridBuilderType().equals("Random")) return createRandomGrid();
		else if (configuration.getGridBuilderType().equals("Probability")) return createProbabilityGrid();
		else throw new Error("Incorrect grid builder config");
	}

	private int[][] createProbabilityGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	private int[][] createRandomGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	private int[][] createDataGrid() {
		Scanner scanner;
		NodeList nList = doc.getElementsByTagName("row");

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