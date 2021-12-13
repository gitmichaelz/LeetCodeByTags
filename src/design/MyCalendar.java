package design;

/**
 * ou are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
 *
 * A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
 *
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendar class:
 *
 *     MyCalendar() Initializes the calendar object.
 *     boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * Output
 * [null, true, false, true]
 */
public class MyCalendar {
    //利用binary search tree的性质，插入需要O(logN), N次插入需要O(NlogN)
    class Node {
        int start, end;
        Node left, right;
        Node() {}
        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    Node root;
    public MyCalendar() {
        root = null;
    }

    public boolean book(int start, int end) {
        Node node = insert(root, start, end);
        if(node == null) return false;
        root = node;
        return true;
    }
    //if can insert, return the root after correctly insert a new node
    //else, return a null;
    private Node insert(Node root, int start, int end) {
        if(root == null) return new Node(start, end);
        if(root.start >= end) {
            Node left = insert(root.left, start, end);
            if(left == null) return null;
            root.left = left;
            return root;
        } else if(root.end <= start) {
            Node right = insert(root.right, start, end);
            if(right == null) return null;
            root.right = right;
            return root;
        }
        return null;//there is a conflict
    }
}
