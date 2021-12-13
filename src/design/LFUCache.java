package design;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    //这题细节太多，注意体会每一行code
    //both put and get are O(1) time.
    class Node {
        int key;
        int val;
        int frequency;
        Node pre;
        Node next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            frequency = 1;
        }
    }

    class LRUList {//a doubly linked list, to store nodes with same count, and new node will be added to head, remove node in O(1) time
        Node head;
        Node tail;
        int size = 0;// to check if cur list is empty or not, if empty and it's a LFU list, we need increase the min frequency/count
        LRUList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
            size  = 0;
        }

        void add(Node node) {
            node.next = head.next;
            node.next.pre = node;
            head.next = node;
            node.pre = head;
            size++;
        }

        void remove(Node node) {
            if(size == 0) return;
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }

        Node removeLast() {//this is to remove the LRU node from the tail, note that we need a return value, since we also need to remove the related key-val from cache
            if(size == 0) return null;
            Node lruNode = tail.pre;
            remove(lruNode);
            return lruNode;
        }
    }

    Map<Integer, Node> cache;
    Map<Integer, LRUList> frequencyMap;//frequency -> LRU nodes list
    int capacity;
    int min;//to track the min frequency so that we can remove the LFU node
    public LFUCache(int capacity) {
        cache = new HashMap<>();
        frequencyMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        update(node);
        return node.val;
    }

    public void put(int key, int val) {
        if(capacity == 0) return;
        Node node;
        if(cache.containsKey(key)) {
            node = cache.get(key);
            node.val = val;
            update(node);
        } else {//插入一个新的node, 不需要update
            if(capacity == cache.size()) {
                LRUList list = frequencyMap.get(min);//找出需要remove的node所在的list
                cache.remove(list.removeLast().key);//list remove, 并且cache也要remove!!!!!!!!!!!!!
            }
            min = 1;//reset min = 1 when we add new key-val pair
            node = new Node(key, val);
            cache.put(key, node);
            LRUList newList = frequencyMap.computeIfAbsent(node.frequency, k -> new LRUList());
            newList.add(node);
        }
    }
    //after we do get() or put() if key is alreayd exited, we alwasy do update
    private void update(Node node) {
        LRUList list = frequencyMap.get(node.frequency);
        list.remove(node);
        if(node.frequency == min && list.size == 0) {
            min++;
        }
        node.frequency += 1;
        list = frequencyMap.computeIfAbsent(node.frequency, k -> new LRUList());
        list.add(node);
    }
}
