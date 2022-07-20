/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myprojects.slidingblockpuzzle;

/**
 *
 * @author rnaid
 */
public class SlidingBlockPuzzleUtils {

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
        int performanceCounter = 0; //Counts how many random numbers are checked during array creation.
        boolean isNewInt = false;
        int toAdd = 0;
        int toCheck;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (!isNewInt) {
                    toAdd = (int) (((Math.random()) * (rows*cols)) + 1);
                    performanceCounter++;
                    System.out.println("To Add: " + toAdd);
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
        System.out.println("Total Numbers Tested: " + performanceCounter);
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
                    counter++;
                }
            }
        }
        System.out.println("System Solved: " + isSolved);
        return isSolved;
    }
    
    /*
    This function accepts the initial 2D array and solves it on a step-by-step basis.
     */
    public static void SolvePuzzle(int[][] slidingBlockPuzzle) {
        //Make a move i.e., swap a tile into the empty spot
        
        //Check if puzzle is solved
        
        //Continue solving if still solved.
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
        int [][] slidingBlockPuzzle = CreateArray(3, 3);
        PrintArray(slidingBlockPuzzle);
        DetermineIfSolved(slidingBlockPuzzle);
    }

}
