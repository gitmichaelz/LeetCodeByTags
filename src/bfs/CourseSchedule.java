package bfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 *     For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 */
public class CourseSchedule {
    //BFS topological sort
    //build adjacent list for each course, use a array to store each course's indegree
    //find the course with indegree 0, which is the start course we need, enqueue
    //bfs, starting from the start course, for every of its children, if --indgegree == 0, enqueue, until queue is empty
    //during bfs, we count the number of courses finished, then return count == numCourse;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] children = new List[numCourses];//坑一。注意这个new的方式
        int[] indegree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            children[i] = new ArrayList<>();
        }
        for(int[] pair : prerequisites){
            children[pair[1]].add(pair[0]);//坑二，注意那个是prerequisite
            indegree[pair[0]]++;//这里也要注意，同上
        }
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0) {
                queue.offer(i);//find start courses
            }
        }
        //bfs from the start course, for every dependent course, we do indegree[x]--;if indegree[x] == 0. enqueue
        int count = 0;//number of courses finished
        while(!queue.isEmpty()){
            count++;
            int cur = queue.poll();
            for(int ready : children[cur]){
                if(--indegree[ready] == 0){
                    queue.offer(ready);
                }
            }
        }
        return count == numCourses;//check if we finished all courses
    }
}
