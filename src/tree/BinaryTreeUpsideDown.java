package tree;

/**
 * Given the root of a binary tree, turn the tree upside down and return the new root.
 *
 * You can turn a binary tree upside down with the following steps:
 *
 *     The original left child becomes the new root.
 *     The original root becomes the new right child.
 *     The original right child becomes the new left child.
 *
 * The mentioned steps are done level by level. It is guaranteed that every right node has a sibling (a left node with the same parent) and has no children.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5]
 * Output: [4,5,2,null,null,3,1]
 */
public class BinaryTreeUpsideDown {
    //两种方法都要掌握,尤其递归解法，要理解树的各指针转化规律，我们是用转换后的左子树的newRoot来作为新的根，而
    //原来根的左节点的左孩子更新为原来根的右孩子，原来根的左节点的右孩子更新为原来根
    //最后，不要忘记让原来root的左右孩子为null

    //https://leetcode.com/problems/binary-tree-upside-down/discuss/49406/Java-recursive-(O(logn)-space)-and-iterative-solutions-(O(1)-space)-with-explanation-and-figure
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        //新root(当前访问节点)的左孩子等于老root的右孩子， 新root的右孩子等于老root,
        TreeNode pre = null;//老root
        TreeNode right = null;//老root的右孩子
        TreeNode next = null;//下一个需要处理的root,为老root的左孩子，因为我们需要更新当前节点的左孩子，所以需要用一个next变量先存起来
        while(root != null){
            next = root.left;
            root.left = right;
            right = root.right;
            root.right = pre;
            pre = root;
            root = next;
        }
        return pre;
    }

    // public TreeNode upsideDownBinaryTree(TreeNode root){
    //     if(root == null || root.left == null) return root;
    //     TreeNode newRoot = upsideDownBinaryTree(root.left);
    //     root.left.left = root.right;
    //     root.left.right = root;
    //     root.left = null;
    //     root.right = null;
    //     return newRoot;
    // }
}
