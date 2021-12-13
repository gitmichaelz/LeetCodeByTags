package tree;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 *     The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
 *     list and the left child pointer is always null.
 *     The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class FlattenBinaryTreeToLinkedList {
    //题目中已给提示生成的linkedlist是先序遍历。 网上有很多种不同的方式去做这题，但我觉得这种最直观。就是create一个全局变量pre
    // 让他pre-order遍历树，同时更改那些指针。
    TreeNode pre = null;
    public void flatten(TreeNode root){
        if(root == null) return;
        if(pre != null) {//since we are using pre to preorder traverse the tree, so we can make sure pre is visited
            pre.right = root;
            pre.left = null;
        }
        pre = root;
        TreeNode right = root.right;//why we need this? because after we flatten(root.left). root.right will be assigned new node
        flatten(root.left);
        flatten(right);
    }

    // TreeNode pre = null;
    // public void flatten(TreeNode root) {
    //     if(root == null) return;
    //     if(pre != null) {
    //         pre.right = root;
    //     }
    //     pre = root;
    //     TreeNode right = root.right;
    //     flatten(root.left);
    //     root.left = null;
    //     flatten(right);
    // }
}
