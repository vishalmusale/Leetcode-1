In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0. What is the largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign. If there is none, return 0.

An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1 going up, down, left, and right, and made of 1s. This is demonstrated in the diagrams below. Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1s.

Examples of Axis-Aligned Plus Signs of Order k:

Order 1:
000
010
000

Order 2:
00000
00100
01110
00100
00000

Order 3:
0000000
0001000
0001000
0111110
0001000
0001000
0000000
Example 1:

Input: N = 5, mines = [[4, 2]]
Output: 2
Explanation:
11111
11111
11111
11111
11011
In the above grid, the largest plus sign can only be order 2.  One of them is marked in bold.
Example 2:

Input: N = 2, mines = []
Output: 1
Explanation:
There is no plus sign of order 2, but there is of order 1.
Example 3:

Input: N = 1, mines = [[0, 0]]
Output: 0
Explanation:
There is no plus sign, so return 0.
Note:

N will be an integer in the range [1, 500].
mines will have length at most 5000.
mines[i] will be length 2 and consist of integers in the range [0, N-1].

Method 1: Brute Force
Time complexity: O(N^3)
class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines){
            set.add(mine[0] * N + mine[1]);
        }
        int max = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                int k = 0;
                while (k <= i && k + i < N && k <= j && k + j < N && 
                       !set.contains((i-k) * N + j) && !set.contains((i+k) * N + j) &&
                       !set.contains(i * N + j - k) && !set.contains(i * N + j + k)){
                    k++;
                }
                max = Math.max(max, k);
            }
        }
        return max;
    }
}

Method 2: DP
Time complexity: O(N^2)
class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int max = 0;
        int[][] dp = new int[N][N];//max largest plus sign at the center of (i, j)
        for (int i = 0; i < N; i++){
            Arrays.fill(dp[i], N);
        }
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines){
            set.add(mine[0] * N + mine[1]);
        }
        for (int i = 0; i < N; i++){
            int l = 0;
            for (int j = 0; j < N; j++){
                if (set.contains(i*N+j)){
                    l = 0;
                }else{
                    l++;
                }
                dp[i][j] = Math.min(dp[i][j], l);
                
            }
            int r = 0;
            for (int j = N-1; j >= 0; j--){
                if (set.contains(i*N+j)){
                    r = 0;
                }else{
                    r++;
                }
                dp[i][j] = Math.min(dp[i][j], r);
            }
        }
        for (int j = 0; j < N; j++){
            int u = 0;
            for (int i = 0; i < N; i++){
                if (set.contains(i*N+j)){
                    u = 0;
                }else{
                    u++;
                }
                dp[i][j] = Math.min(dp[i][j], u);
            }
            int d = 0;
            for (int i = N-1; i >= 0; i--){
                if (set.contains(i*N+j)){
                    d = 0;
                }else{
                    d++;
                }
                dp[i][j] = Math.min(dp[i][j], d);
            }
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}

class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> banned = new HashSet();
        int[][] dp = new int[N][N];
        
        for (int[] mine: mines)
            banned.add(mine[0] * N + mine[1]);
        int ans = 0, count;
        
        for (int r = 0; r < N; ++r) {
            count = 0;
            for (int c = 0; c < N; ++c) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = count;
            }
            
            count = 0;
            for (int c = N-1; c >= 0; --c) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }
        
        for (int c = 0; c < N; ++c) {
            count = 0;
            for (int r = 0; r < N; ++r) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
            
            count = 0;
            for (int r = N-1; r >= 0; --r) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
                ans = Math.max(ans, dp[r][c]);
            }
        }
        
        return ans;
    }
}

Method 3:
https://leetcode.com/problems/largest-plus-sign/discuss/113314/JavaC++Python-O(N2)-solution-using-only-one-grid-matrix
