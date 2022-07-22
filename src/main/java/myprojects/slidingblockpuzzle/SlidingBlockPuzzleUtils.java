/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myprojects.slidingblockpuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author rnaid
 */
public class SlidingBlockPuzzleUtils {

    static int MAX_ATTEMPTS = 100;
    static boolean solved = false;
    static HashSet<String> checked;
    //performance values
    static int moveCounter;
    static double totalPuzzles;
    static double unsolvables;
    static double solves;
    static int totalMoves;

    /*
    This Function creates and returns a randomized 2D array that contains the tiles ranging 
    from 1 to 15, with a 0 representing the empty block.
     */
    public static int[][] CreateArray(int rows, int cols) {
        int[][] slidingBlockPuzzle = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                slidingBlockPuzzle[i][j] = (rows * cols) + 1;
            }
        }
        //int performanceCounter = 0; //Counts how many random numbers are checked during array creation.
        boolean isNewInt = false;
        int toAdd = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (!isNewInt) {
                    toAdd = (int) (((Math.random()) * (rows * cols)) + 1);
                    //performanceCounter++;
                    isNewInt = NewNumber(toAdd, slidingBlockPuzzle);
                }
                slidingBlockPuzzle[i][j] = toAdd;
                isNewInt = false;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (slidingBlockPuzzle[i][j] == rows * cols) {
                    slidingBlockPuzzle[i][j] = 0;
                }
            }
        }
        return slidingBlockPuzzle;
    }

    /*
    This function is called to check if a number is already present in the 2D array.
    It returns false if this is the case, else it returns true.
     */
    public static boolean NewNumber(int toCheck, int[][] slidingBlockPuzzle) {
        boolean isNewNumber = true;
        int rows = slidingBlockPuzzle.length;
        int cols = slidingBlockPuzzle[0].length;
        int current;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                current = slidingBlockPuzzle[i][j];
                if (toCheck == current) {
                    isNewNumber = false;
                }
            }
        }
        return isNewNumber;
    }

    /*
    This function calculates the manhattan distance when the given board is 
    compared to the goal state
     */
    public static int CalcManhattan(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int manhattan = 0;
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(counter < (rows*cols))
                {
                    int[] coordinates = FindTile(board, counter);
                    manhattan += Math.abs((coordinates[0] - i));
                    manhattan += Math.abs((coordinates[1] - j));
                    counter++;
                }
            }
        }
        return manhattan;
    }

    /*
    This function is called to test if the sliding block puzzle is solved
     */
    public static boolean DetermineIfSolved(int[][] slidingBlockPuzzle) {
        boolean isSolved = true;
        int rows = slidingBlockPuzzle.length;
        int cols = slidingBlockPuzzle[0].length;
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (slidingBlockPuzzle[i][j] != counter) {
                    isSolved = false;
                }
                if (counter == (rows * cols - 1)) {
                    counter = 0;
                } else {
                    counter++;
                }
            }
        }
        return isSolved;
    }

    /*
    Creates a deep copy of an array passed to it
     */
    public static int[][] DeepCopy(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] temp = new int[rows][cols];
        for (int i = 0; i < cols; i++) {
            temp[i] = Arrays.copyOf(board[i], rows);
        }
        return temp;
    }

    /*
    This function accepts the initial 2D array and solves it on a step-by-step basis.
    Returns a root node containing the series of steps used to solve the puzzle.
     */
    public static GameTree SolvePuzzle(GameTree root, int counter) {
        int[][] board = root.gameBoard;
        String boardString = ToString(board);
        checked.add(boardString);
        moveCounter++;
        int rows = board.length;
        int cols = board[0].length;
        //Check if puzzle is solved
        boolean isSolved = DetermineIfSolved(board);
        //Make a move i.e., swap a tile into the empty spot
        if (!isSolved && counter <= MAX_ATTEMPTS && !solved) {
            counter++;
            int[] coordinates = FindTile(board, 0);
            int x = coordinates[0];
            int y = coordinates[1];
            int[][] temp;
            temp = DeepCopy(board);
            if (x > 0) {
                temp[x][y] = temp[x - 1][y];
                temp[x - 1][y] = 0;
                boardString = ToString(temp);
                if (!checked.contains(boardString)) {
                    GameTree branch = new GameTree(temp, counter);
                    root.AddTree(branch);
                    SolvePuzzle(branch, counter);
                }
                temp = DeepCopy(board);
            }
            if (x < (rows - 1)) {
                temp[x][y] = temp[x + 1][y];
                temp[x + 1][y] = 0;
                boardString = ToString(temp);
                if (!checked.contains(boardString)) {
                    GameTree branch = new GameTree(temp, counter);
                    root.AddTree(branch);
                    SolvePuzzle(branch, counter);
                }
                temp = DeepCopy(board);
            }
            if (y > 0) {
                temp[x][y] = temp[x][y - 1];
                temp[x][y - 1] = 0;
                boardString = ToString(temp);
                if (!checked.contains(boardString)) {
                    GameTree branch = new GameTree(temp, counter);
                    root.AddTree(branch);
                    SolvePuzzle(branch, counter);
                }
                temp = DeepCopy(board);
            }
            if (y < cols - 1) {
                temp[x][y] = temp[x][y + 1];
                temp[x][y + 1] = 0;
                boardString = ToString(temp);
                if (!checked.contains(boardString)) {
                    GameTree branch = new GameTree(temp, counter);
                    root.AddTree(branch);
                    SolvePuzzle(branch, counter);
                }
            }
        } else if (isSolved) {
            solved = true;
            List<int[][]> output = new ArrayList();
            while (root != null) {
                output.add(root.gameBoard);
                root = root.previousNode;
            }
            System.out.println("Here follows the steps to solve the board.");
            for (int i = output.size() - 1; i >= 0; i--) {
                PrintArray(output.get(i));
                System.out.println("");
            }
        }
        return root;
    }

    /*
    This function determines the coordinates of the requested tile
     */
    public static int[] FindTile(int[][] board, int tile) {
        int[] coordinates = new int[2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == tile) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
            }
        }
        return coordinates;
    }

    /*
    This function creates a simple string representation to store in the hashset
     */
    public static String ToString(int[][] board) {
        String boardString = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boardString += board[i][j];
            }
        }
        return boardString;
    }

    /*
    This function prints the contents of a 2D array to the output screen.
     */
    public static void PrintArray(int[][] slidingBlockPuzzle) {
        for (int i = 0; i < slidingBlockPuzzle.length; i++) {
            for (int j = 0; j < slidingBlockPuzzle[0].length; j++) {
                System.out.print(slidingBlockPuzzle[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
    This function determines if a particular configuration is solvable, returns true
    or false as relevant.
     */
    public static boolean IsSolvable(int[][] board) {
        boolean isSolveable = false;
        //count inversions
        String boardString = ToString(board);
        int inversion = 0;
        for (int i = 0; i < boardString.length(); i++) {
            int first = Integer.parseInt(boardString.substring(i, i + 1));
            for (int j = i; j < boardString.length(); j++) {
                int second = Integer.parseInt(boardString.substring(j, j + 1));
                if (first > second && first != 0 && second != 0) {
                    inversion++;
                }
            }
        }
        //determine empty tile
        int[] coordinates = FindTile(board, 0);
        if (board.length % 2 != 0) //N is odd
        {
            if (inversion % 2 == 0) {
                isSolveable = true;
            }
        } else {
            if (coordinates[0] % 2 != 0 && inversion % 2 == 0) {
                isSolveable = true;
            } else if (coordinates[0] % 2 == 0 && inversion % 2 != 0) {
                isSolveable = true;
            }
        }
        return isSolveable;
    }

    public static void main(String[] args) {
        /*
        The below code runs a small simulation of the algorithm to determine
        its success rate and performance. All functions are tested by this code.
        There is no error checking for incorrect input.
         */
        totalPuzzles = 0;
        unsolvables = 0;
        solves = 0;
        totalMoves = 0;
        checked = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            solved = false;
            moveCounter = 0;
            int[][] slidingBlockPuzzle = CreateArray(2, 2);
            if (IsSolvable(slidingBlockPuzzle)) {
                GameTree root = new GameTree(slidingBlockPuzzle, 0);
                SolvePuzzle(root, moveCounter);
                if (solved) {
                    solves++;
                }
            } else {
                unsolvables++;
            }
            totalPuzzles++;
            checked.clear();
            totalMoves += moveCounter;
        }
        System.out.println("A total of " + totalPuzzles + " puzzles were attempted");
        System.out.println("A total of " + unsolvables + " puzzles were in unsolvable configs");
        System.out.println("A total of " + solves + " puzzles were solved");
        double remainder = totalPuzzles - unsolvables - solves;
        System.out.println("A total of " + remainder + " puzzles were attempted without a solution found");
        double successRate = ((solves / (totalPuzzles - unsolvables)) * 100);
        double averageMoves = totalMoves / totalPuzzles;
        System.out.println("The algorithm had a success rate of " + successRate + "%");
        System.out.println("The algorithm used an average of " + averageMoves + " moves per puzzle");
        
        //Unit test for Manhattan distance
        int[][] slidingBlockPuzzle = CreateArray(2, 2);
        PrintArray(slidingBlockPuzzle);
        System.out.println("The Manhattan distance is " + CalcManhattan(slidingBlockPuzzle));
    }

}
