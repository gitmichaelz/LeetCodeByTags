package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumIII_DataStructureDesign {
    /*Trade off: it's write heavy or find/read heavy?
        Prefer quick find or quick add
        1.HashMap solution: write O(1), read O(n)
        optimization:
        2.Two HashSets solution: write O(n), read O(1)
*/
    //这个题只用map会比用list + map慢点，因为遍历map更耗时
    /** Initialize your data structure here. */
    Map<Integer, Integer> map;//to store inters and their count
    List<Integer> list;//to store the unique integers
    public TwoSumIII_DataStructureDesign(){
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if(map.containsKey(number)){
            map.put(number, map.get(number) + 1);
        } else {
            map.put(number, 1);
            list.add(number);
        }
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(int first : list){
            int second = value - first;
            if((first == second && map.get(first) > 1) || (first != second && map.containsKey(second))){
                return true;
            }
        }
        return false;
    }


    //     Map<Integer, Integer> map;//to store inters and their count
//     public TwoSum(){
//         map = new HashMap<>();
//     }

//     /** Add the number to an internal data structure.. */
//     public void add(int number) {
//         map.put(number, map.getOrDefault(number, 0) + 1);
//     }

//     /** Find if there exists any pair of numbers which sum is equal to the value. */
//     public boolean find(int value) {
//         for(Map.Entry<Integer, Integer> e : map.entrySet()){
//             int first = e.getKey();
//             int second = value - first;
//             if((first == second && e.getValue() > 1) || (first != second && map.containsKey(second))){
//                 return true;
//             }
//         }
//         return false;
//     }

    /*
    Set<Integer> sum;
        Set<Integer> num;

        TwoSum(){
            sum = new HashSet<Integer>();
            num = new HashSet<Integer>();
        }
        // Add the number to an internal data structure.
    	public void add(int number) {
    	    if(num.contains(number)){
    	        sum.add(number * 2);
    	    }else{
    	        Iterator<Integer> iter = num.iterator();
    	        while(iter.hasNext()){
    	            sum.add(iter.next() + number);
    	        }
    	        num.add(number);
    	    }
    	}

        // Find if there exists any pair of numbers which sum is equal to the value.
    	public boolean find(int value) {
    	    return sum.contains(value);
    	}
       */

}
