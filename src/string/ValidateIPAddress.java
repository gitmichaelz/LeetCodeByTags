package string;

/**
 * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 *
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00" and "192.168@1.1" are invalid IPv4 addresses.
 *
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *
 *     1 <= xi.length <= 4
 *     xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 *     Leading zeros are allowed in xi.
 *
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 *
 *
 *
 * Example 1:
 *
 * Input: queryIP = "172.16.254.1"
 * Output: "IPv4"
 */
public class ValidateIPAddress {
    //注意参考split(String regex, int limit)的api, split(String regex) 默认limit 为0，会丢掉trailing empty strings，这样这个例子"2001:0db8:85a3:0:0:8A2E:0370:7334:"
    //会出现错的结果，所以我们应该limit = -1
    // If limit n is non-positive then the pattern will be applied as many times as possible and the array can have any length. If n is zero then the pattern
    // will be applied as many times as possible, the array can have any length, and trailing empty strings will be discarded.
    public String validIPAddress(String IP){
        if(IP.indexOf('.') > 0) {
            String[] fields = IP.split("\\.", -1);//注意这个写法  "\\."
            if(isIPv4(fields)) return "IPv4";
        } else if(IP.indexOf(':') > 0) {
            String[] fields = IP.split(":", -1);//注意这个写法   ":", 还有个坑是第一要注意加上-1，
            if(isIPv6(fields)) return "IPv6";
        }
        return "Neither";
    }
    private boolean isIPv4(String[] fields){
        if(fields.length != 4) return false;
        for(String field : fields){
            if(field.length() == 0 || field.length() > 3) return false;
            if(field.charAt(0) == '0' && field.length() != 1) return false;
            for(char c : field.toCharArray()){
                if(!Character.isDigit(c)) return false;
            }
            if(Integer.parseInt(field) > 255) return false;
        }
        return true;
    }

    private boolean isIPv6(String[] fields){//注意与IPv4的区别，这里0000是可以的2001:0db8:85a3:0000:0000:8a2e:0370:7334 is valid
        if(fields.length != 8) return false;
        String hexdigits = "0123456789abcdefABCDEF";//因为不区分大小写，所以我们在这里把他们都加上
        for(String field : fields){
            if(field.length() == 0 || field.length() > 4) return false;//field length must be between[0, 4]
            for(char c : field.toCharArray()){
                if(hexdigits.indexOf(c) == -1) return false;
            }
        }
        return true;
    }
}
