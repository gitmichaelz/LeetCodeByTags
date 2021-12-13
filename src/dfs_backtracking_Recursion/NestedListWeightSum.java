package dfs_backtracking_Recursion;

import java.util.List;

/**
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 *
 * Return the sum of each integer in nestedList multiplied by its depth.
 *
 *
 *
 * Example 1:
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 */

  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
class NestedInteger {
      public NestedInteger(){}

      public NestedInteger(int vale) {}
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger(){return false;}

      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger() {return 0;}

      // Set this NestedInteger to hold a single integer.
      public void setInteger(int value) {};

      // Set this NestedInteger to hold a nested list and adds a nested integer to it.
      public void add(NestedInteger ni){};

      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList(){
          return null;
      }
  }

public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }
    private int helper(List<NestedInteger> nestedList, int depth){
        int sum = 0;
        for(NestedInteger i : nestedList){
            if(i.isInteger()){
                sum += i.getInteger() * depth;
            } else {
                sum += helper(i.getList(), depth + 1);
            }
        }
        return sum;
    }
}
