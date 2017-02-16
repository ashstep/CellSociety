# Design
### Provide the high-level design goals of your project

Our project is to design a software that simulates Cellular Automata. The specifications of this project require us to implement 8 different kind of CA simulations.  In addition, we are required to make the design as flexible as possible, so that we can implement new kind of simulation specifications easily.

One design goal was to make the code as readable and easy to follow as possible. By carefully naming our methods to be precise and using javadoc commenting methods, we ensured that both classes and methods were defined well and only performed tasks that they needed to (thus not handling extraneous and unrelated information). Furthermore, the way in which we documented our general classes and packages closely modeled the structure of existing Java Documentation thus resulting in clean and readable overall code. We also made classes and subclasses as short as possible in order to follow the “Pass it to the Other Guy” concept Professor Duvall assigned in reading (ensuring that each class dealt with only what it had to and that extraneous information was not passed between methods and classes needlessly).

Another design convention we followed was the creation and use of abstracted classes. We used this to not only organize code conceptually (as it is easy to see and understand how various parts work together) but to also abstract common methods that were performed repeatedly, thus avoiding duplicated code and specify the APIs for all simulation. In addition, abstracting methods allowed for the easy extension of code that already exists. For example, due to our creation of the Grid class (object that holds the information of cell interactions and updates to reflect the result of the interactions), Sprint 2’s implementation of the slime simulation was less difficult to implement in regards to the code we already had in place (simply created two grids of two different cells and their interactions, which passed information between each other). On the other hand, if we had used an existing structure like an ArrayList, we would be restricted as to what we could implement and what information could be passed on. This is an example of the extendability of our code in use.

### Explain, in detail, how to add new features to your project

Our code has been designed to be as extendable as possible. We followed many of the concepts covered during lectures, such as the Template design structure and the Builder design structure. The template method means that the skeleton algorithm was defined but implemented with more specifications in the extended classes so that duplicate code was not an issue (“evolutionary design”). This was one reason that adding new features to the code was relatively simple (as we discovered during Sprint 2 with added simulations). This structure was used in all abstracted classes. For example, simulations were implemented such that each simulation was a package which extended the base code from a few classes (`Cell`, `Simulation`, `SimulationInfo`, and `ActionByCell`).

To be more specific, each simulation has its own package in put repository. To implement a new simulation, create in the package the classes that extend the abstract classes: `Simulation.class`,  `Cell.class`, `SimulationInfo.class` and `ActionBySim.class` in the `backend` package.

`Simulation.class` contains APIs and other methods necessary for maintaining the grid and updating the grid. Our front end uses the APIs (especially `updateGrid`) to create and maintain the data for each simulation.

`Cell.class` represents the cell of the simulations. It contains abstract APIs that Simulation uses to maintain the `Grid` by doing things to the cells in the `Grid`.

`SimulationInfo.class` captures the “environmental” attributes of a simulation, for example `proCatch` in fire simulation. These attributes are common to all cells in a simulation, so we believe it is easier to maintain such informations by making it an object and an instance variable of each simulation class.

`ActionBySim.class` captures the information passed from a `Cell` to `Simulation` , indicating to `Simulation` on the kinds of actions the `Cell` intend to do to its neighbors. In our design, cells can only do actions on themselves. If a cell wants to do things to its neighbors, it needs to put its desired actions into an `ActionBySim` object to `Simulation` and `Simulation` does the things requested by the `Cell`.

The client must extend all 4 classes, and implement the abstract methods. After that, a new simulation is added to the project because our front-end code only calls the abstract APIs in the abstract classes. As long as the abstract APIs are fully implemented, our front end is able to work with the new simulation.

### Justify major design choices, including trade-offs (i.e., pros and cons), made in your project

One major design choice was using a Grid to hold the information for each Cell object rather than an existing data structure such as an Array (which was what we originally had planned to do). Initially, we began to switch over because Professor Duvall announced in lecture that we shouldn’t use an existing structure. However, after speaking to him about it in detail and discussing amongst ourselves, we realized that this, in fact, was a good design choice to go through with. 

- This allowed us to create multiple “layers” of cells interacting with each other (as can be seen in the slime simulation, for example) which would not have been as easily implemented if this structure had not used.

- Allowed us to pass one object and have the control of what information we are giving out and what information we are taking in (more overall control).

- In the third sprint we needed to implement different kind of grids (rectangular, triangular, hexagonal) and different kind of boundary conditions. Having different `Grid` classes allows different implementations of the grid such as getting neighbors and checking boundaries. Such implementations vary as the kind of grid changes.

This ended up being a good decision in the long run because it allowed us to make additional grids that interacted with each other to a degree that would not have been possible with an existing data structure. There were not many cons to this design implementation except (if anything) mild confusion as to why new object implementation was necessary. However, after Sprint 2 those doubts were assuaged.

Another design choice we made was which classes to abstract. For example, as mentioned earlier we abstracted the Cell, SimInfo, Simulation and ActionBySim since this was what all simulations had in common. Similarly, within various simulation classes such as PredatorPrey and SlimeMold, classes that created these cells were abstracted to lead to a better code organization (abstracted methods were those that were common behaviors).

We also made the decision to have the Cells only do actions on themselves, not to other cells. This is because it is difficult for a cell to find its neighbors in a grid (it needs access to the grid which it is currently in). We left this part to the `Simulation` class. Simulation is able to do different things to the cells through the APIs of the `Cell.class`. This made the implementation of interaction between cells much easier because a cell can just tell Simulation what it wants to do to its neighbors or simply any cell in the Grid, and Simulation does the specified actions. Having access to the Grid, Simulation can easily carry out the actions. This made our code more flexible as in later simulations, cells can do things not only to its immediate neighbors, but also neighbors further away from them. Our code can easily implement those as `Simulation` has access to all cells with the APIs of the `Grid` class.

A design choice we made but could have improved on was the implementation of the SimInfo abstracted class in the Simulations package (rather than in the SimBuilder package). Currently, when a simulation is run, many different parameters have to be passed in (as can be seen in the current code). If SimInfo had been implemented in the other package, only one object would have to be passed in per a run of a simulation thus resulting in less written code and a more readable implementation than what we currently have. However this is an easy fix and would not be hard to correct if given more time.


### State any assumptions or decisions made to simplify or resolve ambiguities in your the project's functionality

One assumption that we made was in regards to the basic implementation or idea of what a “simulation” was in its essence. For example, we assumed all cells would perform the same basic functions, such as be affected by its neighbors and move (as can be seen by our abstracted Cell methods, such as checkAndTakeAction() and createCell()). If the definition of what a “cell” is were to change so that it would not take action based on surrounding cells then our implementation would need to be made more flexible. These assumptions, however, were okay to make since we understood what a simulation entailed in terms of this project. 

Another assumption we made was in regards to what the data would be read from (an XML file). This was another assumption that was okay to make because it was given as such in the specifications, however if given more time and the possibility of inputs from other sources, we could make our code more flexible to deal with other styles and sources of input.

Furthermore, we assumed that the current state of the cell would only depend on its previous state (and thus we only made 1 copy of the cell as can be seen in the abstracted implementation of Cell). However, if a simulation were to be give where this was not the case (say a cell’s next state would depend on a state a few iterations ago), the current model would need to be extended to account for this. 

Another assumption is that we thought all cells in many (though not all) simulations move in the grid. Thus `ActionBySim` abstract class contains implementation about signaling if the cell wants to move. However in some simulations e.g. GameOfLife, the cells do not move at all. We would argue that putting the moving stuff in `ActionBySim` abstract class is a good one. Because in very few simulations do the cells not move. Thus we can eliminate a lot of duplicated code. For those simulations where the cells do not move, we can just remember not to call the APIs related to moving.

Overall, these assumptions did not affect the current implementation but it would make our code more flexible to deal with these if given more time and less constraints.
