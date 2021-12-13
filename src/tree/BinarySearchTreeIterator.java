package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 *     BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 *     boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 *     int next() Moves the pointer to the right, then returns the number at the pointer.
 *
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 */
public class BinarySearchTreeIterator {
    //similar to inorder traversal, we maintain a stack, which hold all the left branches nodes
    Deque<TreeNode> stack;
    public BinarySearchTreeIterator(TreeNode root) {
        stack = new LinkedList<>();
        pushLeftNodes(root);
    }
    private void pushLeftNodes(TreeNode root){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    //The average time complexity of next() function is O(1) . As the next function can be called n times at most, and the number of right nodes in self.pushAll(tmpNode.right) function is maximal n in a tree which has n nodes, so the amortized time complexity is O(1).

    /*
    This is in fact average O(1) time. The while loop is misleading you to think it is not. Think about the number of times a node has been visited after iterating the whole tree. Each node has been visited twice. In some cases the while loop doesn't execute, so that node at that call is only visited once. Where does the other visit go? It goes to the while loop when you visit another node. That's why it's "average" O(1) time.
    */
    public int next() {
        TreeNode node = stack.pop();
        if(node.right != null){
            pushLeftNodes(node.right);
        }
        return node.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
