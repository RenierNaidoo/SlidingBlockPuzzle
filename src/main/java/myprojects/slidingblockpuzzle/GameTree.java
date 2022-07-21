/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myprojects.slidingblockpuzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxine Van Der Merwe
 */

/*
This class is used to maintain a tree of boards which can then be printed if solved
*/
public class GameTree {
    public int[][] gameBoard;
    public GameTree previousNode;
    public List<GameTree> nextNodes;
    public int move;
    
    public GameTree(int[][] board, int m)
    {
        gameBoard = board;
        move = m;
        nextNodes = new ArrayList();
    }
    
    /*
    This function adds a node to the tree
    */
    public void AddTree(GameTree branch)
    {
        branch.previousNode = this;
        nextNodes.add(branch);
    }
}
