package tree;

/**
 * Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.
 *
 * Return the root of the Quad-Tree representing the grid.
 *
 * Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 *
 *     val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 *     isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 */
public class ConstructQuadTree {
    // Definition for a QuadTree node.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }


    public Node construct(int[][] grid) {
        return helper(grid, 0, 0, grid.length);
    }

    public Node helper(int[][] grid, int x, int y, int len) {
        if(len == 1)
            return new Node(grid[x][y] == 1, true);
        Node root = new Node();
        Node topLeft = helper(grid, x, y, len/2);
        Node topRight = helper(grid, x , y + len/2, len/2) ;
        Node bottomLeft = helper(grid, x + len/2, y, len/2);
        Node bottomRight = helper(grid, x + len/2, y + len/2, len/2);
        if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            root.isLeaf = true;
            root.val = topLeft.val;

        } else {
            root.topLeft = topLeft;
            root.topRight = topRight;
            root.bottomLeft = bottomLeft;
            root.bottomRight = bottomRight;
        }
        return root;
    }
}
