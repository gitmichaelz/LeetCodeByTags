package tree;

/**
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 */
public class SerializeAndDeserializeBST {
    //preorder
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        dfs(sb, root);
        return sb.toString();
    }

    private void dfs(StringBuilder sb, TreeNode root) {
        if(root == null) return;
        sb.append(root.val).append(" ");
        dfs(sb, root.left);
        dfs(sb, root.right);
    }

    public TreeNode deserialize(String data) {
        if(data.isEmpty()) return null;
        String[] nodes = data.split(" ");
        return dfs(nodes, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //use low and upper boundary to check whether we should add null node
    //因为我们是preorder遍历，所以第一个符合[min, max]范围内的值就可以确定是上一层的第一个孩子。
    int idx = 0;
    private TreeNode dfs(String[] nodes, int min, int max) {
        if(idx == nodes.length) return null;//因为没有了BT那题’#‘的判断，所以我们这里必须要判断index == nodes.length
        int val = Integer.valueOf(nodes[idx]);
        if(val <= min || val >= max) return null;
        TreeNode root = new TreeNode(val);
        idx++;
        root.left = dfs(nodes, min, val);
        root.right = dfs(nodes, val, max);
        return root;
    }
}
