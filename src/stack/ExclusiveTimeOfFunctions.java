package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.
 *
 * Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.
 *
 * You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.
 *
 * A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.
 *
 * Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * Output: [3,4]
 */
public class ExclusiveTimeOfFunctions {
    //function call, 经典的用栈模拟，stack用来存储function ID
    //遇到新的function(即遇到start)，push to stack, 同时记录上一个function已经执行的时间res[fid] += curFunctionStart - lastFunctionStart
    //遇到function执行结束(即遇到end), pop out of stack.同时记录该function已经执行的时间res[fid] += curFunctionEnd - curFunctionStart + 1,
    //其中curFunction的ID可以用stack.pop()来获取，用一个变量preTime来记录上一次function(无论跟这一次是否相同）的时间(有可能是start,也有可能是end)，
    //根据start/end的属性来分别求的function的运行时间
    //这题需要注意的是：对于start和end, update pre的时候需要不同对待，因为end是某一时间戳的end,所以他实际上是下一个时间戳的开始，为了保持一致，我们需要
    //将pre update为curEnd  + 1
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int pre = 0;
        for(String log : logs) {
            String[] vals = log.split(":");
            int fid = Integer.valueOf(vals[0]);
            boolean start = vals[1].equals("start");
            int curTime = Integer.valueOf(vals[2]);
            if(start) {
                if(!stack.isEmpty()){
                    res[stack.peek()] += curTime - pre;
                }
                stack.push(fid);
                pre = curTime;
            } else {
                res[stack.pop()] += curTime - pre + 1;
                pre = curTime + 1;
            }
        }
        return res;
    }
}
