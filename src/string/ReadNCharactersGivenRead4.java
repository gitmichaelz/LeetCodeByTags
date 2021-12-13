package string;

/**
 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
 *
 * Method read4:
 *
 * The API read4 reads four consecutive characters from file, then writes those characters into the buffer array buf4.
 *
 * The return value is the number of actual characters read.
 *
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 *
 * Definition of read4:
 *
 *     Parameter:  char[] buf4
 *     Returns:    int
 *
 * buf4[] is a destination, not a source. The results from read4 will be copied to buf4[].
 */
class Reader4 {
    int read4(char[] buf){return String.valueOf(buf).length();}
}
public class ReadNCharactersGivenRead4 extends Reader4{
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    /*
    这道题相当于说我们每次读取文件里面的所有字符都是先由read4(buf)这个函数去读取，read4每次最多读取4个字符，read4的buf里最多有4个字符
然后，每次调用read4(buf)这个函数就是直接返回他这一次读取了多少个字符，buf的前n个(n是他的返回值)是他这一次都读入了哪些字符，所以相当于有两个返回值，读取字符的个数n, 以及这一次读取buf里面装了哪些字符
然后现在要设计一个read函数，他的buf里面一次性最多能装n个字符，然后就是这个n和文件总长度的关系
但是read函数不能直接接触文件，每次的读入都要借用read4(buf)函数，所以其实read函数是利用read4函数来读取文件（字符串）
也就是说read函数自己也要返回一个buf,然后用read4的buf拼接成read函数的buf,我们自己也不用声明buf,因为这个函数要自带这个buf
     */
    public int read(char[] buf, int n){
        char[] buf4 = new char[4];
        int idx = 0;
        while(idx < n){
            int len = read4(buf4);
            int pointer = 0;
            while(idx < n && pointer < len){
                buf[idx++] = buf4[pointer++];
            }
            if(len < 4) break;//we already finished reading
        }
        return idx;
    }
}
