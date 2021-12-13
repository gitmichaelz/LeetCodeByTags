package array_matrix_sorting;

/**
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 *
 * Implement the Vector2D class:
 *
 *     Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 *     next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
 *     hasNext() returns true if there are still some elements in the vector, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
 * [[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
 * Output
 * [null, 1, 2, 3, true, true, 4, false]
 */
public class Flatten2DVector {
    class Vector2D {
        int[][] v;
        int i = 0, j = 0;
        public Vector2D(int[][] v) {
            this.v = v;
        }

        public int next() {
            if(hasNext()) return v[i][j++];
            return -1;
        }

        public boolean hasNext() {
            while(i < v.length && j == v[i].length){//move to next available row
                i++;
                j = 0;
            }
            return i < v.length;
        }
    }
//with iterator
// class Vector2D {
//     List<Integer> list;
//     Iterator<Integer> iterator;
//     public Vector2D(int[][] v) {
//         list = new ArrayList<>();
//         for(int[] row : v){
//             for(int num : row){
//                 list.add(num);
//             }
//         }
//         iterator = list.iterator();
//     }

//     public int next() {
//         return iterator.next();
//     }

//     public boolean hasNext() {
//         return iterator.hasNext();
//     }
// }

//With iterator的另一种实现，掌握
// class Vector2D {
//     Iterator<List<Integer>> i;
//     Iterator<Integer> j;
//     public Vector2D(int[][] v) {
//         List<List<Integer>> lol = new ArrayList<List<Integer>>();
//         for(int[] sv:v){
//             List<Integer> lst = new ArrayList<>();
//             for(int ssv:sv){
//                 lst.add(ssv);
//             }
//             lol.add(lst);
//         }
//         i = lol.iterator();
//     }

//     public int next() {
//         hasNext();
//         return j.next();
//     }

//     public boolean hasNext() {
//         while((j==null||!j.hasNext())&&i.hasNext())
//             j = i.next().iterator();
//         return j!=null&&j.hasNext();
//     }
// }
}