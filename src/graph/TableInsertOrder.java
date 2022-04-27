package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个图的拓扑排序问题
 * We have a bunch of raw SQL inserts which have been separated by table that need to be injected into our database.
 * Unfortunately because the tables have foreign key constraints we can't run the statements indiscriminately.
 * Given a list of table names which are present in a database.
 * Write a function which determines the proper table insertion order into that database considering foreign key constraints.
 * Assuming that you have access to the following two helper functions:
 * tables_with_constraints_on
 * Takes a list of tables names and returns a list of table names that must be inserted after the input tables. I.E the output tables have a foreign key constraint on one or more of the input tables.
 * tables_constrained_by
 * Takes a list of tables names and returns a list of table names that must be inserted before the input tables. I.E the returned tables have a foreign key constraint on one or more of the input tables.
 * Consider a database has the tables A, B, C, D, E, F, G, H
 * Table B has a fk on A and is self referencing
 * B -> A
 * B -> B
 * Table C has a fk on A  and B
 * C -> A
 * C -> B
 * Table D has a fk on B
 * D -> B
 * Table E has a fk on G
 * E -> G
 * Table F has a fk on E
 * F -> E
 * Table G has a fk on F
 * G -> F
 * Table H has a fk on G
 * H -> G
 * tables_with_constraints_on(['D']) -> []
 * tables_constrained_by(['D']) -> ['B', 'A'] since D -> B -> A
 * tables_with_constraints_on(['B']) -> ['C', 'D', 'B'] since C -> B and D -> B and B -> B
 * tables_constrained_by(['B']) -> ['A', 'B'] since B -> A and B -> B
 * tables_with_constraints_on(['G']) -> ['E', 'F', 'G', 'H'] since G -> F -> E -> G and H -> G
 * tables_constrained_by(['G']) -> ['E', 'F', 'G'] since G -> F -> E -> G
 *
 * 看不是很懂，請問 output 是什麼?
 * 因為B fk B 那就不能insert啦
 * 所以可以insert的結果是 D, C, H 是嗎?
 *
 * 我当时也问了面试官同样的问题。。。
 * 面试官说如果有循环引用的情况，我们先考虑随便选一个作为输出就可以。
 *
 * Q:请问最后两个：
 * tables_with_constraints_on(['G']) -> ['E', 'F', 'G'] since G -> F -> E -> G
 * tables_constrained_by(['G']) -> ['E', 'F', 'G', 'H'] since G -> F -> E -> G and H -> G
 * 是不是反了 ？ tables_with_constraints_on不是要 inserted after the input tables吗？ 为什么不是 ['E', 'F', 'G', 'H']  ？
 * tables_constrained_by(['G'])  -》  ['E', 'F', 'G']  ？
 * A:最后一个确实反了
 *
 * 这个就是一个有向图判断环的问题？没有环就可以退化成树，输出祖先和子孙？
 */
public class TableInsertOrder {
    //遇到环时，特殊处理，比如如度为1但是他是循环节点，此时扔输出该点，将其入队。但是要和面试官讨论，有多少个循环引用
    public static void main(String[] args) {
        List<List<String>> prerequeistes = new ArrayList<>();
        Integer a = 2;
        a++;
        System.out.println(a);

    }

    public static List<Character> getTableOrder(List<Character> tables) {
        return tables;
    }

    private static List<Character> tables_with_constraints_on(List<Character> tables) {//insert order: input first, then return list
        return tables;
    }

    private static List<Character> tables_constrained_by(List<Character> tables) {//insert order: return list first, then input
        return tables;
    }
}
