/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myprojects.slidingblockpuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rnaid
 */
public class SlidingBlockPuzzleUtils {
    
    static int MAX_ATTEMPTS = 15;
    static boolean solved = false;
    
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
        int toCheck;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (!isNewInt) {
                    toAdd = (int) (((Math.random()) * (rows*cols)) + 1);
                    //performanceCounter++;
                    isNewInt = NewNumber(toAdd, slidingBlockPuzzle);
                }
                slidingBlockPuzzle[i][j] = toAdd;
                isNewInt = false;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(slidingBlockPuzzle[i][j]==rows*cols){
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
    This function is called to test if the sliding block puzzle is solved
    */
    
    public static boolean DetermineIfSolved(int[][] slidingBlockPuzzle)
    {
        boolean isSolved = true;
        int rows = slidingBlockPuzzle.length;
        int cols = slidingBlockPuzzle[0].length;
        int counter = 1;
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(slidingBlockPuzzle[i][j] != counter)
                {
                    isSolved = false;
                }
                if(counter == (rows*cols - 1))
                {
                    counter = 0;
                }
                else
                {
                    counter++;
                }
            }
        }
        return isSolved;
    }
    
    /*
    This function accepts the initial 2D array and solves it on a step-by-step basis.
    Returns a root node containing the series of steps used to solve the puzzle.
     */
    public static GameTree SolvePuzzle(GameTree root, int counter) {
        int[][] board = root.gameBoard;
        int rows = board.length;
        int cols = board[0].length;
        //Check if puzzle is solved
        boolean isSolved = DetermineIfSolved(board);
        //Make a move i.e., swap a tile into the empty spot
        if(!isSolved && counter <= MAX_ATTEMPTS && !solved)
        {
            counter++;
            int[] coordinates = FindEmptyTile(board);
            int x = coordinates[0];
            int y = coordinates[1];
            int[][] temp = new int[rows][cols];
            for(int i = 0; i < cols; i++)
            {
                temp[i] = Arrays.copyOf(board[i], rows);
            }
            if(x > 0)
            {
                temp[x][y] = temp[x-1][y];
                temp[x-1][y] = 0;
                GameTree branch = new GameTree(temp, counter);
                root.AddTree(branch);
                SolvePuzzle(branch, counter);
                for(int i = 0; i < cols; i++)
                {
                    temp[i] = Arrays.copyOf(board[i], rows);
                }
            }
            if(x < (rows - 1))
            {
                temp[x][y] = temp[x+1][y];
                temp[x+1][y] = 0;
                GameTree branch = new GameTree(temp, counter);
                root.AddTree(branch);
                SolvePuzzle(branch, counter);
                for(int i = 0; i < cols; i++)
                {
                    temp[i] = Arrays.copyOf(board[i], rows);
                }
            }
            if(y > 0)
            {
                temp[x][y] = temp[x][y - 1];
                temp[x][y - 1] = 0;
                GameTree branch = new GameTree(temp, counter);
                root.AddTree(branch);
                SolvePuzzle(branch, counter);
                for(int i = 0; i < cols; i++)
                {
                    temp[i] = Arrays.copyOf(board[i], rows);
                }
            }
            if(y < cols - 1)
            {
                temp[x][y] = temp[x][y + 1];
                temp[x][y + 1] = 0;
                GameTree branch = new GameTree(temp, counter);
                root.AddTree(branch);
                SolvePuzzle(branch, counter);
                for(int i = 0; i < cols; i++)
                {
                    temp[i] = Arrays.copyOf(board[i], rows);
                }
            }
        }
        else if(isSolved)
        {
            solved = true;
            List<int[][]> output = new ArrayList();
            while(root != null)
            {
                output.add(root.gameBoard);
                root = root.previousNode;
            }
            System.out.println("Here follows the steps to solve the board.");
            for(int i = output.size() - 1; i >=0; i--)
            {
                PrintArray(output.get(i));
                System.out.println("");
            }
        }
        return root;
    }
    
    /*
    This function determines the coordinates of the 0/empty tile
    */
    public static int[] FindEmptyTile(int[][] board)
    {
        int[] coordinates = new int[2];
        for(int i = 0; i< board.length; i++)
        {
            for(int j = 0; j< board[0].length; j++)
            {
                if(board[i][j] == 0)
                {
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
            }
        }
        return coordinates;
    }
    /*
    This function prints the contents of a 2D array to the output screen.
     */
    public static void PrintArray(int[][] slidingBlockPuzzle) {
        for(int i = 0; i < slidingBlockPuzzle.length; i++){
            for(int j = 0; j < slidingBlockPuzzle[0].length; j++){
                System.out.print(slidingBlockPuzzle[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        /*
        The below code is a small unit test for the CreateArray and
        PrintArray functions
        */
        int [][] slidingBlockPuzzle = CreateArray(2, 2);
        PrintArray(slidingBlockPuzzle);
        DetermineIfSolved(slidingBlockPuzzle);
        GameTree puzzle = new GameTree(slidingBlockPuzzle, 0);
        puzzle = SolvePuzzle(puzzle,0);
    }

}
