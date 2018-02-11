You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.


Method 1:
Recursion + memorization
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        if (amount < 0){
            return -1;
        }
        if (map.containsKey(amount)){
            return map.get(amount);
        }
        
        int ans = Integer.MAX_VALUE;
        for (int coin : coins){
            int curr = 0;
            if (coin <= amount){
                int next = coinChange(coins, amount - coin);
                if (next >= 0){
                    curr = next + 1;
                }
            }
            if (curr > 0){
                ans = Math.min(ans, curr);
            }
        }
        int count = (ans == Integer.MAX_VALUE) ? -1 : ans;
        map.put(amount, count);
        return count;
    }
}
