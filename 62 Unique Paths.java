/**
 *
 * A robot is located at the top-left corner of a m x n grid. The robot can only 
 * move either down or right at any point in time. The robot is trying to reach 
 * the bottom-right corner of the grid. How many possible unique paths are there?
 * 
 * https://leetcode.com/problems/isomorphic-strings/
 *
 */

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] g = new int[m][n];
        // g[i][j] = number of paths from the cell (i,j) to the
        // bottom-right corner
        
        // one path for all the cells on the bottom row
        for (int j = 0; j <= n - 1; j++)
            g[0][j] = 1;
        
        // one path for all the cells on the rightmost column
        for (int i = 0; i <= m - 1; i++)
            g[i][n - 1] = 1;
        
        for (int i = 1; i <= m - 1; i++)
            for (int j = n - 2; j >= 0; j--)
                g[i][j] = g[i - 1][j] + g[i][j + 1];
        
        return g[m - 1][0];
    }
}

/**
* Expected time: O(n), space: O(n), where n = source.length. 
*/ 