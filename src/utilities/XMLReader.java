package utilities;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import back_end.Simulation;
import back_end.Fire.FireSim;
import back_end.PredatorPrey.PredatorPreySim;
import back_end.Segregation.SegregationSim;
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
		if (doc.getDocumentElement().getAttribute("type").equals("Game of Life")) return createGameOfLifeSim();
		else if (doc.getDocumentElement().getAttribute("type").equals("Tree Fire")) return createFireSim();
		else if (doc.getDocumentElement().getAttribute("type").equals("Predator Prey")) return createPredatorPreySim();
		else if (doc.getDocumentElement().getAttribute("type").equals("Segregation")) return createSegregationSim();
		
		return null;
	}

	private Simulation createSegregationSim()
	{
		NodeList nList = doc.getElementsByTagName("threshold");
		int threshold = Integer.parseInt(nList.item(0).getTextContent());
		System.out.println(threshold);
		return new SegregationSim(createGrid(), threshold);
	}

	private Simulation createPredatorPreySim()
	{
		PredatorPreySim simulation = new PredatorPreySim(null, 0, 0, 0);
		
		return simulation;
	}

	private Simulation createFireSim()
	{
		//FireSim simulation = new FireSim(null); return simulation;
		return null;
	}

	private Simulation createGameOfLifeSim()
	{	
		return new GameOfLifeSim(createGrid());
	}
	
	private int[][] createGrid()
	{
		Scanner scanner;
		Node nNode;
		NodeList nList = doc.getElementsByTagName("row");

		int[][] testGrid = new int[getNumRows()][getNumCols()];
		for (int i = 0; i < getNumRows(); i++)
		{
			nNode = nList.item(i);
			scanner = new Scanner(nNode.getTextContent());
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