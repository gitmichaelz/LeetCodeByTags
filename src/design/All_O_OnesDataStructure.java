package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 *     AllOne() Initializes the object of the data structure.
 *     inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 *     dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 *     getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 *     getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 */
public class All_O_OnesDataStructure {
    class AllOne {
        //维护一个string 到 linkedNode的map,其中linkedNode为双向链表，他的值保证了从小到大
        class LinkedNode{
            int val;//the number of key
            Set<String> keys;//a collection of keys with same value
            LinkedNode pre;
            LinkedNode next;
            LinkedNode(int val, String key){
                this.val = val;
                keys = new HashSet<>();
                keys.add(key);
            }
        }

        LinkedNode head;
        LinkedNode tail;
        Map<String, LinkedNode> map;
        /** Initialize your data structure here. */
        public AllOne() {
            map = new HashMap<>();
            head = new LinkedNode(-1, "");
            tail = new LinkedNode(-1, "");
            head.next = tail;
            tail.pre = head;
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            if(map.containsKey(key)){
                LinkedNode node = map.get(key);
                int val = node.val + 1;
                if(node.next == tail || node.next.val != val){//只有node的下一个是尾部或者下一个的值不等于val(即node.val + 1)，我们才create一个新的节点
                    insertNodeAfter(node,  new LinkedNode(val, key));
                } else {
                    node.next.keys.add(key);
                }
                map.put(key, node.next);
                node.keys.remove(key);//前一个节点的key集合要删除key
                if(node.keys.isEmpty()){
                    removeNode(node);
                }
            }else {
                if(head.next == tail || head.next.val != 1){//只有head的下一个是尾部或者下一个的值不等于1，才create一个新的节点插入
                    insertNodeAfter(head, new LinkedNode(1, key));
                }else {
                    head.next.keys.add(key);
                }
                map.put(key, head.next);//不要忘记存到map里
            }
        }

        private void insertNodeAfter(LinkedNode node, LinkedNode newNode){
            newNode.next = node.next;
            newNode.pre = node;
            node.next = newNode;
            newNode.next.pre = newNode;
        }
        private void removeNode(LinkedNode node){
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if(!map.containsKey(key)) return;
            LinkedNode node = map.get(key);
            int val = node.val - 1;
            node.keys.remove(key);

            if(val > 0){
                if(node.pre == head || node.pre.val != val){//如果当前节点的上一个节点是head，或者上一个节点的值不等于val(即当前节点的val - 1),需要create一个新的节点
                    insertNodeAfter(node.pre, new LinkedNode(val, key));//注意这个是插入node.pre的后面
                } else {
                    node.pre.keys.add(key);
                }
                map.put(key, node.pre);
            }else {//val == 0, remove from map
                map.remove(key);
            }
            if(node.keys.isEmpty()){//注意，大坑。一定要在最后判断，如果在node.keys.remove(key)后面直接判断的话，以为我们下面还有insertNodeAfter的操作，会出错，所以一定要在最后操作这个
                removeNode(node);
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            return tail.pre.keys.iterator().next();
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            return head.next.keys.iterator().next();
        }
    }
}
