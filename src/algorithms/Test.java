/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.Comparator;

/**
 *
 * @author Tobias
 */
public class Test
{
    public static void main(String[] args)
    {
        RedBlackBST<String, Integer> myTree = new RedBlackBST<String, Integer>(Comparator.naturalOrder());
        myTree.put("Q", 1); 
        myTree.put("A", 2);
        myTree.put("Z", 3);
        myTree.put("E", 4);
        myTree.put("D", 5);
        myTree.put("C", 6);
        System.out.println("Size: " + myTree.size() 
        + "\nget A: " + myTree.get("A") 
        + "\nget Z: " + myTree.get("Z"));
    }
}
