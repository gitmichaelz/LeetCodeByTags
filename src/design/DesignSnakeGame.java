package design;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Design a Snake game that is played on a device with screen size height x width. Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.
 *
 * You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.
 *
 * Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until the snake eats the first piece of food.
 *
 * When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies after moving (i.e. a snake of length 4 cannot run into itself).
 *
 * Implement the SnakeGame class:
 *
 *     SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of size height x width and the positions of the food.
 *     int move(String direction) Returns the score of the game after applying one direction move by the snake. If the game is over, return -1.
 */
public class DesignSnakeGame {
    //use a deque to mimic the snake movement. Every time we move one step forward, we get a new x and y
    //we have three situation:
    //1> the new head is out of the boundary, return -1
    //2> the new head bite its body(it is ok the new head in the old tail position), return -1.
    //3> the new head position is the food, the body remains the same (we will add back the deleted tail),
    class SnakeGame {
        Deque<Integer> snake;//for any 2d position x, y, can be encoded as 1d x*width + y
        Set<Integer> body;//to check if the new head hit the body in O(1) time
        int indexOfFood;//to track the next available food, also it is the score of the game, eat food, score++
        int[][] food;
        int width;
        int height;

        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        public SnakeGame(int width, int height, int[][] food) {
            snake = new LinkedList<>();
            body = new HashSet<>();
            this.food = food;
            this.width = width;
            this.height = height;
            snake.offer(0);
            body.add(0);
            indexOfFood = 0;
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            if(indexOfFood == -1) return -1;//already dead, cannot move further and eat food
            int head = snake.peek();
            int x = head / width, y = head % width;
            if(direction.equals("U")){
                x--;
            } else if(direction.equals("D")){
                x++;
            } else if(direction.equals("L")) {
                y--;
            } else {
                y++;
            }
            body.remove(snake.peekLast());//each move we first remove the snake's tail so we can check if the new head can bite the body
            int newHead = x * width + y;
            if(x < 0 || x == height || y < 0 || y == width || body.contains(newHead)) {//case 1 and case 2
                return indexOfFood = -1;
            }
            snake.offerFirst(newHead);//把头加上
            body.add(newHead);//坑，这里第一次做的时候忘记在body里加上头了
            if(indexOfFood < food.length && x == food[indexOfFood][0] && y == food[indexOfFood][1]){//坑，不要忘记判断indexOfFood的合法性
                body.add(snake.peekLast());//吃到食物，尾巴保持原位置不变，body把去掉的尾巴加回来
                indexOfFood++;
            } else {
                snake.pollLast();//如果没有吃到食物，则相当于只往前走了一步，把尾巴去掉即可
            }
            return indexOfFood;
        }
    }
}
