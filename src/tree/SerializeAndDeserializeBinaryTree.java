package tree;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 */
public class SerializeAndDeserializeBinaryTree {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        return sb.toString();
    }
    private void dfsSerialize(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("* ");
        } else {
            sb.append(root.val).append(" ");
            dfsSerialize(root.left, sb);
            dfsSerialize(root.right, sb);
        }
    }

    public TreeNode deserialize(String data) {
        String[] vals = data.split(" ");
        return dfsDeserialize(vals);
    }
    int idx = 0;
    private TreeNode dfsDeserialize(String[] vals){
        String nodeVal = vals[idx];
        idx++;//访问完每个节点idx++;
        if(nodeVal.equals("*")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(nodeVal));
        root.left = dfsDeserialize(vals);
        root.right = dfsDeserialize(vals);
        return root;
    }

//     // Encodes a tree to a single string.
//     public String serialize(TreeNode root) {
//         if(root == null) return "";
//         StringBuilder sb = new StringBuilder();
//         Deque<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);
//         while(!queue.isEmpty()){
//             root = queue.poll();
//             if(root == null){
//                 sb.append("* ");
//             } else {
//                 sb.append(root.val).append(" ");
//                 queue.offer(root.left);
//                 queue.offer(root.right);
//             }
//         }
//         return sb.toString();
//     }

//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String data) {
//         if(data.isEmpty()) return null;
//         String[] vals = data.split(" ");
//         Deque<TreeNode> queue = new LinkedList<>();
//         TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
//         queue.offer(root);
//         for(int i = 1; i < vals.length; i++){
//             TreeNode parent = queue.poll();
//             if(!vals[i].equals("*")){
//                 parent.left = new TreeNode(Integer.valueOf(vals[i]));
//                 queue.offer(parent.left);
//             }
//             if(!vals[++i].equals(("*"))){
//                 parent.right = new TreeNode(Integer.valueOf(vals[i]));
//                 queue.offer(parent.right);
//             }
//         }
//         return root;
//     }
}
