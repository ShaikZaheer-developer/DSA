class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Edge case handling
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Start at the top-right corner
        int row = 0;
        int col = n - 1;
        
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true; 
            } else if (matrix[row][col] > target) {
                col--; // Move left: eliminate this column
            } else {
                row++; // Move down: eliminate this row
            }
        }
        
        return false;
    }
}