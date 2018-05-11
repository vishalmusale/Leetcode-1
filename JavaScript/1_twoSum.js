
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    var ans = [];
    var hash = {};
    for (let i = 0; i < nums.length; i++){
        var other = target - nums[i];
        if (hash[other.toString()] != undefined){
            ans.push(hash[other.toString()]);
            ans.push(i);
            return ans;
        }
        hash[nums[i].toString()] = i;
    }
    return ans;
};
