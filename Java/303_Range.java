Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.


class NumArray {
    int[] preSum;
    public NumArray(int[] nums) {
        int n = nums.length;
        preSum = new int[n+1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }
    }
    
    public int sumRange(int i, int j) {
        return preSum[j+1] - preSum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
