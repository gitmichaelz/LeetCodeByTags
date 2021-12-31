package OOD.hashTable;

public class MyHashTable<K, V>{
    private class ListNode<K, V> {
        ListNode<K, V> next;
        ListNode<K, V> pre;
        K key;
        V value;
        ListNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    private ListNode<K, V>[] arr;
    public MyHashTable(int capacity) {
        //create list of linked list
        arr = new ListNode[capacity];
    }

    //insert key and value into hash table
    //return the pre value associated with key
    public V put(K key, V value) {
        ListNode<K, V> node = getNodeForKey(key);
        if(node != null) {
            V oldValue = node.value;
            node.value = value;//just update the value
            return oldValue;
        }

        node = new ListNode<K, V>(key, value);
        int idx = getIndexForKey(key);
        if(arr[idx] != null) {
            node.next = arr[idx];
            node.next.pre = node;
        }
        arr[idx] = node;
        return null;
    }

    //get value for key
    public V get(K key) {
        if(key == null) return null;
        ListNode<K, V> node = getNodeForKey(key);
        return node == null? null : node.value;
    }

    //remove node for key
    public V remove(K key) {
        ListNode<K, V> node = getNodeForKey(key);
        if(node == null) return null;

        if(node.pre != null) {
            node.pre.next = node.next;
        } else {
            //remove head
            int idx = getIndexForKey(key);
            arr[idx] = node.next;
        }

        if(node.next != null) {
            node.next.pre = node.pre;
        }
        return node.value;
    }

    //get listNode associated with a given key
    //return null if such node not found
    private ListNode getNodeForKey(K key) {
        int idx = getIndexForKey(key);
        ListNode<K, V> cur = arr[idx];
        while(cur != null) {
            if(cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public int getIndexForKey(K key) {
        return Math.abs(key.hashCode() % arr.length);
    }
}
