package tree;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 *     Search for a node to remove.
 *     If the node is found, delete the node.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 */
public class DeleteNodeInABST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if(root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {//found node to be deleted
            if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            }
            //root with two children, replace root with the min node in right subtree, then delete min node
            root.val = findMin(root.right);
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private int findMin(TreeNode root){
        while(root.left != null) {
            root = root.left;
        }
        return root.val;
    }
}
