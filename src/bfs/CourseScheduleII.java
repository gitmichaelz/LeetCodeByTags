package bfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
 * prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 *     For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of
 * them. If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the
 * correct course order is [0,1].
 */
public class CourseScheduleII {
    //similar to courseScheduleI
    //只需要在bfs时collect courses into result
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] children = new List[numCourses];
        int[] indegree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            children[i] = new ArrayList<>();
        }
        for(int[] pair : prerequisites){
            children[pair[1]].add(pair[0]);
            indegree[pair[0]]++;
        }
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        int[] res = new int[numCourses];
        int idx = 0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            res[idx++] = cur;
            for(int ready : children[cur]){
                if(--indegree[ready] == 0){
                    queue.offer(ready);
                }
            }
        }
        return idx == numCourses?res : new int[0];
    }
}
