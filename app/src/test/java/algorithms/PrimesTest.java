package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Vector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

public class PrimesTest {

  @Nested
  @DisplayName("IsPrime(int n) tests")
  class IsPrimeTests {

    @Test
    @DisplayName("Edge case: negative numbers should return false")
    public void testIsPrimeNegative() {
      assertFalse(Primes.IsPrime(-1), "IsPrime(-1) should return false");
      assertFalse(Primes.IsPrime(-5), "IsPrime(-5) should return false");
      assertFalse(Primes.IsPrime(-100), "IsPrime(-100) should return false");
    }

    @Test
    @DisplayName("Edge case: n=0 should return false")
    public void testIsPrimeZero() {
      assertFalse(Primes.IsPrime(0), "IsPrime(0) should return false");
    }

    @Test
    @DisplayName("Edge case: n=1 should return false")
    public void testIsPrimeOne() {
      assertFalse(Primes.IsPrime(1), "IsPrime(1) should return false");
    }

    @Test
    @DisplayName("n=2 (smallest prime) should return true")
    public void testIsPrimeTwo() {
      assertTrue(Primes.IsPrime(2), "IsPrime(2) should return true");
    }

    @Test
    @DisplayName("Small primes: 3, 5, 7 should return true")
    public void testIsPrimeSmallPrimes() {
      assertTrue(Primes.IsPrime(3), "IsPrime(3) should return true");
      assertTrue(Primes.IsPrime(5), "IsPrime(5) should return true");
      assertTrue(Primes.IsPrime(7), "IsPrime(7) should return true");
    }

    @Test
    @DisplayName("Small composites: 4, 6, 8, 9 should return false")
    public void testIsPrimeSmallComposites() {
      assertFalse(Primes.IsPrime(4), "IsPrime(4) should return false");
      assertFalse(Primes.IsPrime(6), "IsPrime(6) should return false");
      assertFalse(Primes.IsPrime(8), "IsPrime(8) should return false");
      assertFalse(Primes.IsPrime(9), "IsPrime(9) should return false");
    }

    @Test
    @DisplayName("Larger primes: 13, 17, 19 should return true")
    public void testIsPrimeLargerPrimes() {
      assertTrue(Primes.IsPrime(13), "IsPrime(13) should return true");
      assertTrue(Primes.IsPrime(17), "IsPrime(17) should return true");
      assertTrue(Primes.IsPrime(19), "IsPrime(19) should return true");
    }

    @Test
    @DisplayName("Larger composites: 15, 20, 100 should return false")
    public void testIsPrimeLargerComposites() {
      assertFalse(Primes.IsPrime(15), "IsPrime(15) should return false");
      assertFalse(Primes.IsPrime(20), "IsPrime(20) should return false");
      assertFalse(Primes.IsPrime(100), "IsPrime(100) should return false");
    }
  }

  @Nested
  @DisplayName("SumPrimes(int n) tests")
  class SumPrimesTests {

    @Test
    @DisplayName("Edge case: n=0 should return 0")
    public void testSumPrimesZero() {
      assertEquals(0, Primes.SumPrimes(0), "SumPrimes(0) should return 0");
    }

    @Test
    @DisplayName("Edge case: n=1 should return 0 (no primes less than 1)")
    public void testSumPrimesOne() {
      assertEquals(0, Primes.SumPrimes(1), "SumPrimes(1) should return 0");
    }

    @Test
    @DisplayName("Edge case: n=2 should return 0 (no primes less than 2)")
    public void testSumPrimesTwo() {
      assertEquals(0, Primes.SumPrimes(2), "SumPrimes(2) should return 0");
    }

    @Test
    @DisplayName("n=5 should return 5 (primes < 5: 2, 3)")
    public void testSumPrimesFive() {
      // Primes less than 5 are: 2, 3
      // Sum = 2 + 3 = 5
      assertEquals(5, Primes.SumPrimes(5), "SumPrimes(5) should return 5");
    }

    @Test
    @DisplayName("n=6 should return 5 (primes < 6: 2, 3, 5)")
    public void testSumPrimesSix() {
      // Primes less than 6 are: 2, 3, 5
      // Sum = 2 + 3 + 5 = 10
      assertEquals(10, Primes.SumPrimes(6), "SumPrimes(6) should return 10");
    }

    @Test
    @DisplayName("n=10 should return 17 (primes < 10: 2, 3, 5, 7)")
    public void testSumPrimesTen() {
      // Primes less than 10 are: 2, 3, 5, 7
      // Sum = 2 + 3 + 5 + 7 = 17
      assertEquals(17, Primes.SumPrimes(10), "SumPrimes(10) should return 17");
    }

    @Test
    @DisplayName("n=11 should return 17 (primes < 11: 2, 3, 5, 7)")
    public void testSumPrimesEleven() {
      // Primes less than 11 are: 2, 3, 5, 7
      // Sum = 2 + 3 + 5 + 7 = 17
      assertEquals(17, Primes.SumPrimes(11), "SumPrimes(11) should return 17");
    }

    @Test
    @DisplayName("n=12 should return 28 (primes < 12: 2, 3, 5, 7, 11)")
    public void testSumPrimesTwelve() {
      // Primes less than 12 are: 2, 3, 5, 7, 11
      // Sum = 2 + 3 + 5 + 7 + 11 = 28
      assertEquals(28, Primes.SumPrimes(12), "SumPrimes(12) should return 28");
    }
  }

  @Nested
  @DisplayName("PrimeFactors(int n) tests")
  class PrimeFactorsTests {

    @Test
    @DisplayName("Edge case: n=1 should return empty vector")
    public void testPrimeFactorsOne() {
      Vector<Integer> result = Primes.PrimeFactors(1);
      assertEquals(0, result.size(), "PrimeFactors(1) should return empty vector");
    }

    @Test
    @DisplayName("Edge case: n=2 should return [2]")
    public void testPrimeFactorsTwo() {
      Vector<Integer> expected = new Vector<>(Arrays.asList(2));
      assertEquals(expected, Primes.PrimeFactors(2), "PrimeFactors(2) should return [2]");
    }

    @Test
    @DisplayName("Prime input: n=7 should return [7]")
    public void testPrimeFactorsSeven() {
      Vector<Integer> expected = new Vector<>(Arrays.asList(7));
      assertEquals(expected, Primes.PrimeFactors(7), "PrimeFactors(7) should return [7]");
    }

    @Test
    @DisplayName("Prime input: n=11 should return [11]")
    public void testPrimeFactorsEleven() {
      Vector<Integer> expected = new Vector<>(Arrays.asList(11));
      assertEquals(expected, Primes.PrimeFactors(11), "PrimeFactors(11) should return [11]");
    }

    @Test
    @DisplayName("Prime input: n=13 should return [13]")
    public void testPrimeFactorsThirteen() {
      Vector<Integer> expected = new Vector<>(Arrays.asList(13));
      assertEquals(expected, Primes.PrimeFactors(13), "PrimeFactors(13) should return [13]");
    }

    @Test
    @DisplayName("Composite input: n=12 should return [2, 2, 3]")
    public void testPrimeFactorsTwelve() {
      // 12 = 2 * 2 * 3
      Vector<Integer> expected = new Vector<>(Arrays.asList(2, 2, 3));
      assertEquals(expected, Primes.PrimeFactors(12), "PrimeFactors(12) should return [2, 2, 3]");
    }

    @Test
    @DisplayName("Composite input: n=18 should return [2, 3, 3]")
    public void testPrimeFactorsEighteen() {
      // 18 = 2 * 3 * 3
      Vector<Integer> expected = new Vector<>(Arrays.asList(2, 3, 3));
      assertEquals(expected, Primes.PrimeFactors(18), "PrimeFactors(18) should return [2, 3, 3]");
    }

    @Test
    @DisplayName("Composite input: n=24 should return [2, 2, 2, 3]")
    public void testPrimeFactorsTwentyFour() {
      // 24 = 2 * 2 * 2 * 3
      Vector<Integer> expected = new Vector<>(Arrays.asList(2, 2, 2, 3));
      assertEquals(expected, Primes.PrimeFactors(24), "PrimeFactors(24) should return [2, 2, 2, 3]");
    }

    @Test
    @DisplayName("Perfect square: n=4 should return [2, 2]")
    public void testPrimeFactorsFour() {
      // 4 = 2 * 2
      Vector<Integer> expected = new Vector<>(Arrays.asList(2, 2));
      assertEquals(expected, Primes.PrimeFactors(4), "PrimeFactors(4) should return [2, 2]");
    }

    @Test
    @DisplayName("Perfect square: n=9 should return [3, 3]")
    public void testPrimeFactorsNine() {
      // 9 = 3 * 3
      Vector<Integer> expected = new Vector<>(Arrays.asList(3, 3));
      assertEquals(expected, Primes.PrimeFactors(9), "PrimeFactors(9) should return [3, 3]");
    }

    @Test
    @DisplayName("Perfect square: n=16 should return [2, 2, 2, 2]")
    public void testPrimeFactorsSixteen() {
      // 16 = 2 * 2 * 2 * 2
      Vector<Integer> expected = new Vector<>(Arrays.asList(2, 2, 2, 2));
      assertEquals(expected, Primes.PrimeFactors(16), "PrimeFactors(16) should return [2, 2, 2, 2]");
    }

    @Test
    @DisplayName("Composite with larger factors: n=30 should return [2, 3, 5]")
    public void testPrimeFactorsThirty() {
      // 30 = 2 * 3 * 5
      Vector<Integer> expected = new Vector<>(Arrays.asList(2, 3, 5));
      assertEquals(expected, Primes.PrimeFactors(30), "PrimeFactors(30) should return [2, 3, 5]");
    }
  }
}
