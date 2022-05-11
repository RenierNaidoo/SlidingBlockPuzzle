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
    This Function creates and returned a randomized 2D array that contains the tiles ranging 
    from 1 to 15, with a 0 representing the impty block.
     */
    public static int[][] CreateArray(int rows, int cols) {
        int[][] slidingBlockPuzzle = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                slidingBlockPuzzle[i][j] = (rows * cols) + 1;
            }
        }
        boolean isNewInt = false;
        int toAdd;
        int toCheck;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (!isNewInt) {
                    toAdd = (int) (((Math.random()) * 16) + 1);
                    isNewInt = NewNumber(toAdd, slidingBlockPuzzle);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(slidingBlockPuzzle[i][j]==16){
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
    This function accepts the initial 2D array and solves it on a step-by-step basis.
     */
    public static void SolvePuzzle(int[][] slidingBlockPuzzle) {

    }

    /*
    This function prints the contents of a 2D array to the output screen.
     */
    public static void PrintArray(int[][] slidingBlockPuzzle) {
        
    }

    public static void main(String[] args) {
        
    }

}
