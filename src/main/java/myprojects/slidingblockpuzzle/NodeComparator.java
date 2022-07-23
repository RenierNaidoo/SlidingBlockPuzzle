/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myprojects.slidingblockpuzzle;

import java.util.Comparator;

/**
 *
 * @author Maxine Van Der Merwe
 */
public class NodeComparator implements Comparator<GameTree>{
    public NodeComparator(){
        
    }
    
    //Comparator to help priority queue order nodes based on manhattan distance
    //where less is better.
    public int compare(GameTree g1, GameTree g2){
        if(g1.manhattanDist < g2.manhattanDist){
            return -1;
        }else if(g1.manhattanDist > g2.manhattanDist){
            return 1;
        }
        return 0;
    }
}
