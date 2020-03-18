/**
 * You are given a list of non-negative integers a_0, a_1, ..., a_{n - 1}, 
 * and a target T. For each of the numbers a_0, a_1, ..., a_{n - 1}, you can
 * choose either the sign + or -. Find out in how many ways you can assign
 * signs to make the sum equal to the given target T. 
 *
 * Input: a is [1, 1, 1, 1], S is 2. 
 * Output: 4
 * Explanation: 
 *    - 1 + 1 + 1 + 1 = 2
 *    + 1 - 1 + 1 + 1 = 2
 *    + 1 + 1 - 1 + 1 = 2
 *    + 1 + 1 - 1 + 1 = 2
 *
 *  - the length of the given array is positive and will not exceed 20.
 *  - the sum of elements in the given array will not exceed 1000.
 *  - the answer is guaranteed to be fitted in a 32-bit integer.
 * 
 * https://leetcode.com/problems/target-sum/
 *
 */

class Solution {
    public int findTargetSumWays(int[] a, int T) {
        int n = a.length;
        int S = 0;
        for (int i = 0; i < n; i++)
            S += a[i];
        if (S < T) return 0;
        /**
         * - If 0 <= j <= S, dp[i][j] is the number of ways of assigning signs 
         * to the numbers a_0, ..., a_i in order to obtain the sum -j.
         * - If S + 1 <= j <= 2*S, dp[i][j] is the number of ways of assigning signs 
         * to the numbers a_0, ..., a_i in order to obtain the sum j - S.
         */
        int[][] dp = new int[n][2*S + 1];
        dp[0][S + a[0]] = 1;
        dp[0][S - a[0]] = 1;
        
        // if a[0] is 0, it can be taken with either + or -
        if (a[0] == 0) dp[0][S] = 2;
        
        for (int i = 0; i < n - 1; i++) 
            for (int j = 0; j <= 2*S; j++) {
                int plus = (j + a[i + 1] <= 2*S) ? dp[i][j + a[i + 1]] : 0;
                int minus = (j - a[i + 1] >= 0) ? dp[i][j - a[i + 1]] : 0;
                dp[i + 1][j] = minus + plus;
            }
        return dp[n - 1][S + T];
    }
}

/**
 * Running time: expected O(n*S), where S is the sum of elements of the input array
 */
