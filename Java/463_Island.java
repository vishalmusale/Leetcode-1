You are given a map in form of a two-dimensional integer grid where 1 represents
land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). 
The grid is completely surrounded by water, and there is exactly one island (i.e., one or more 
connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the 
water around the island). One cell is a square with side length 1. The grid is rectangular, width 
and height don't exceed 100. Determine the perimeter of the island.

class Solution {
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int island = 0;
        int neighbor = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    island++;
                    if (i+1 < m && grid[i+1][j] == 1){//count down
                        neighbor++;
                    }
                    if (j+1 < n && grid[i][j+1] == 1){//count right
                        neighbor++;
                    }
                }
            }
        }
        return island * 4 - neighbor * 2;
    }
}

