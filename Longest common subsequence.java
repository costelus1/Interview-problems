/**
 * Given two strings x and y, return the length of their longest common 
 * subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string obtained from the original string 
 * by deleting zero or more characters, and without changing the relative order 
 * of the remaining characters. Eg "ace" is a subsequence of "abcde" while "aec" 
 *
 * Input: x = "abcde", y = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace"
 *
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 */

class Solution {
    public int longestCommonSubsequence(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] LCS = new int[m + 1][n + 1];
        // LCS[i][j] is the length of the longest common subseq of x[1...i], y[1...j]
        // we use the 0-indexed entries in LCS as sentinels
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                int ignore_xi = LCS[i - 1][j];
                int ignore_yj = LCS[i][j - 1];
                int matchOrIgnore_xi_yj = LCS[i - 1][j - 1] + 
                    (x.charAt(i - 1) == y.charAt(j - 1) ? 1 : 0);
                LCS[i][j] = Math.max(Math.max(ignore_xi, ignore_yj), matchOrIgnore_xi_yj);
            }
        return LCS[m][n];
    }
}

/**
 * Running time: O(m * n), where m is the length of x and n is the length of y.
 */