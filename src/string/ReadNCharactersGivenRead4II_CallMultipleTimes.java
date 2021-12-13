package string;

/**
 * Given a file and assume that you can only read the file using a given method read4, implement a method read to read n characters. Your method read may be called multiple times.
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

public class ReadNCharactersGivenRead4II_CallMultipleTimes extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    //I used buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data received in previous calls. In the while loop,
    // if buffPtr reaches current buffCnt, it will be set as zero to be ready to read new data.
    private int pointer = 0;
    private int len = 0;
    private char[] buf4 = new char[4];
    public int read(char[] buf, int n) {
        int idx = 0;//这个idx每次call都需要初始化一次，因为是要计算此次call的char数量
        while(idx < n){
            if(pointer == 0) {//这个必须判断，因为上一次call结束后，有可能pointer < len,即上一次的buf4里还有data
                len = read4(buf4);
            }
            if(len == 0) break;//no more data to consume from stream
            while(idx < n && pointer < len){
                buf[idx++] = buf4[pointer++];
            }
            if(pointer == len) pointer = 0;
        }
        return idx;
    }
}
