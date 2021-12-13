package hashtable;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 *     LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 *     int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *     void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 */
public class LRUCache {
    //如果要求支持所有类型，可以这样定义 class LRUCache<K, V>{ ... }....  class Node(K, V){ ... }
    class Node{
        int key;
        int val;
        Node pre;
        Node next;
        Node(int key, int val){
            this.key = key;
            this.val = val;
            pre = null;
            next = null;
        }
    }

    Node head;
    Node tail;
    Map<Integer, Node> cache;
    int capacity;
    public LRUCache(int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }
    //if not in cache, return -1
    //if in cache, move the node to head;(actually head's next, because our actual head is a dummy node "head")
    //return cache.get(key).val
    public int get(int key) {
        if(!cache.containsKey(key)){
            return -1;
        }
        //remove cur node, connect its pre to its next node
        Node cur = cache.get(key);
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;

        //move cur to head
        moveToHead(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;//这个特殊case需要处理一下。

        if(get(key) != -1){//note: when we call get(key), we move the key to the head, i.e. it is a most recently used node
            cache.get(key).val = value;
            return;
        }
        //if not in cache, we will insert to cache, before do that, we need to check the cache capacity, and remove the LRU node
        if(cache.size() == capacity){
            cache.remove(tail.pre.key);//that's why we need a key in the node!!!!!
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
        }

        Node insert = new Node(key, value);
        cache.put(key, insert);
        moveToHead(insert);
    }

    private void moveToHead(Node node){
        node.next = head.next;
        node.pre = head;
        head.next = node;
        node.next.pre = node;
    }
}
