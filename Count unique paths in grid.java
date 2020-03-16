/**
 *
 * A robot is located at the top-left corner of a m x n grid. The robot can only 
 * move either down or right at any point in time. The robot is trying to reach 
 * the bottom-right corner of the grid. How many possible unique paths are there?
 * 
 * Input: m = 3, n = 2
 * Output: 3
 * 
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * 
 * https://leetcode.com/problems/isomorphic-strings/
 *
 */

class Solution {
    public int uniquePaths(int m, int n) {
        /**
         * g[i][j] is the number of paths from cell (i,j) to the
         * bottom-right corner
         */
        int[][] g = new int[m][n];

        // one path for all cells on the bottom row
        for (int j = 0; j <= n - 1; j++)
            g[0][j] = 1;
        
        // one path for all cells on the rightmost column
        for (int i = 0; i <= m - 1; i++)
            g[i][n - 1] = 1;
        
        for (int i = 1; i <= m - 1; i++)
            for (int j = n - 2; j >= 0; j--)
                g[i][j] = g[i - 1][j] + g[i][j + 1];
        
        return g[m - 1][0];
    }
}

/**
 * Time complexity: O(m * n) 
 */ 