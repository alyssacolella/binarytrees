
import java.util.List;
import java.util.ArrayList;

/**
 * @author alyssacolella
 * tui91984@temple.edu
 * BinarySearchTree class
 * Class with methods to manipulate binary search trees and 
 */

//BinarySearchTree is a subclass of BinaryTree and implements the interface SearchTree.

//The element class type E must implement the interface Comparable.
public class BinarySearchTree<E extends Comparable<E>>
        extends BinaryTree1<E>
        implements SearchTree<E> {
 
    //find and return the smallest value
    //public wrapper
    public E findSmallest() {
        return findSmallest(root);
    }
    //private recursive counterpart
    private E findSmallest(Node<E> parent) 
    {
        while(parent.left != null)
        {
            parent = parent.left;
        }
        return (parent.data);
    }

    //check if calling BST equals to bst2 in terms of content and structure
    //public wrapper
    public boolean equals(BinarySearchTree<E> bst2) 
    {
        return equals(this.root, bst2.root);
    }
    //private recursive counterpart
    private boolean equals(Node<E> currentRoot1, Node<E> currentRoot2) 
    {
        return equalData(currentRoot1, currentRoot2) && equalStruct(currentRoot1, currentRoot2);
    }
    
    public boolean equalData(Node<E> n1, Node<E> n2)
    {
        if(n1 != null && n2 != null)
        {
            if(n1.data != n2.data)
            {
                return false;
            }
            else
            {
                return equalData(n1.left, n2.left) && equalData(n1.right, n2.right);
            }
        }
        if(n1 == null && n2 == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //check if calling BST equals to bst2 in terms of structure only  
    //public wrapper
    public boolean equalStruct(BinarySearchTree<E> bst2) {
        return equalStruct(this.root, bst2.root);
    }
    //private recursive counterpart
    private boolean equalStruct(Node<E> root1, Node<E> root2) {
        if((root1 == null) && (root2 == null))
        {
            return true;
        }
        else if((root1 == null) && (root2 != null))
        {
            return false;
        }
        else if((root1 != null) && (root2 == null))
        {
            return false;
        }
        else
        {
            return equalStruct(root1.left, root2.left) && equalStruct(root1.right, root2.right);
        }
    }    
    
    //find and return the depth of the smallest value
    //public wrapper
    public int depthOfMinValueRecursive() 
    {
        return depthOfMinValueRecursive(root);
    }
    //private recursive counterpart
    private int depthOfMinValueRecursive(Node<E> localRoot) 
    {
        int depth = 0;
        if(localRoot == null)
        {
            System.out.println("Empty List.");
            return 0;
        }
        else if(localRoot.left == null)
        {
            return 1 + depth;
        }
        else
        {
            depth = 1 + depthOfMinValueRecursive(localRoot.left);
            return depth;
        }
    }

    // Data Fields
    /**
     * Return value from the public add method, indicating whether the item was
     * inserted or not.
     */
    protected boolean addReturn;
    /**
     * Return value from the public delete method, which is a reference to the
     * deleted item.
     */
    protected E deleteReturn;

    //Methods

    /**
     * Starter method find.
     *
     * @pre The target object must implement the Comparable interface.
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     */
    @Override
    public E find(E target) {
               //call the private recursive version to do the actual search,
        //  pasing the original root of this BST as the local root
        return find(root, target);
    }

    /**
     * Recursive find method.
     *
     * @param localRoot The local subtree's root (i.e. current root node)
     * @param target The object being sought
     * @return The object, if found, otherwise null
     */
    private E find(Node<E> localRoot, E target) {
        if (localRoot == null) {   //base case 1: target is not in this BST tree
            return null;
        }

        // Compare the target with the data at the current root node).
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) {     //base case 2: found the target in current root node
            return localRoot.data;
        } else if (compResult < 0) {                //target is smaller than the current local root data
            return find(localRoot.left, target);    //recursively search the left subtree of the current root
        } else {                                    //target is larger than the current local root data
            return find(localRoot.right, target);   //recursively search the right subtree of the current root
        }
    }
   
    /**
     * Starter method add.
     *
     * @pre The object to insert must implement the Comparable interface.
     * @param item The object being inserted
     * @return true if the object is inserted, false if the object already
     * exists in the tree
     */
    @Override
    public boolean add(E item) {
        //special case: empty tree can be handled here or inside the private recursive add(..)
        if (root == null) {
            root = new Node<E>(item);
            addReturn = true;
            return true;
        }

        //call the private recursive version to do the actual addition,
        //  pasing the original root of this BST as the local root,
        //assign the return value of the private add method to this BST's root.        
        root = add(root, item);
        return addReturn;
    }

    /**
     * Recursive add method.
     *
     * @post The data field addReturn is set true if the item is added to the
     * tree, false if the item is already in the tree.
     * @param localRoot The local root of the subtree (i.e. the current root)
     * @param item The object to be inserted
     * @return The new local root that now contains the inserted item
     */
    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree and insert it.
            addReturn = true;
            return new Node<E>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data, a duplicate, so cannot insert it
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data.
            // Try to recursively add the item to the left subtree of the local root.
            // Don't have to update localRoot.left at every return from private add.
            //  The update is used here mainly for tree balancing in Chapter 9.
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            // Try to recursively add the item to the right subtree of the local root.
            // Don't have to update localRoot.right at every return from private add.
            //  The update is used here mainly for tree balancing in Chapter 9.            
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Starter method delete.
     *
     * @post The object is not in the tree.
     * @param target The object to be deleted
     * @return The object deleted from the tree or null if the object was not in
     * the tree
     * @throws ClassCastException if target does not implement Comparable
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Recursive delete method.
     *
     * @post The item is not in the tree; deleteReturn is equal to the deleted
     * item as it was stored in the tree or null if the item was not found.
     * @param localRoot The root of the current subtree
     * @param item The item to be deleted
     * @return The modified local root that does not contain the item
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {  //1 child
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) { //1 child
                // If there is no right child, return left child.
                return localRoot.left;
            } else {        //2 children
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor, which is the largest in left subtree.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the local root data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with left child's left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }
    
    /**
     * Find the node that is the in order predecessor and replace it with its
     * left child (if any).
     *
     * @post The in order predecessor is removed from the tree.
     * @param parent The parent of possible in order predecessor (ip)
     * @return The data in the ip
     */
    private E findLargestChild(Node<E> parent) {
        // If the right child has no right child, then the right child is 
        // the inorder predecessor.
        if (parent.right.right == null) {
            //right child is theinorder predecessor
            E returnValue = parent.right.data;
            //remove the inorder predecessor by replacing it with its left child
            parent.right = parent.right.left;
            return returnValue;
        } else {
            //keep processing, starting with the right child.
            return findLargestChild(parent.right);
        }
    }
    /*</listing>*/

    //Other methods in the interface SearchTree
    //   to be implemented by Students.
    /**
     * Removes target from tree.
     *
     * @param target Item to be removed
     * @return true if the object was in the tree, false otherwise
     * @post target is not in the tree
     */
    public boolean remove(E target) {
        //to be implemented by Students
        return false;
    }

    /**
     * Determine if an item is in the tree
     *
     * @param target Item being sought in tree
     * @return true If the item is in the tree, false otherwise
     */
    public boolean contains(E target) {
        //to be implemented by Students
        return false;
    }
}

