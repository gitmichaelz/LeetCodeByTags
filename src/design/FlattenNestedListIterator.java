package design;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *
 *     NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 *     int next() Returns the next integer in the nested list.
 *     boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 *
 * Your code will be tested with the following pseudocode:
 *
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 *     append iterator.next() to the end of res
 * return res
 *
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 *
 *
 * Example 1:
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 */
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
public class FlattenNestedListIterator{

    class NestedIterator implements Iterator<Integer> {

        //关于这题用stack还是用queue的讨论，详见https://leetcode.com/problems/flatten-nested-list-iterator/discuss/80212/For-the-interview-is-it-better-to-use-the-constructor-to-construct-the-entire-flattened-list-first-or-no
        //这题最好用stack做，不建议在构造函数里一次将整个List全部 flatten然后存储在queue里
        //"Since you are implementing an Iterator, so you shouldn't pre-compute all the list, you should compute the needed information on the fly"

        //stack 方法要注意， 在调用next时，先要调用hasNext防止出现exception, 因为stack里的元素有可能integer有可能是list
        //这种方式比较lazy,也就是stack里并不是存储所有的integer,而是integer和未flattern的list, 当stack顶部元素不是list是
        //再进行flattern,
        Deque<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new ArrayDeque<>();
            flattenList(nestedList);
        }

        //push to stack backwards, since stack is LIFO
        private void flattenList(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return hasNext() ? stack.pop().getInteger() : null;
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                if (stack.peek().isInteger()) return true;
                flattenList(stack.pop().getList());//注意这里不要忘记是getList()
            }
            return false;
        }


        /*
        Deque<Integer> queue;
        public NestedIterator(List<NestedInteger> nestedList) {
            queue = new ArrayDeque<>();
            flattenList(nestedList);
        }

        private void flattenList(List<NestedInteger> nestedList){
            for(NestedInteger ni : nestedList) {
                if(ni.isInteger()) {
                    queue.offer(ni.getInteger());
                } else {
                    flattenList(ni.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return queue.poll();
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
        */
    }
}