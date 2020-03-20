/**
 * Given a binary array, find the maximum length of a contiguous subarray 
 * with equal number of zeroes and ones.
 *
 * Input: [0, 1, 0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with 
 * an equal number of zeroes and ones.
 *
 * https://leetcode.com/problems/contiguous-array/
 *
 */

class Solution {
    public int findMaxLength(int[] a) {
        /**
         * We store in a hashmap the difference between the number of zeroes and the
         * number of ones until the current position, and the current position. If
         * we find a repeated key, we have a balanced subarray.
         */
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = a.length;
        int maxLen = 0;
        int diff = 0; // #zeroes - #ones in a[0 ... i - 1]
        map.put(0, 0);
        for (int i = 0; i < n; i++) {
            diff = (a[i] == 0) ? diff + 1 : diff - 1;
            // if we find a subarray with #zeroes = #ones
            if (map.containsKey(diff)) {
                int lastPos = map.get(diff);
                maxLen = Math.max(maxLen, i + 1 -lastPos);
            }
            else
                map.put(diff, i + 1);
        }
        return maxLen;
    }
}

/**
 * Expecred running time: O(n).
 */
