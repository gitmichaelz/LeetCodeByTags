package array_matrix_sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 *
 *
 * Example 1:
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 */
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
}
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule){
        List<Interval> res = new ArrayList<>();
        List<Interval> timeline = new ArrayList<>();
        for(List<Interval> employeeSchedule : schedule){
            timeline.addAll(employeeSchedule);
        }
        Collections.sort(timeline, Comparator.comparingInt(a -> a.start));
        int end = timeline.get(0).end;
        for(Interval interval : timeline){
            if(end < interval.start){
                res.add(new Interval(end, interval.start));
            }
            end = Math.max(end, interval.end);
        }
        return res;
    }
}
