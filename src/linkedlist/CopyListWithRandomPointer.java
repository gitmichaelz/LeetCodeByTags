package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 *     val: an integer representing Node.val
 *     random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 *
 * Your code will only be given the head of the original linked list.
 */
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyListWithRandomPointer {
    //这题掌握approach 1和2，面试估计会考察approach 2
    //approach 2
    //优化做法。time: O(N), space: O(1), without Map,
    //https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N) 看下面人的画图解答
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node node = head;
        //after first round we create copy for each node, link them together as 1 - 1' - 2 - 2' - 3 - 3' - 4 - 4'
        while(node != null) {
            Node next = node.next;
            node.next = new Node(node.val);
            node.next.next = next;
            node = next;
        }

        //this while loop assigns random pointer for the clone nodes
        node = head;
        while(node != null) {
            if(node.random != null) {//一定要判断
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        //this while loop restore the original list and extract the clone nodes
        node = head;
        Node cloneHead = node.next;
        Node clone = cloneHead;
        while(clone.next != null) {//注意这里是判断clone.next //clone是整个链的最后一个
            node.next = node.next.next;
            clone.next = clone.next.next;
            node = node.next;
            clone = clone.next;
        }
        node.next  = null;//不要忘记这一步，让node.next指向null
        return cloneHead;
    }


    //approach 1
    //BFS 经典做法 time: O(N), space: O(N)   经典做法
    public Node copyRandomListBFS(Node head) {
        if(head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        while(node != null) {//先建立所有节点映射。
            map.put(node, new Node(node.val));
            node = node.next;
        }
        for(Node old : map.keySet()) {
            Node clone = map.get(old);
            clone.next = map.get(old.next);
            clone.random = map.get(old.random);
        }
        return map.get(head);
    }



    //approach 3： dfs
//     public Node copyRandomList(Node head) {
//         Map<Node, Node> map = new HashMap<>();
//         return deepCopy(map, head);
//     }

//     private Node deepCopy(Map<Node, Node> map, Node head) {
//         if(head == null) return null;
//         if(!map.containsKey(head)) {
//             map.put(head, new Node(head.val));
//             map.get(head).next = deepCopy(map, head.next);
//             map.get(head).random = deepCopy(map, head.random);
//         }
//         return map.get(head);
//     }
}
