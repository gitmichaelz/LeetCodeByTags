package tree;

/**
 * Given an array of unique integers preorder, return true if it is the correct preorder traversal sequence of a binary search tree.
 *
 * Example 1:
 *
 * Input: preorder = [5,2,1,3,6]
 * Output: true
 *
 * Example 2:
 *
 * Input: preorder = [5,2,6,1,3]
 * Output: false
 */
public class VerifyPreorderSequenceInBinarySearchTree {
    //use a stack to simulate the preorder traversal
    //if next node's value is less than stack.peek(), means we are still in the left subtree of all stack nodes
    //, so we keep pushing it onto the stack。
    //but if next node's value is larger than stack.peek(), means we have switched a right subtree, so we need to pop out all smaller nodes
    //in the mean time, we also need to use the poped values as a lower bound, and any new nodes in right subtree must larger than such nodes
    // public boolean verifyPreorder(int[] preorder){
    //     Deque<Integer> stack = new LinkedList<>();
    //     long low = Long.MIN_VALUE;
    //     for(int p : preorder) {
    //         if(low > p) return false;
    //         while(!stack.isEmpty() && p > stack.peek()){
    //             low = stack.pop();
    //         }
    //         stack.push(p);
    //     }
    //     return true;
    // }

    //follow up: same idea, but use preorder array as stack instead
    public boolean verifyPreorder(int[] preorder) {
        long low = Long.MIN_VALUE;
        int idx = -1;
        for(int p : preorder) {
            if(low > p) return false;
            while(idx >= 0 && p > preorder[idx]) {
                low = preorder[idx--];
            }
            preorder[++idx] = p;
        }
        return true;
    }
}
