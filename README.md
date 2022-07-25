# SlidingBlockPuzzle
A program that solves puzzles of the sliding block variety.

Sliding block puzzles consist of a surface of n x m squares, and holding n x m -1 tiles.
The puzzle is solved by sorting the tiles in the correct order.
This is done by moving tiles into the empty space in a sequence that ends in the correct pattern.
This program will accept a 2D array of digits in randomized order, with one empty space, which will then be sorted into the correct pattern.
The algorithm will measure its performance with respect to how many steps are required to reach the goal state (Correct Pattern) as well as the rate of success it has at finding a solution when one exist.
The algorithm uses a simple A* search algorithm and also utilizes hashing to avoid duplicating moves.

