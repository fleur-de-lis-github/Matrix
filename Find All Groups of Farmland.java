/* You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.

To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.

land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].

Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland, return an empty array. You may return the answer in any order.

 

Example 1:


Input: land = [[1,0,0],[0,1,1],[0,1,1]]
Output: [[0,0,0,0],[1,1,2,2]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].
Example 2:


Input: land = [[1,1],[1,1]]
Output: [[0,0,1,1]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].
Example 3:


Input: land = [[0]]
Output: []
Explanation:
There are no groups of farmland.
 

Constraints:

m == land.length
n == land[i].length
1 <= m, n <= 300
land consists of only 0's and 1's.
Groups of farmland are rectangular in shape. */

/*the idea is very similar to finding the number of islands. Here we consider each farmland to be an island. We can run a simple DFS to find the number of farmlands in the given grid. One simple modification is to keep track of the minimum and the maximum coordinates. So after finishing one DFS call we can store the minimum as the upper left coordinate and the maximum as the lower right coordinate. Refer to the solution for the implementation part.
Please upvote if it was helpful!!! */
 
class Solution {
    int x1;
    int y1;
    int x2;
    int y2;
    public int[][] findFarmland(int[][] land) {
        int rows = land.length;
        int cols = land[0].length;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        boolean[][] vis = new boolean[rows][cols];
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols ; j++){
                if(!vis[i][j] && land[i][j] == 1){
                    x1 = Integer.MAX_VALUE;
                    y1 = Integer.MAX_VALUE;
                    x2 = Integer.MIN_VALUE;
                    y2 = Integer.MIN_VALUE;
                    dfs(i , j , land , vis);
                    ArrayList<Integer> a = new ArrayList<>();
                    a.add(x1);
                    a.add(y1);
                    a.add(x2);
                    a.add(y2);
                    ans.add(a);
                }
            }
        }
        int size = ans.size();
        int[][] res = new int[size][4];
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < 4 ; j++){
                res[i][j] = ans.get(i).get(j);
            }
        }
        return res;
    }
    public void dfs(int i , int j , int[][] land , boolean[][] vis){
        vis[i][j] = true;
        
        if(i < x1 || j < y1){
            x1 = i;
            y1 = j;
        }
        if(i > x2 || j > y2){
            x2 = i;
            y2 = j;
        }
        int[] dx = {-1 , 0 , 1 , 0};
        int[] dy = {0 , 1 , 0 , -1};
        for(int k = 0 ; k < 4 ; k++){
            if(isValid(i + dx[k] , j + dy[k] , vis , land) == true)
                dfs(i + dx[k] , j + dy[k] , land , vis);
        }
    }
    public boolean isValid(int i , int j , boolean[][] vis , int[][] land){
        int rows = land.length;
        int cols = land[0].length;
        if(i < 0 || j < 0 || i > rows - 1 || j > cols - 1)
            return false;
        if(land[i][j] != 1)
            return false;
        if(vis[i][j] == true)
            return false;
        return true;
    }
}
