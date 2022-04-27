package Google;


import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NestedListWeightSumII {
    //single pass BFS
    //add integers multiple times instead of multiplying by depth. (by going level by level and adding the unweighted sum
    // to the weighted sum after each level)
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.isEmpty()) return 0;
        Deque<List<NestedInteger>> queue = new LinkedList<>();
        queue.offer(nestedList);
        int unweightedSum = 0;
        int weightedSum = 0;
        while(!queue.isEmpty()){
            for(int size = queue.size(); size > 0; size--){
                nestedList = queue.poll();
                for(NestedInteger i : nestedList){
                    if(i.isInteger()){
                        unweightedSum += i.getInteger();
                    } else {
                        queue.offer(i.getList());
                    }
                }
            }
            weightedSum += unweightedSum;//add to weightedSum at each layer，so last layer's integer will be added repeatedly
        }
        return weightedSum;
    }
    /*
    public int depthSumInverse2(List<NestedInteger> nestedList) {
        int unweightedSum = 0;
        int weightedSum = 0;
        while(!nestedList.isEmpty()){
            List<NestedInteger> nextLevel = new ArrayList<>();
            for(NestedInteger i : nestedList){
                if(i.isInteger()){
                    unweightedSum += i.getInteger();
                } else {
                    nextLevel.addAll(i.getList());
                }
            }
            weightedSum += unweightedSum;
            nestedList = nextLevel;
        }
        return weightedSum;
    }
    */
}
