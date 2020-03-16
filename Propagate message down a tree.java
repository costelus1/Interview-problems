/**
 *
 * A company has n employees with unique IDs from 0 to n - 1. The head of the 
 * company has the ID headID. Each employee has one direct manager, indicated in the
 * array manager: manager[i] is the direct manager of the i-th employee, 
 * manager[headID] = -1. It is guaranteed that the subordination relationships 
 * have a tree structure.
 * 
 * The head of the company wants to inform all the employees of the company of an 
 * urgent piece of news. He will inform his direct subordinates and they will inform 
 * their subordinates and so on until all employees know about the news. The i-th 
 * employee needs time[i] minutes to inform all of his direct subordinates (i.e. 
 * after time[i] minutes, all his direct subordinates can start spreading the news).

 * Compute the number of minutes needed to inform all the employees about the news.
 * A robot is located at the top-left corner of a m x n grid. The robot can only 
 * move either down or right at any point in time. The robot is trying to reach 
 * the bottom-right corner of the grid. How many possible unique paths are there?
 * 
 * Input: n = 4, headID = 2, manager = [3,3,-1,2], time = [0,0,1,2]
 * Output: 3
 * 
 * Input: n = 1, headID = 0, manager = [-1], time = [0]
 * Output: 0
 * 
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/ 
 *
 */

class Solution {
    public int numOfMinutes(int n, int headId, int[] manager, int[] time){
        if (n == 1) return 0;
        // construct the adjacency matrix
        List<Integer>[] adj = (List<Integer>[]) new List[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
                if (manager[i] != -1)
                    adj[manager[i]].add(i);
        
        // dist[i] is the time needed for employee i to be informed
        int[] dist = new int[n];
        
        // do a BFS starting from the head to compute the distances
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(headId);
        while (!q.isEmpty()) {
            int current = q.remove();
            for (int child: adj[current]) {
                dist[child] = dist[current] + time[current];
                q.add(child);
            }
        }
        
        // the answer is the maximum distance
        int max = -1;
        for (int i = 0; i < n; i++)
            max = Math.max(max, dist[i]);
        return max;
    }
}

/**
 * Time complexity: O(n) 
 */ 