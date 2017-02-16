#cellsociety 
Cell Society Team 23: Juan Philippe, Ashka Stephen, Yuxiang He

### date you started, date you finished, and an estimate of the number of hours worked on the project
start: 1/26/17, end 2/13/17, estimated 180 man-hours worked

### each person's role in developing the project
Juan Philippe implemented front end, XML handling and configuration handling, and designed integration between front and back
Ashka Stephen worked on back end design, implemented error handling and several simulations
Yuxiang He implemented and explained (to the group) the functions of the major superclasses, built several simulations, and implemented the different configurations of cell shapes and border types.

### any books, papers, online, or human resources that you used in developing the project
-http://zool33.uni-graz.at/schmickl/Self-organization/Group_behavior/Slime_mold_behavior/slimemold_simple/slimemold_simple.html
- http://golly.sourceforge.net/Help/bounded.html
- http://www2.le.ac.uk/departments/interdisciplinary-science/research/the-sugarscape
- http://golly.sourceforge.net/Help/Algorithms/QuickLife.html#nontotal
- Other resources linked to on: http://www.cs.duke.edu/courses/compsci308/spring17/assign/02_cellsociety/part3_PL7.php


### files used to start the project (the class(es) containing main)
Main.java

### files used to test the project
all XML files in ./data

### any data or resource files required by the project (including format of non-standard files)
sim.properties, gameText.properties, one of the various XML files required to start a simulation

### any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
everything is done through the GUI

### any known bugs, crashes, or problems with the project's functionality
hitting cancel instead of selecting an XML file will cause the program to crash.

### your impressions of the assignment to help improve it in the future
More time would have been great to implement everything that was asked. Also, we could have designed data flow better to make it more expandable; example: instead of passing various parameters from simulationbuilder into a simulation object to then initialize a simulationinfo object, we should have just initialized the siminfo object in simulation builder. By the time we realized this, it was too difficult to change the code in every one of the the simulation subclasses.


### NOTES:
-SugarScape: General code implementation and basic structure is there. If given more time for this project, functionality would have been simple from here.
-SlimeModel: Basic implementation is there and simulation's initial state works. If given more time, debugging would lead to complete functionality. At this point it does not fully run but structure is easily extendable.
