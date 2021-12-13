package design;

/**
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
 *
 * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
 *
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendarTwo class:
 *
 *     MyCalendarTwo() Initializes the calendar object.
 *     boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, true, true, true, false, true, true]
 */
public class MyCalendarII {
    //follow up of  I, 允许两个事件overlap
    //还是用binary search tree, or segment tree
    class Node {
        int start, end;
        boolean overlapped;
        Node left, right;
        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    Node root;
    public MyCalendarII() {
        root = null;
    }

    public boolean book(int start, int end) {
        if(canInsert(start, end, root)) {
            root = insertNode(start, end, root);
            return true;
        }
        return false;
    }

    private Node insertNode(int start, int end, Node root) {
        if(root == null) {
            root = new Node(start, end);
        } else if(end <= root.start) {
            root.left = insertNode(start, end, root.left);
        } else if(start >= root.end) {
            root.right = insertNode(start, end, root.right);
        } else {//overlap
            root.overlapped = true;
            int minStart = Math.min(start, root.start);
            int maxStart = Math.max(start, root.start);
            int minEnd = Math.min(end, root.end);
            int maxEnd = Math.max(end, root.end);
            if(minStart < maxStart) {
                root.left = insertNode(minStart, maxStart, root.left);
            }
            if(minEnd < maxEnd) {
                root.right = insertNode(minEnd, maxEnd, root.right);
            }
            root.start = maxStart;//root overlap的部分为minStart何minEnd之间
            root.end = minEnd;
        }
        return root;
    }

    private boolean canInsert(int start, int end, Node root) {
        if(start >= end) return true;//这里一定要理解！！！看本方法最后注释，its possible start >= root.start 或者 root.end >= end.root内部无overlap，所以可以插入。
        if(root == null) return true;
        if(end <= root.start) {//check left subtree
            return canInsert(start, end, root.left);
        } else if(start >= root.end) {//check right subtree
            return canInsert(start, end, root.right);
        } else {//start, end have overlap with root
            if(root.overlapped) {//cur node already overlapped
                return false;
            }
            if(start >= root.start && end <= root.end) {//start, end is inside root.start/end
                return true;
            } else {//since [root.start, root.end] has no overlap, we dont need to check this range, we only need to check[start, root,start] and[root.end, end]
                return canInsert(start, root.start, root.left) && canInsert(root.end, end, root.right);
            }
        }
    }
}
