package tree;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 *
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 */
public class PopulatingNextRightPointersInEachNode {
    //tree的题 优先考虑recursive的做法，然后从local view的角度考虑
    public Node connect(Node root){
        if(root == null) return null;
        if(root.left != null) {//since its a perfect binary tree
            root.left.next = root.right;
            root.right.next = root.next == null? null : root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }




    //we use a variable leftmost to mark the head of each level, and connect its left right child
    // public Node connect(Node root){
    //     if(root == null) return null;
    //     Node leftmost = root;
    //     while(leftmost.left != null){//if leftmost have children, we connect its children
    //         Node head = leftmost;
    //         while(head != null){
    //             head.left.next = head.right;
    //             head.right.next = head.next == null? null : head.next.left;
    //             head = head.next;
    //         }
    //         leftmost = leftmost.left;
    //     }
    //     return root;
    // }





    //level order traversal, connect nodes at same level
    // public Node connect(Node root){
    //     if(root == null) return null;
    //     Deque<Node> queue = new LinkedList<>();
    //     queue.offer(root);
    //     while(!queue.isEmpty()) {
    //         int size  = queue.size();
    //         for(int i = 0; i < size; i++){
    //             Node node = queue.poll();
    //             if(i < size - 1){//we only connect first size nodes
    //                 node.next = queue.peek();
    //             }
    //             if(node.left != null) {
    //                 queue.offer(node.left);
    //             }
    //             if(node.right != null) {
    //                 queue.offer(node.right);
    //             }
    //         }
    //     }
    //     return root;
    // }
}
