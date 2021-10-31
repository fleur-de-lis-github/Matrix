/*You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

 Example 1:


Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
Example 2:


Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 

Constraints:

m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1. */

/*Do a bfs/dfs on grid2 matrix similar to what you did in Number of Islands.
For a current island ingrid2, check for every cell in that island if they are present in grid1. This will ensure if this is a sub-island of grid1.
Maintain a flag and mark it false if you encounter any cell which is part of island in grid2 and not a part of island in grid1.
Increase the count for each island of grid2, when flag is true.
Return count, as this will be the number of islands in grid2 that are considered sub-islands.
Source Code:
*/
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        boolean[][] vis = new boolean[m][n];
        int count = 0;
        int[] dir = {1, 0, -1, 0, 1};
        
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid2[i][j] == 0 || vis[i][j])
                    continue;
                
                Queue<int[]> queue = new LinkedList<>();
                boolean flag = true;
                vis[i][j] = true;
                
                queue.add(new int[] {i, j});
                
                while(!queue.isEmpty()) {
                    int[] vtx = queue.remove();
                    
                    if(grid1[vtx[0]][vtx[1]] == 0)
                        flag = false;
                    
                    for(int k = 0; k < 4; ++k) {
                        int x = vtx[0] + dir[k];
                        int y = vtx[1] + dir[k + 1];
                        
                        if(x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && !vis[x][y]) {
                            vis[x][y] = true;
                            
                            queue.add(new int[] {x, y});
                        }
                    }
                }
                
                if(flag)
                    ++count;
            }
        }
        
        return count;
    }
}
