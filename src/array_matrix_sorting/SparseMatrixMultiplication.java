package array_matrix_sorting;

/**
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.
 *
 *
 *
 * Example 1:
 *
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 */
public class SparseMatrixMultiplication {
    //矩阵乘法注意事项
    //1、当矩阵A的列数（column）等于矩阵B的行数（row）时，A与B可以相乘。
    //2、矩阵C的行数等于矩阵A的行数，C的列数等于B的列数。
    //3、乘积C的第m行第n列的元素等于矩阵A的第m行的元素与矩阵B的第n列对应元素乘积之和。
    //time:O(N^3) 读作n cubed,  x 的y次方读作： x to the yth power
    public int[][] multiply(int[][] A, int[][] B) {
        int row1 = A.length, col1 = A[0].length;
        int row2 = B.length, col2 = B[0].length;
        if(col1 != row2) return new int[0][0];//其实题目中已经assume他们相等，所以这里可写可不写
        int[][] C = new int[row1][col2];
        for(int i = 0; i < row1; i++){
            for(int j = 0; j < col1; j++){//A 的col对应B的row
                if(A[i][j] != 0){
                    for(int k = 0; k < col2; k++){
                        if(B[j][k] != 0){
                            C[i][k] += A[i][j] * B[j][k];//注意C的角标。C的行等于A的行，C的列等于B的列
                        }
                    }
                }
            }
        }
        return C;
    }
        /*
    //FB面试中，要求设计数据结构，稀疏矩阵的压缩存储，一般用三元组和十字链表。其中十字链表可以用来进行矩阵乘法等运算。
    //十字链表cross list, 图形参考 https://blog.csdn.net/u012734723/article/details/76552134
    //具体的数据结构及算法参考下面
    class CrossNode{
        int row;//该元素所在的行
        int col;//该元素所在的列
        int val;//元素值
        CrossNode right;//下一个行元素
        CrossNode down;//下一个列元素
        CrossNode(int row, int col, int val){
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    class CrossList{
        CrossNode root;//十字链表的总结点，存储总的行数，列数，以及非零元素个数信息
        CrossNode[] rowHeads;//存储每行的第一个元素, dummy row heads
        CrossNode[] colHeads;//存储每列的第一个元素, dummy col heads

        CrossList(int rows, int cols, int count){//count is the number of nonzero elements
            root = new CrossNode(rows, cols, count);
            rowHeads = new CrossNode[rows];
            for(int i = 0; i < rows; i++){
                rowHeads[i] = new CrossNode(-1, -1, 0);//dummy node's row and col are -1
                if(i > 0){
                    rowHeads[i - 1].down = rowHeads[i];
                }
            }
            colHeads = new CrossNode[cols];
            for(int j = 0; j < cols; j++){
                colHeads[j] = new CrossNode(-1, -1, 0);
                if(j > 0) {
                    colHeads[j - 1].right = colHeads[j];
                }
            }
        }

        public void insert(int[][] nums, int rows, int cols){
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    if(nums[i][j] != 0){
                        CrossNode node = new CrossNode(i, j, nums[i][j]);
                        insertNodeAndUpdate(node);
                    }
                }
            }
        }

        public void insertNodeAndUpdate(CrossNode node){//如果被插入位置已经存在，需要update, 原值 + 插入node的值
            //先定位行,找到第node.row行的dummy node
            CrossNode rnode = rowHeads[node.row];
            CrossNode pre = null;
            while(rnode != null && rnode.col < node.col){
                pre = rnode;
                rnode = rnode.right;
            }
            if(rnode == null){
                pre.right = node;
            } else if(rnode.col == node.col){//如果被插入位置已经存在，则直接更新其value即可
                rnode.val += node.val;
            } else {
                pre.right = node;
                node.right = rnode;
            }


            //再定位列，找到第node.col列的dummy node
            CrossNode cnode = colHeads[node.col];
            pre = null;
            while(cnode != null && cnode.row < node.row){
                pre = cnode;
                cnode = cnode.down;
            }
            if(cnode == null){
                pre.down = node;
            } else if(cnode.row > node.row){
                pre.down = node;
                node.down = cnode;
            }
        }
    }
    public CrossList crossListMultiplication(CrossList l1, CrossList l2){
        int row1 = l1.root.row, col1 = l1.root.col;
        int row2 = l2.root.row, col2 = l2.root.col;
        if(col1 != row2) return null;
        CrossList l = new CrossList(row1, col2, 0);

        for(int i = 0; i < row1; i++){
            CrossNode rhead = l1.rowHeads[i].right;
            while(rhead != null){
                for(int j = 0; j < col2; j++){
                    CrossNode chead = l2.colHeads[j].down;
                    while(chead != null && chead.row != rhead.col){
                        chead = chead.down;
                    }
                    if(chead != null){
                        l.insertNodeAndUpdate(new CrossNode(rhead.row, chead.col, rhead.val * chead.val));
                    }
                }
                rhead = rhead.right;
            }
        }
        return l;
    }
    */
}
