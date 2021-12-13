package tree;

/**
 * Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.
 *
 * A grandparent of a node is the parent of its parent if it exists.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 */
public class SumOfNodesWithEvenValuedGrandparent {
    //traver down th tree and keep parent and grandparent
    //if cur node's grandparent's value is even, add the cur.val to res
    int sum = 0;
    public int sumEvenGrandparent(TreeNode root){
        traverse(root, null, null);
        return sum;
    }

    private void traverse(TreeNode root, TreeNode parent, TreeNode grandpa) {
        if(root == null) return;
        if(grandpa != null && grandpa.val % 2 == 0) {
            sum += root.val;
        }
        traverse(root.left, root, parent);
        traverse(root.right, root, parent);
    }
}
