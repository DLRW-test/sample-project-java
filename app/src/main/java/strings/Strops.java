package strings;

public class Strops {
  /**
   * Reverses a string
   *
   * @param str The string to reverse.
   * @return The reversed string.
   */
  public String reverse(String str) {
    return new StringBuilder(str).reverse().toString();
  }

  /**
   * Checks if a string is a palindrome
   *
   * @param str The string to check.
   * @return True if the string is a palindrome, false otherwise.
   */
  public boolean isPalindrome(String str) {
    if (str.length() == 0) {
      return true;
    }

    int left = 0;
    int right = str.length() - 1;
    while (left < right) {
      if (str.charAt(left) != str.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }

    return true;
  }
}
