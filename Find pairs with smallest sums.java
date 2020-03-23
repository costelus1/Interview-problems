/**
 * You are given two integer arrays a and b sorted in ascending order and an 
 * integer k. Find k pairs (a_0, b_0), ... , (a_k, b_k) with the smallest
 * sums.
 *
 * Input: a = [1, 7, 11], b = [2, 4, 6], k = 3
 * Output: [[1, 2], [1, 4], [1, 6]] 
 * Explanation: The first 3 pairs are returned from the cartesian product 
 *    [1, 2], [1, 4], [1, 6], [7, 2], [7, 4], [11, 2], [7, 6], [11, 4], [11, 6]
 *
 * Input: a = [1, 1, 2], b = [1, 2, 3], k = 2
 * Output: [[1, 1], [1, 1]]
 * 
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 *
 */

/**
 * We form a matrix with the a values indexing the rows and the b values
 * indexing the columns. E.g., if a = [1, 7, 11] and b = [2, 4, 6], the
 * matrix looks like this:
 *
 *         2   4   6
 *      +------------
 *   1  |   
 *   7  | 
 *   11 | 
 * 
 * We insert possible candidate pairs in a min-heap. The first entry we insert
 * is clearly (a_0, b_0). Then, at each step, we remove the min from the heap, 
 * add it to the result, and insert back in the heap other candidates.
 * 
 * Below # means a pair that was removed from the heap, and ? means a pair that
 * is in the heap.
 *
 * Say that the pair we removed has a - index i and b - index j.
 * 
 * --> If j < b.length - 1, we insert the next cell on the same row. 
 *
 * For example, if we have just removed (1, 4) from the heap
 *
 *         2   4   6
 *      +------------
 *   1  |  #   # 
 *   7  |  ?
 *   11 | 
 * 
 * then we insert (1, 6)
 *
 *         2   4   6
 *      +------------
 *   1  |  #   #   ?
 *   7  |  ?
 *   11 | 
 *
 * --> If j == 0, we insert the first cell on the next row (if there is a next 
 * row).
 *
 */

class Solution {

    class Pair{
        int first;
        int second;
        
        Pair(int x, int y) {
            this.first = x;
            this.second = y;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] a, int[] b, int k) {
        Queue<Pair> pq = new PriorityQueue<Pair>( 
            (x, y) -> a[x.first] + b[x.second] - a[y.first] - b[y.second]
        );
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (a.length == 0 || b.length == 0) return res;
        
        pq.add(new Pair(0, 0));
        
        for (int i = 0; i < k; i++) {
            if (pq.isEmpty()) return res;
            Pair minPair = pq.remove();
            res.add(List.of(a[minPair.first], b[minPair.second]));
            
            // add the next entry on the same row
            if (minPair.second < b.length - 1) 
                pq.add(new Pair(minPair.first, minPair.second + 1));
                
            // if the b-index is 0, also add the first cell on the next row
            if (minPair.second == 0 && minPair.first < a.length - 1) 
                pq.add(new Pair(minPair.first + 1, 0));
        }
        return res;
    }
}

/**
 * Running time: Adding or removing a pair from the priority queue takes O(log k),
 * so the total running time is O(k * log k).
 */