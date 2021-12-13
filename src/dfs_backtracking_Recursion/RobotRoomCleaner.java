package dfs_backtracking_Recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.
 *
 * The robot starts at an unknown location in the root that is guaranteed to be empty, and you do not have access to the grid, but you can move the robot using the given API Robot.
 *
 * You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
 *
 * When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell
 */


  // This is the robot's control interface.
  // You should not implement it, or speculate about its implementation
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();
    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();

    // Clean the current cell.
    public void clean();
}

public class RobotRoomCleaner {
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//clockwise, right, down, left, up, dont mess them up
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, 0, 0, 0, visited);
    }
    private void dfs(Robot robot, int x, int y, int curDir, Set<String> visited){
        robot.clean();
        for(int i = 0; i < 4; i++){
            int nextDir = (curDir + i) % 4;
            int nextX = x + dir[nextDir][0];
            int nextY = y + dir[nextDir][1];
            if(!visited.contains(nextX + ":" + nextY) && robot.move()){
                visited.add(nextX + ":" + nextY);
                dfs(robot, nextX, nextY, nextDir, visited);
                //backtracking, after try all the possibilities at cur dir, we go back to last step/cell and try next available move at that cell
                goBack(robot);
            }
            robot.turnRight();//before try next dir, we let robot make a right turn
        }
    }
    private void goBack(Robot robot){
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
