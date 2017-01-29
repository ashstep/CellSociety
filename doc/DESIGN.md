Cell Society Design
---
By Juan Philippe, Ashka Stephen and Yuxiang He

## Introduction
In this project, we are building a Java program that animates any Cellular Automata simulation. Our primary goal is to provide a platform that runs different simulations. Our plan is such that by just adding classes specific to the simulation and stick to the APIs for the back end, one is able to easily run any possible CA simulation on our platform.

We have a closed implementation of abstract classes which are called by the main class to manipulate the back-end data. To add a new simulation simply create simulation related classes that extend the abstract classes, and add in the simulation specific stuff when necessary. Since the Main class only calls the methods in the abstract classes, as long as the new simulation’s implementation sticks to the abstract classes, the simulation can run on our back-end architecture.

The program will be most flexible in terms of simulation type and cell type, as these are the parameters that are most likely to change in the future. This will be accounted for by making the packages’ main class abstract and thus extendable to any kind of simulation and/or cell added to the project at a later date.

## Overview
The program overview consists of 3 main groups: back-end classes, front-end classes and the Main class (which will be in the default package).
The Cell Class will be abstract, as we know that the type of cell is dependent on the simulation being run. In order to keep the code flexible and extendable, it would be best to use an abstract class since cells will all contain the same basic properties but will contain additional properties that differentiate a cell in one simulation from a cell in another. Thus various kinds of cells will extend this class. Examples of some methods that may be included in the cell class are checkNeighbors() and takeAction(). However, the methods within specific cell classes which extend the abstract may or may not override these methods, depending on function.

Another abstract class would be the Cell Information. This class would contain information such as 1) the neighborhood information of a cell (off of which it will base its actions) and 2) where a displaced cell may go next (the potential next points that a cell could move if needed based on parameters such as neighbor information). This class is also best abstract because actions taken will depend on the cell type and simulation being run.

A third abstract class would be for Simulations (also a package, since each kind of simulation will have its own class). The package’s main simulation class will be abstract for the reasons mentioned above. Examples of simulation classes include the GameofLife and Wator.

The Grid class will be the last back-end class. Since only one grid is made (and what it represents is dependent on the simulation being run), the class does not need to be abstract. This is how the concept of multiple generations will be represented in the project, as the grid will update the cell information (2D array) based on which simulation is being run, which cells are being used, and what information is held in each cell.

The main front-end class will be the GUI package which is what the user sees and interacts with (included in this package will be classes such as InformationBanner, ControlPanel, etc.). This class is connected (through the main class) to the back-end Grid class; Grid will therefore pass information as it gets updated to the GUI class which will render it on the screen. This class may or may not be abstracted depending on how we want our final representation to work in terms of shapes, colors and other visual cues. Examples of methods that may be included in this class are buttonInitializer() and graphOutput(). Thus this class is a visual representation of the generation movement we are trying to represent. 

Overall, the back-end classes and front-end classes are tied together through the Main class which is located in the default package. The main class will be kept as small as possible to ensure that the code is flexible if, for example, additional simulations or cells are added. Some examples of methods in the Main include readXML(), getSimulation() and generateGrid().

![map](/data/Design_Images/one.png)

## User Interface
As can be seen by the diagrams below, there will be a few classes included in the GUI package that the user interacts with. The ControlPanel class will provide the information at the top of the screen for each simulation, which will include items such as buttons (such as pause, resume, speed up, slow down, step, etc) and labels for items read from the XML file such as the simulation name and author. These will most likely be placed in other classes (label and buttonInitializer classes). The main GUI class will contain references to all of these classes (as they will be in the same package).

![two](/data/Design_Images/two.jpg) ![three](/data/Design_Images/three.jpg)
![four](/data/Design_Images/four.jpg) ![five](/data/Design_Images/five.jpg)

Erroneous cases will be shown to users using pop-up windows (erroneous cases will be dealt with in a separate class that will be dealt with in week 2).. The following picture shows an example when the XML file specified by the user is not found. Other errors are shown in a similar manner.

![six](/data/Design_Images/six.jpg)

## Design Details 
#### Packages:
##### Main
- ###### `Main` class
    Interface between front-end and back-end
    Passes updated state from `Simulation`  to front-end every frame
    Passes user commands from front-end to back-end
		
##### Front end
- ###### `GUI` class-- to be refactored
    Contains `root` and `scene` for JavaFX
    Contains `ControPanel` and `InformationBanner` objects
	Renders everything onto the screen
- ###### `ControlPanel` class
    Buttons and functions to control the simulation
- ###### `InformationBanner` class
    Formats String to text objects to be put under root

##### Back end
- ###### Abstract `Simulation` class
	Contains simulation related attributes such as probability for catching fire
	Contains the `Grid` object
	A new `Simulation` object is created whenever a new simulation is started.
	It reads the XML file to set up the `Grid` and other simulation attributes
	Main calls setter methods in `Simulation` to change simulation attributes
	Returns a `Cell[][]` to `Main` when `Grid` is updated
 	
- ###### `Grid` class
	- `Cell[][];`
	- `updateCells();`
	- `getUpdatedCells();`

    will return `Cell[][]` to pass to `Simulation`

- ###### Abstract class `Cell`
	Contains current state of cell
	- Abstract `checkAndTakeAction();`
	   Checks neighbors and depending on the result, makes an action
	- Abstract `checkNeighbors(ArrayLisy<Cellnfo>);`
	    will take in information from neighbors assigned to it by Grid, then will update state accordingly
	- Abstract `takeAction();`


- ##### Sub-package [SimulationName] Pack
    - `[SimulationName]` part of the name changes according to the type of simulation
    - `[SimulationName] Simulation` extends `Simulation`
			Simulation object specific for [SimulationName]
	- `[SimulationName] SimulationAttribute`
			Contains attributes related to the simulation
	- `[SimulationName]Cell` extends `Cell`
			Contains behavior and information for the simulation cells for [SimulationName]. 		
    - `[SimulationName]CellInfo`
			Contains all info for each type of `Cell`
##### Utility classes
- `XMLReader` class reads the XML fils
- `ErrorHandler` class take actions depending on the error catched
 
		

#### Use cases
###### Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)

`GameOfLifeCell` inerited checkNeighbors(ArrayList<CellInfo>) method from Cell. Thus, it has its own implementation for that method. Thus, by iterating through the ArrayList the method checks the percentage of living neighbors and returns the result to checkAndMakeAction(); then checkAndMakeAction() calls makeAction() based on the checking result.


###### Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)

`GameOfLifeCell` inerited checkNeighbors(ArrayList<CellInfo> neighborInfo) method from Cell. Thus, it has its own implementation for that method. Thus, by iterating through the ArrayList the method checks the percentage of living neighbors and returns the result to checkAndMakeAction(); then checkAndMakeAction(ArrayList<CellInfo> neighborInfo) calls makeAction(result) based on the checking result. Since we have an ArrayList of cell info. The number of cells we are checking is very flexible and checkNeighbors(ArrayList<CellInfo> neighborInfo) will calculate the percentage based on ArrayList size. 

###### Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically

Simulation class will call updateGrid() method on its Grid object. This method returns an Cell[][] array to main. Main will make a new Rectangle[][] array based on the cell types and set the color accordingly. This Rectangle[][] array is passed to GUI class for rendering by JavaFX.

updateGrid() in the Grid class calls checkAndMakeAction(ArrayList<CellInfo> neighborInfo) on all the cells in the Cell[][] array.


For each cell checkAndMakeAction(ArrayList<CellInfo> neighborInfo) we will call makeAction(result) method and based on the checkNeighbors(ArrayList<CellInfo> neighborInfo)  `result` to determine if this cell will move to the next generation.

###### Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML file
In the GUI, the user will pick the TreeFire simulation, so the TreeFireSimulation subclass will be initialized and will take in the XML file. probCatch will be an instance field within TreeFireSimulation, and will be read in from the file upon initialization of the class.

###### Switch simulations: use the GUI to change the current simulation from Game of Life to Wator
The ControlPanel in the GUI will have a "Open new simulation" button which will then allow the use to pick a simulation, and then pick whether to use the default XML file or select a new one. A new Simulation subclass object will be initialized with the given XML file and will replace the current one in the main, at which point the GUI object will receive a different `Grid` object to process and render from.

## Design Considerations 
As a group we addressed how to make our code as extendable as foreseeably possible. After some discussion, we decided that the best course of action (in regards to various kinds of cells and simulations specifically) would be to make a Simulation and CellType package with the base code extendable so many different kinds can be accounted for. Abstract classes make the code much more flexible. The pros of this decision include the ability for the code to account for various kinds of items of the same base “type” and future extendibility. There would not be many cons to making the class abstract, besides not being able to create something of the abstract class itself, which does not seem like an issue since additional classes can always be added.
	
We also discussed at length how the back-end grid would pass information to the front-end GUI. We decided the best way to do this would be through the Main class, as this is what generally connects the back-end to the front-end. .

We also discussed at length the pros and cons of various backend classes and how they interact. The classes and what they do are discussed in detail in the overview section above. Here we will discuss how these classes interact with each other to provide overall functionality.

We debated the pros and cons of having the Cell class be able to see its neighbors, versus having the Grid class pass a list of neighbor Cells to each of its Cells. We decided that the position stuff will be done by the Grid class, because the work of Cell class should be limited to only 3:
- Contains information for itself
- Based on the information of its neighbors, make a decision on what to do
- Take action on the decision

As for collecting information for its neighbors, Grid has direct access to the Cell[][] array so it will be easier to know who are the cell’s neighbors through indexing. It will be much harder for the cell to know who its neighbors are since cell does not contain the Cell[][] array it is in. Thus, we leave the work to Grid class. “Tell the other guy” at work. The cons for this might be that if Grid’s code for determining neighbors is wrong, the information passed to all cells will be wrong, which is catastrophic to the whole simulation.

We also discussed the Simulation class. The Main class will read data in through the XML file (by means of another class) and the inputted data will determine what simulation class is called and set up. This is how the simulation package and main class will interact. From the simulation class the grid class will be called (which will deal with the information by receiving the information about each cell through each iteration passed on by the simulation class. The simulation class will also contain the information for the initial state of the cell since that is passed in through the XML file. We decided on this setup because it follows good coding principles and practice, as no one class is taking on jobs other than its own.


## Team Responsibilities
- ###### Simulation package
    Creation of the abstract class and subclasses within the package
Ashka and Yuxiang

- ###### UI GUI Class
    Juan, Yuxiang, Ashka

- ###### Grid Class
    Creation of the grid holding information which will be passed onto frontend
Juan and Yuxiang

- ###### Cells
    Creation of the abstract class and subclasses within the package
Ashka and Juan

High Level Plan:
We’ll all help each other as needed --lots of overlap in responsibilities.