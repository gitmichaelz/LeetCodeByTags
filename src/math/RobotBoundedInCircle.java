package math;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
 *
 *     "G": go straight 1 unit;
 *     "L": turn 90 degrees to the left;
 *     "R": turn 90 degrees to the right.
 *
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 *
 *
 * Example 1:
 *
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 */
public class RobotBoundedInCircle {
    //这题就记住一个结论。如果机器人在一个圈里活动。它必须满足以下其中一点。
    //1. 经过一轮指令回到原点。
    //2. 或者一轮指令后不在原点，但方向不和原点一致(不指向北方)，这样再经过三个cycle就能回到原点。
    //用(x, y)表示机器人位置，idx = 0, 1, 2, 3分别表示北，东，南，西四个方位。
    //int[][]dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}},表示分别在对应的方向移动一个单位
    //最后判断x,y以及idx的值即可
    public boolean isRobotBounded(String instructions) {
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;//initial position
        int idx = 0;//facing north
        for(char c : instructions.toCharArray()) {
            if(c == 'L') {
                idx = (idx + 3) % 4;//相对于当前位置逆时针90度，顺时针270度。 % 4是因为一共就4个方向
            } else if(c == 'R') {
                idx = (idx + 1) % 4;
            } else {
                x += dirs[idx][0];
                y += dirs[idx][1];
            }
        }
        // after one cycle:
        // robot returns into initial position
        // or robot doesn't face north
        return (x == 0 && y == 0) || idx != 0;
    }
}
