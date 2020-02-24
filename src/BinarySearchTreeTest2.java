
/**
 * @author alyssacolella
 * tui91984@temple.edu
 * BinarySearchTreeTest2 class
 * Class to create binary search trees and test the methods of the BinarySearchTree class
 */
public class BinarySearchTreeTest2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //create an empty binary search tree of Integer objects
        BinarySearchTree<Integer> bstNumbers = new BinarySearchTree<Integer>();
        int[] numbers = {45, 27, 15, 21, 36};
        
        //print the data and structure of the binary search tree bstNumbers
        System.out.println(bstNumbers.toString());        
        System.out.println("---------------------");
        
        //populate the binary search tree: bstNumbers
        for (int i = 0; i < 5; i++) {
            bstNumbers.add(numbers[i]); //add current number in the array numbers
            System.out.println("After adding " + numbers[i] + "==>\n");
            //display the data and structure of the tree bstNumbers after the insertion
            System.out.println(bstNumbers.toString());
            System.out.println("---------------------");
        }
        
        //use the bstNumbers to test methods:
        //findSmallest
        System.out.println("The smallest value in bstNumbers is: " + bstNumbers.findSmallest());
        
        //depthOfMinValueRecursive
        System.out.println("The depth of the minimum value in bstNumbers is: " + bstNumbers.depthOfMinValueRecursive());
        
        //create and populate 3 BSTs based on 3 integer arrays
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>();
        int[] numbers2 = {45, 27, 15, 21, 36};
       
        BinarySearchTree<Integer> bst3 = new BinarySearchTree<Integer>();
        int[] numbers3 = {50, 30, 20, 25, 32};
       
        BinarySearchTree<Integer> bst4 = new BinarySearchTree<Integer>();
        int[] numbers4 = {30, 20, 10, 5, 15};
        
        for(int i = 0; i < 5; i++)
        {
            bst2.add(numbers2[i]);
            bst3.add(numbers3[i]);
            bst4.add(numbers4[i]);
        }
       
        //use the 4 BSTs to test methods:
        
        //equals
        System.out.println( "bstNumbers and bst2 are equal in both data and structure: " + bstNumbers.equals(bst2));
        System.out.println( "bstNumbers and bst3 are equal in both data and structure: " + bstNumbers.equals(bst3));
        System.out.println( "bstNumbers and bst4 are equal in both data and structure: " + bstNumbers.equals(bst4));
        
        //equalStruct
        System.out.println( "bstNumbers and bst2 are equal in structure: " + bstNumbers.equalStruct(bst2));
        System.out.println( "bstNumbers and bst3 are equal in structure: " + bstNumbers.equalStruct(bst3));
        System.out.println( "bstNumbers and bst4 are equal in structure: " + bstNumbers.equalStruct(bst4));

    }    
}