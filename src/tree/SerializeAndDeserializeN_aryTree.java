package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */
public class SerializeAndDeserializeN_aryTree {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    //similar to serialize/deserialize binary tree, the only difference is we need to store the number of children for each node

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        preorder(sb, root);
        return sb.toString();
    }

    private void preorder(StringBuilder sb, Node root) {
        if(root == null){
            sb.append("# ");
            return;
        }
        sb.append(root.val).append(" ");
        sb.append(root.children.size()).append(" ");
        for(Node child : root.children) {
            preorder(sb, child);
        }
    }


    public Node deserialize(String data) {
        if(data.isEmpty()) return null;
        String[] nodes = data.split(" ");
        return preorder(nodes);
    }

    int idx = 0;
    private Node preorder(String[] nodes) {
        if(nodes[idx].equals("#")) {
            idx++;
            return null;
        }
        int val = Integer.valueOf(nodes[idx++]);
        int size = Integer.valueOf(nodes[idx++]);
        Node root = new Node(val, new ArrayList<>());
        while(size > 0) {
            root.children.add(preorder(nodes));
            size--;
        }
        return root;
    }
}
