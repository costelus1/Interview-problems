/**
 * Given a non-empty string s and a dictionary wordDict containing a list of 
 * non-empty words, determine if s can be segmented into a space-separated 
 * sequence of one or more dictionary words. The same word in the dictionary 
 * may be reused multiple times in the segmentation.
 * Given two strings x and y, return the length of their longest common 
 * subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string obtained from the original string 
 * by deleting zero or more characters, and without changing the relative order 
 * of the remaining characters. Eg "ace" is a subsequence of "abcde" while "aec" 
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: "applepenapple" = "apple" + "pen" + "apple"
 *
 * https://leetcode.com/problems/word-break/
 *
 */

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<String>(wordDict);
        int n = s.length();
        // dp[i] is true if the string s[0...i] can be split into words
        boolean[] dp = new boolean[n];
        if (dict.contains(s.substring(0, 1)))
            dp[0] = true;
        for (int i = 1; i < n; i++) {
            if (dict.contains(s.substring(0, i + 1))) {
                dp[i] = true;
            }
            else {
                for (int k = 0; k < i; k++)
                    if (dp[k] && dict.contains(s.substring(k + 1, i + 1))) {
                        dp[i] = true;
                        break;
                    }
            }
        }
        return dp[n - 1];
    }
}

/**
 * Running time: expected O(n^2), where n is the length of s.
 */