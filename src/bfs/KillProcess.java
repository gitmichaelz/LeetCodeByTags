package bfs;

import java.util.*;

/**
 * You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.
 *
 * Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).
 *
 * When a process is killed, all of its children processes will also be killed.
 *
 * Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.
 */
public class KillProcess {
    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill){
        if(kill == 0) return pid;
        Map<Integer, Set<Integer>> map = new HashMap<>();//build a mapping from parent process to its children processes
        int size = pid.size();
        for(int i = 0; i < size; i++){
            Integer parent = ppid.get(i);
            map.putIfAbsent(parent, new HashSet<>());
            map.get(parent).add(pid.get(i));
        }
        List<Integer> res = new ArrayList();
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        while(!queue.isEmpty()){
            Integer parent = queue.poll();
            res.add(parent);
            if(map.containsKey(parent)){
                for(Integer child : map.get(parent)){
                    queue.offer(child);
                }
            }
        }
        return res;
    }
}
