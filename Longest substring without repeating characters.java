/**
 * Given a 2D board of characters and a word, find if the word appears in the 
 * grid. The word can be constructed from letters of sequentially adjacent cells, 
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same cell may not be used more than once.
 * 
 * Example:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 * https://leetcode.com/problems/word-search/
 *
 */

class Solution {
    public boolean exist(char[][] board, String word) 
    {
        int nRows = board.length;
        int nCols = board[0].length;
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nCols; j++)
                if (match(board, i, j, word))
                    return true;
        return false;
    }
    
    /**
     * match the string target starting from the cell (startR, startC)
     */
    public boolean match(char[][] board, int startR, int startC, String target)
    {
        if (target.length() == 0) return true; // we matched all characters
        
        // if we stepped outside the board, end the current branch
        if (startR < 0 || startC < 0 || startR == board.length || startC == board[0].length) 
            return false;
        
        if (board[startR][startC] != target.charAt(0)) return false;
        
        String nextToMatch = target.substring(1, target.length());
        if (nextToMatch.length() == 0) return true;
        
        char temp = board[startR][startC];
        // mark the current cell with a special character
        board[startR][startC] = '!';
        
        // flood fill
        boolean exist = match(board, startR, startC + 1, nextToMatch)
                     || match(board, startR, startC - 1, nextToMatch)
                     || match(board, startR + 1, startC, nextToMatch)
                     || match(board, startR - 1, startC, nextToMatch);
        // unmark the current cell
        board[startR][startC] = temp; 
        
        return exist;
    }
}