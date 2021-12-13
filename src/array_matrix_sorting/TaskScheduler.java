package array_matrix_sorting;

/**
 *Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 */
public class TaskScheduler {
    //https://leetcode.com/problems/task-scheduler/solution/
    //time: O(N), N is number of tasks, since count contains 26 elements, and all operations with it takes consitant time
    //space: O(1).
    // public int leastInterval(char[] tasks, int n){
    //     int[] count = new int[26];
    //     for(char c : tasks){
    //         count[c - 'A']++;
    //     }
    //     Arrays.sort(count);
    //     int max_count = count[25];
    //     int idle_time = (max_count - 1) * n;
    //     for(int i = count.length - 2; idle_time > 0 && i >= 0; i--){
    //         idle_time -= Math.min(max_count - 1, count[i]);//Math.min(max_count - 1, count[i]) 这里是避免count[i]== max_count的情况，如果是，则取max_count - 1
    //     }
    //     idle_time = Math.max(0, idle_time);//make sure idle_time non negative
    //     return tasks.length + idle_time;
    // }

    //https://leetcode.com/problems/task-scheduler/solution/ 见这个solution的讲解
    public int leastInterval(char[] tasks, int n){
        int[] count = new int[26];
        for(char t : tasks) {
            count[t - 'A']++;
        }

        int max_count = 0;//max frequency
        int num_max = 0;//the number of most frequency tasks
        for(int num : count){
            if(num == 0) continue;
            if(max_count < num){
                max_count = num;
                num_max = 1;
            } else if(max_count == num){
                num_max++;
            }
        }
        int slots = (n + 1) * (max_count - 1) + num_max;
        return Math.max(slots, tasks.length);//task.length的情况：A B C A D E A F G，即cooldown里面不能全放下其他需要执行次数更少的task时，例如F G，这时答案就是task.length
    }
}
