package bfs;

import java.util.*;

/**
 * You have a data structure of employee information, including the employee's unique ID, importance value, and direct subordinates' IDs.
 *
 * You are given an array of employees employees where:
 *
 *     employees[i].id is the ID of the ith employee.
 *     employees[i].importance is the importance value of the ith employee.
 *     employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
 *
 * Given an integer id that represents an employee's ID, return the total importance value of this employee and all their direct and indirect subordinates.
 *
 *
 *
 * Example 1:
 *
 * Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
 * Output: 11
 */

// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
}

public class EmployeeImportance {
    /*
        Time Complexity: O(N), where N is the number of employees. We might query each employee in dfs.

        Space Complexity: O(N), the size of the implicit call stack when evaluating dfs.

        */
    public int getImportance(List<Employee> employees, int id) {
        if(employees.isEmpty()) return 0;
        Map<Integer, Employee> map = new HashMap<>();
        for(Employee e : employees) {
            map.put(e.id, e);
        }
        return getImportance(map, id) ;
    }

    private int getImportance(Map<Integer, Employee> map, int id) {
        Employee cur = map.get(id);
        int importance = cur.importance;
        for(int subordinate : cur.subordinates) {
            importance += getImportance(map, subordinate);
        }
        return importance;
    }


    //BFS
    public int getImportanceBFS(List<Employee> employees, int id) {
        if(employees.isEmpty()) return 0;
        Map<Integer,Employee> map = new HashMap<>();
        for(Employee e : employees) {
            map.put(e.id, e);
        }
        Deque<Employee> queue = new ArrayDeque<>();
        queue.offer(map.get(id));
        int importance = 0;
        while(!queue.isEmpty()) {
            Employee cur = queue.poll();
            importance += cur.importance;
            for(int subordinate : cur.subordinates) {
                queue.offer(map.get(subordinate));
            }
        }
        return importance;
    }
}
