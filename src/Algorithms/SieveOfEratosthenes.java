package Algorithms;

import java.util.Arrays;

public class SieveOfEratosthenes {
    // It is used in questions where we are given a range and the interviewer can give us 'q' queries where
    // he can ask whether a number (which is in range) is prime or not. So we can solve it
    // in TC q*(Root(range)) i.e. for each query we find whether the number is prime or not and TC for that is root(N)
    // But by using this algorithm we can do some initial work and then solve any query in O(1)

    // A number is prime if it is not divisible by any integer less than root of that number like for
    // 36 the root is 6 so if 36 is not divisible by any number from 2-6 then it is a prime number.

    // Video - https://www.youtube.com/watch?v=z-Ct00cFYpU&t=1205s . Explained complexity from 26:00 onwards.
    // Complexity of making the prime array is n*log(log(n)) which is approximately equal to O(N) since
    // log(log(n) is a very small value

    // The general algorithm is for generating prime numbers from range [0,b] but we can use this same logic
    // to find prime numbers in any range i.e. [a,b] and not just [0,b]

    public static void main(String args[]) {
        SOE(47);
    }

    public static void SOE(int range) {
        boolean[] primes = new boolean[range + 1];// this array will in the end
        // store false in indexes which are prime. And range + 1 because we have to consider
        // last element as well and since elements are represented by indexes therefore if we dont do it then
        // we wont be able to check last element
        Arrays.fill(primes, true); // i.e. initially we are assuming that all numbers in the range are prime

        for (int i = 2; i * i < (range+1); i++) {
            if(primes[i]){ // i.e. if we already marked a number as not prime then the number which
                // marked this current number as not prime must have marked all the multiples of this
                // current numbers as not prime as well because see suppose we reached 9 and it would
                // have been marked not prime by 3 so you take any multiple of 9 you can break it into
                // multiple of 3 as well like 36 = 9*4 = (3*3)*4. which means all the multiples of 9 are already
                // marked by 3 so no need to run loop for 9
                for (int j = i * 2; j <= range; j += i) { // we start the loop from next multiple that why j = 2*i
                    primes[j] = false; // Marking as not prime
                }
            }
        }
        displayPrimes(primes);
    }

    public static void displayPrimes(boolean[] primes) {
        System.out.println("Printing Primes");
        for (int i = 2; i < primes.length - 1; i++) { // starting from 2 because 1 is neither prime nor composite
            if (primes[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
