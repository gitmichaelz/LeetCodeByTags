package math;

public class IntegerToEnglishWords {
    //记住一点: 对于大于等于100的数，都要用helper递归求解。
    //1ms faster than 100%
    private String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] TENS = {" ", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        return helper(num);
    }
    //对于大于等于100的数，都要用helper递归求解。
    private String helper(int num){
        StringBuilder sb = new StringBuilder();
        if(num < 20) {
            sb.append(LESS_THAN_20[num]);
        } else if(num < 100) {
            sb.append(TENS[num / 10]).append(" ").append(helper(num % 10));
        } else if(num < 1000) {
            sb.append(helper(num / 100)).append(" Hundred ").append(helper(num % 100));
        } else if(num < 1000000) {// num < 1m
            sb.append(helper(num / 1000)).append(" Thousand ").append(helper(num % 1000));
        } else if(num < 1000000000){// num < 1b
            sb.append(helper(num / 1000000)).append(" Million ").append(helper(num % 1000000));
        } else {
            sb.append(helper(num / 1000000000)).append(" Billion ").append(helper(num % 1000000000));
        }
        return sb.toString().trim();//不要忘记trim(), 如 num = 20.如果不加trim会有空格
    }
}
