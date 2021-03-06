/*
	In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

	Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

	Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

	Example:
	Input: [1,2,1,2,6,7,5,1], 2
	Output: [0, 3, 5]
	Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
	We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
	
	Note:
	nums.length will be between 1 and 20000.
	nums[i] will be between 1 and 65535.
	k will be between 1 and floor(nums.length / 3).
 */

// Solution 1:
// First compute the subarray sum for each k windows.
// Then use two dp array left and right to get the best subarray sum to the left and right or each index.
// Since the indices need to be k-units apart, 
// we scan through the middle index, and left is middle - k,
// and right is mid + k.
//
// Time: O(n)
// Space: O(n)
// 08/30/2018

class Solution {
    int max;
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] windows = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                windows[i - k + 1] = sum;
            }
        }
        int[] res = new int[3];
        Arrays.fill(res, -1);
        
        int[] left = new int[windows.length];
        int best = 0;
        for (int i = 0; i < windows.length; i++) {
            if (windows[i] > windows[best]) {
                best = i; 
            }
            left[i] = best;
        }
        
        int[] right = new int[windows.length];
        best = windows.length - 1;
        for (int i = windows.length - 1; i >= 0; i--) {
            if (windows[i] > windows[best]) {
                best = i; 
            }
            right[i] = best;
        }
        
        int max = 0;
        for (int mid = k; mid < windows.length - k; mid++) {
            int l = left[mid - k];
            int r = right[mid + k];
            int s = windows[l] + windows[mid] + windows[r];
            if (s > max) {
                max = s;
                res[0] = l;
                res[1] = mid;
                res[2] = r;
            }
        }
        return res;
    }
}