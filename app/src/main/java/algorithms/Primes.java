package algorithms;
import java.util.Vector;

public class Primes {
  /**
   * Generates a sieve of Eratosthenes for prime detection up to the given limit.
   * This is the core algorithm used by all prime-related methods in this class.
   * 
   * <p>Algorithm: Sieve of Eratosthenes</p>
   * <ul>
   *   <li>Time Complexity: O(n log log n)</li>
   *   <li>Space Complexity: O(n)</li>
   * </ul>
   * 
   * <p><b>Memory Requirements:</b></p>
   * <ul>
   *   <li>n = 10: ~10 bytes</li>
   *   <li>n = 100: ~100 bytes</li>
   *   <li>n = 1,000: ~1 KB</li>
   *   <li>n = 10,000: ~10 KB</li>
   *   <li>n = 100,000: ~100 KB</li>
   *   <li>n = 1,000,000: ~1 MB</li>
   *   <li>n = 10,000,000: ~10 MB</li>
   *   <li>n = 100,000,000: ~100 MB</li>
   * </ul>
   * 
   * @param limit The upper bound (inclusive) for the sieve.
   * @return A boolean array where isPrime[i] is true if i is prime, false otherwise.
   */
  private static boolean[] generateSieve(int limit) {
    if (limit < 2) {
      return new boolean[limit + 1];
    }
    
    boolean[] isPrime = new boolean[limit + 1];
    
    // Initialize all numbers as potentially prime
    for (int i = 2; i <= limit; i++) {
      isPrime[i] = true;
    }
    
    // Sieve of Eratosthenes algorithm
    for (int i = 2; i * i <= limit; i++) {
      if (isPrime[i]) {
        // Mark all multiples of i as composite
        for (int j = i * i; j <= limit; j += i) {
          isPrime[j] = false;
        }
      }
    }
    
    return isPrime;
  }

  /**
   * Checks if a number is prime using the Sieve of Eratosthenes algorithm.
   * 
   * <p><b>Implementation:</b> This method generates a sieve up to n and performs
   * a direct lookup. For repeated primality checks, consider using {@link #getAllPrimesUpTo(int)}
   * to generate the sieve once and reuse it.</p>
   * 
   * <p><b>Performance:</b></p>
   * <ul>
   *   <li>Time Complexity: O(n log log n) for sieve generation + O(1) lookup</li>
   *   <li>Space Complexity: O(n)</li>
   * </ul>
   * 
   * <p><b>Memory Usage:</b> Allocates approximately n bytes for the sieve array.</p>
   * 
   * @param n The number to check.
   * @return True if the number is prime, false otherwise.
   */
  public static boolean IsPrime(int n) {
    if (n < 2) {
      return false;
    }
    
    boolean[] sieve = generateSieve(n);
    return sieve[n];
  }

  /**
   * Sums all prime numbers from 0 to n (exclusive) using the Sieve of Eratosthenes.
   * 
   * <p><b>Implementation:</b> Generates a sieve up to n-1 and sums all numbers marked as prime.
   * This replaces the previous trial division approach with a single-pass sieve generation
   * followed by a summation.</p>
   * 
   * <p><b>Performance:</b></p>
   * <ul>
   *   <li>Time Complexity: O(n log log n) for sieve + O(n) for summation = O(n log log n)</li>
   *   <li>Space Complexity: O(n)</li>
   *   <li>Previous Implementation: O(n * √n) with trial division</li>
   * </ul>
   * 
   * <p><b>Memory Usage:</b> Allocates approximately n bytes for the sieve array.</p>
   * 
   * @param n The upper bound (exclusive) - sums all primes less than n.
   * @return The sum of all prime numbers less than n.
   */
  public static int SumPrimes(int n) {
    if (n <= 2) {
      return 0;
    }
    
    boolean[] sieve = generateSieve(n - 1);
    int sum = 0;
    
    for (int i = 2; i < n; i++) {
      if (sieve[i]) {
        sum += i;
      }
    }
    
    return sum;
  }

  /**
   * Returns all prime numbers up to and including n using the Sieve of Eratosthenes.
   * 
   * <p><b>Usage:</b> This utility method exposes the sieve results directly, which is
   * useful when you need to perform multiple operations with primes in a range. It's
   * more efficient to call this once and reuse the result than to call {@link #IsPrime(int)}
   * repeatedly.</p>
   * 
   * <p><b>Performance:</b></p>
   * <ul>
   *   <li>Time Complexity: O(n log log n) for sieve generation + O(n) for collection</li>
   *   <li>Space Complexity: O(n) for sieve + O(π(n)) for result vector (where π(n) ≈ n/ln(n))</li>
   * </ul>
   * 
   * <p><b>Memory Usage:</b> Allocates approximately n bytes for the sieve array plus
   * storage for the returned prime numbers.</p>
   * 
   * <p><b>Examples:</b></p>
   * <ul>
   *   <li>getAllPrimesUpTo(10) returns [2, 3, 5, 7]</li>
   *   <li>getAllPrimesUpTo(1) returns []</li>
   *   <li>getAllPrimesUpTo(2) returns [2]</li>
   * </ul>
   * 
   * @param n The upper bound (inclusive) for prime generation.
   * @return A Vector containing all prime numbers from 2 to n (inclusive).
   */
  public static Vector<Integer> getAllPrimesUpTo(int n) {
    Vector<Integer> primes = new Vector<Integer>();
    
    if (n < 2) {
      return primes;
    }
    
    boolean[] sieve = generateSieve(n);
    
    for (int i = 2; i <= n; i++) {
      if (sieve[i]) {
        primes.add(i);
      }
    }
    
    return primes;
  }

  /**
   * Finds all prime factors of a number using trial division.
   * 
   * <p><b>Implementation:</b> Uses trial division with optimization to only check
   * divisors up to √n. This method does not use the sieve as the current approach
   * is already efficient for single factorizations.</p>
   * 
   * <p><b>Performance:</b></p>
   * <ul>
   *   <li>Time Complexity: O(√n) in the worst case (when n is prime)</li>
   *   <li>Space Complexity: O(log n) for the result vector</li>
   * </ul>
   * 
   * <p><b>Note:</b> For factoring multiple numbers, consider pre-generating a sieve
   * of primes up to √(max_n) and using those as trial divisors for improved performance.</p>
   * 
   * @param n The number to find the prime factors of.
   * @return A vector of all prime factors of n (with repetition for prime powers).
   */
  public static Vector<Integer> PrimeFactors(int n) {
    Vector<Integer> ret = new Vector<Integer>();

    for (int i = 2; i * i <= n; i++) { // Optimized loop condition
      while (n % i == 0) { // Optimized to handle repeated factors
        ret.add(i);
        n /= i; // Reduce n to avoid redundant checks.
      }
    }
    if (n > 1) { // Add any remaining prime factor.
        ret.add(n);
    }
    return ret;
  }

  /**
   * Generates a boolean array using the Sieve of Eratosthenes algorithm.
   * The array indicates which numbers from 0 to n are prime.
   * 
   * <p>Algorithm: Sieve of Eratosthenes
   * <ul>
   *   <li>Time complexity: O(n log log n)</li>
   *   <li>Space complexity: O(n)</li>
   * </ul>
   * 
   * <p>This is significantly faster than checking each number individually,
   * especially for large values of n. The algorithm works by iteratively marking
   * the multiples of each prime as composite, starting from 2.
   * 
   * @param n The upper limit (inclusive) for prime generation.
   * @return A boolean array where sieve[i] is true if i is prime, false otherwise.
   *         Returns array with all false for n < 2.
   */
  public static boolean[] generateSieve(int n) {
    if (n < 2) {
      // For n < 2, there are no primes
      boolean[] sieve = new boolean[n + 1];
      return sieve; // All false by default
    }

    boolean[] sieve = new boolean[n + 1];
    
    // Initialize all numbers as potentially prime
    for (int i = 2; i <= n; i++) {
      sieve[i] = true;
    }
    
    // Sieve of Eratosthenes algorithm
    for (int i = 2; i * i <= n; i++) {
      if (sieve[i]) {
        // Mark all multiples of i as composite
        // Start from i*i as smaller multiples already marked by smaller primes
        for (int j = i * i; j <= n; j += i) {
          sieve[j] = false;
        }
      }
    }
    
    return sieve;
  }

  /**
   * Returns all prime numbers up to and including n using the Sieve of Eratosthenes.
   * 
   * <p>Algorithm: Sieve of Eratosthenes
   * <ul>
   *   <li>Time complexity: O(n log log n)</li>
   *   <li>Space complexity: O(n)</li>
   * </ul>
   * 
   * <p>This method is significantly faster than repeatedly calling IsPrime() for each number,
   * making it ideal for generating all primes up to a limit. For n=1,000,000, this can be
   * orders of magnitude faster than the naive approach.
   * 
   * @param n The upper limit (inclusive) for prime generation.
   * @return A Vector containing all prime numbers from 2 to n in ascending order.
   *         Returns empty vector for n < 2.
   */
  public static Vector<Integer> getAllPrimesUpTo(int n) {
    Vector<Integer> primes = new Vector<Integer>();
    
    if (n < 2) {
      return primes; // No primes less than 2
    }
    
    boolean[] sieve = generateSieve(n);
    
    // Collect all prime numbers
    for (int i = 2; i <= n; i++) {
      if (sieve[i]) {
        primes.add(i);
      }
    }
    
    return primes;
  }

  /**
   * Sums all prime numbers from 0 to n using the Sieve of Eratosthenes algorithm.
   * 
   * <p>This is an optimized alternative to SumPrimes() that uses the Sieve of Eratosthenes
   * algorithm for significantly improved performance on large values of n.
   * 
   * <p>Algorithm: Sieve of Eratosthenes
   * <ul>
   *   <li>Time complexity: O(n log log n)</li>
   *   <li>Space complexity: O(n)</li>
   * </ul>
   * 
   * <p>Performance comparison:
   * <ul>
   *   <li>SumPrimes(): O(n√n) - checks primality of each number individually</li>
   *   <li>sumPrimesUsingSieve(): O(n log log n) - generates all primes at once</li>
   * </ul>
   * 
   * <p>For large n (e.g., n=1,000,000), this method can be orders of magnitude faster.
   * Both methods produce identical results.
   * 
   * @param n The upper limit (exclusive) for prime summation.
   * @return The sum of all prime numbers less than n.
   */
  public static long sumPrimesUsingSieve(int n) {
    if (n < 2) {
      return 0; // No primes less than 2
    }
    
    boolean[] sieve = generateSieve(n - 1);
    long sum = 0;
    
    // Sum all prime numbers
    for (int i = 2; i < n; i++) {
      if (sieve[i]) {
        sum += i;
      }
    }
    
    return sum;
  }
}
