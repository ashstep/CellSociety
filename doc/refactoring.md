###Refactoring

####Offset arrays
Those arrays are isntance variables in each specific simulation, but they are common to all simulations, so they are refactored to `Simulation` abstract class as private variables. I added corresponding getters for teh arrays.

####getNeighbors
`getNeighbors` method is a property of the grid class. Since we are implementing different kind of grids, I moved it to specific `Grid` classes. `RectangleGrid` and `TriangularGrid` have different getNeighbor methods, which makes out neighbor checking much easier.