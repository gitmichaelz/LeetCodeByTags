package design;

/**
 * Design a phone directory that initially has maxNumbers empty slots that can store numbers. The directory should store numbers, check if a certain slot is empty or not, and empty a given slot.
 *
 * Implement the PhoneDirectory class:
 *
 *     PhoneDirectory(int maxNumbers) Initializes the phone directory with the number of available slots maxNumbers.
 *     int get() Provides a number that is not assigned to anyone. Returns -1 if no number is available.
 *     bool check(int number) Returns true if the slot number is available and false otherwise.
 *     void release(int number) Recycles or releases the slot number.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["PhoneDirectory", "get", "get", "check", "get", "check", "release", "check"]
 * [[3], [], [], [2], [], [2], [2], [2]]
 * Output
 * [null, 0, 1, true, 2, false, null, true]
 */
public class DesignPhoneDirectory {
    class PhoneDirectory {
        int[] next;
        int pos;
        public PhoneDirectory(int maxNumbers) {
            next = new int[maxNumbers];
            for(int i=0;i<maxNumbers;i++){
                next[i]=(i+1)%maxNumbers;
            }
            pos=0;
        }

        public int get() {
            if(next[pos]==-1) return -1;
            int res=pos; //get cur idx //not next[pos]
            pos=next[pos]; //assign next idx to be cur
            next[res]=-1;  //set next[cur] =-1
            return res;
        }

        public boolean check(int number) {
            if(next[number]==-1) return false; //number is used
            return true;
        }

        public void release(int number) {
            if(next[number]!=-1) return; //not used, do nothing
            next[number]=pos;
            pos=number;
        }
    }

    /*
    class PhoneDirectory {
        class Node{
            int val;
            Node next;
            Node(int val) {
                this.val = val;
            }
        }
        Node head;
        Node dummy;
        //Initialize your data structure here
         //@param maxNumbers - The maximum numbers that can be stored in the phone directory.
        public PhoneDirectory(int maxNumbers) {
            dummy = new Node(0);
            dummy.next = head;
            Node cur = dummy;
            for(int i = 0; i < maxNumbers; i++) {
                cur.next = new Node(i);
                cur = cur.next;
            }
        }

        //Provide a number which is not assigned to anyone.
         //@return - Return an available number. Return -1 if none is available.
        public int get() {
            if(dummy.next == null) {
                return -1;
            } else {
                Node n = dummy.next;
                dummy.next = dummy.next.next;
                return n.val;
            }
        }

        // Check if a number is available or not.
        public boolean check(int number) {
            for(Node node = dummy.next; node != null; node = node.next) {
                if(number == node.val) return true;
            }
            return false;
        }

        // Recycle or release a number.
        public void release(int number) {
            Node node = dummy.next;
            Node pre = dummy;
            while(node != null && node.val != number) {
                node = node.next;
                pre = pre.next;
            }
            if(node == null){
                node = new Node(number);
                node.next = dummy.next;
                dummy.next = node;
            }
        }
    }
    */
}

