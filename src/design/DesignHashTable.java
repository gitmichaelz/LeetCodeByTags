package design;

/**
 * Design a HashMap without using any built-in hash table libraries.
 *
 * Implement the MyHashMap class:
 *
 *     MyHashMap() initializes the object with an empty map.
 *     void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 *     int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 *     void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 */
public class DesignHashTable {
    class MyHashMap {
        class ListNode {
            int key, val;
            ListNode next;

            ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        ListNode[] nodes;
        /** Initialize your data structure here. */
        public MyHashMap() {
            nodes = new ListNode[10000];
        }

        //given key, get its index in list
        private int getIndex(int key){
            return Integer.hashCode(key) % nodes.length;
        }

        //if we find a node which node.key == key, we return the node before it
        private ListNode find(ListNode node, int key){
            ListNode pre = null;
            while(node != null && node.key != key){
                pre = node;
                node = node.next;
            }
            return pre;
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int index = getIndex(key);
            if(nodes[index] == null){
                nodes[index] = new ListNode(-1, -1);//每一个bucket都是以一个dummy node开头
            }
            ListNode pre = find(nodes[index], key);
            if(pre.next == null){//no found, create one
                pre.next = new ListNode(key, value);
            } else {//found, update
                pre.next.val = value;
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int index = getIndex(key);
            if(nodes[index] == null) return -1;
            ListNode pre = find(nodes[index], key);
            return pre.next == null? -1 : pre.next.val;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int index = getIndex(key);
            if(nodes[index] == null) return;
            ListNode pre = find(nodes[index], key);
            if(pre.next != null){
                pre.next = pre.next.next;
            }
        }
    }
}
