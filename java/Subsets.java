/*
 * Given a set of distinct integers, nums, return all possible subsets.
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * For example,
 * If nums = [1,2,3], a solution is:
 * 
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 */

//backtracking
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), 0, nums);
        return result;
    }
    
    public void helper(List<List<Integer>> result, List<Integer> list, int start, int[] nums) {
        // base case
        if (start > nums.length) return;
        
        // add the deep copy of list to the result
        result.add(new ArrayList<Integer>(list));
        
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            helper(result, list, i+1, nums);
            list.remove(list.size()-1);
        }
        return;
    }
}