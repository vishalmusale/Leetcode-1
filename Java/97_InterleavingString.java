Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

https://leetcode.com/problems/interleaving-string/solution/
Time complexity: O(m*n)
Space complexity: O(m*n)
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()){
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <=m; i++){
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }
        for (int j = 1;j <= n; j++){
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1) || dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
            }
        }
        return dp[m][n];
    }
}

Rolling array:
time complexity: O(m*n)
space complexity: O(n)
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()){
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[2][n+1];
        for (int i = 0; i <= m; i++){
            for (int j = 0; j <= n; j++){
                if (i == 0 && j == 0){
                    dp[i][j] = true;
                }else if (j==0){
                    dp[i%2][j] = dp[(i-1)%2][0] &&s1.charAt(i-1) == s3.charAt(i-1);
                }else if (i == 0){
                    dp[i%2][j] = dp[i%2][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
                }else{
                   dp[i%2][j] = dp[(i-1)%2][j] && s1.charAt(i-1) == s3.charAt(i+j-1) || dp[i%2][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1); 
                }
                
            }
        }
        return dp[m%2][n];
    }
}
