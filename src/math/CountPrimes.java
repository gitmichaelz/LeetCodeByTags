package math;

/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {
    //for each prime number i, start off with j =  i * i, mark them as non_prime, we iterater on i until i * i <= n; since we are looking for all invlaid odd numbers(initially we assume all odd numbers are prime, we need to find out those non prime)
    //time: O(NloglogN), space. o(N)
    public int countPrimes(int n) {
        if(n < 3) return 0;
        boolean[] nonPrime = new boolean[n];//initialized with false, means all numbers are prime.
        int count = n / 2;//count all the odd number less than n for all n >= 3, remove non prime from count;
        for(int i = 3; i * i <= n; i += 2) {//优化，i += 2,
            if(nonPrime[i]) continue;//non prime
            //if i is prime, i * i is non prime,
            //if the current number is p, we can always mark off multiples of p starting at p2,
            // then in increments of p(since all number less than p already processed): p2 + p, p2 + 2p
            //since we are count the invalid odd number, we just use p2 += 2p;
            for(int j = i * i; j < n; j += 2 * i) {//因为执照出不符合要求的odd number,所以j += 2 * i;
                if(!nonPrime[j]) {//if i is prime, j = i * i is non prime,for example, 5 is prime, 25 is non prime, so we need to set nonPrime[25] = true;
                    count--;//j should be non prime, invalid prime, count--
                    nonPrime[j] = true;
                }
            }
        }
        return count;
    }
}
