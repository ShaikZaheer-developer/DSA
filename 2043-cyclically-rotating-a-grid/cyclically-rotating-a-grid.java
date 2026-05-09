class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int layers = Math.min(m, n) / 2;

        for (int l = 0; l < layers; l++) {
            // 1. Identify the boundaries of the current layer
            int top = l, left = l;
            int bottom = m - 1 - l, right = n - 1 - l;

            // 2. Extract elements in Counter-Clockwise order
            List<Integer> list = new ArrayList<>();
            for (int j = left; j < right; j++) list.add(grid[top][j]); // Top row
            for (int i = top; i < bottom; i++) list.add(grid[i][right]); // Right col
            for (int j = right; j > left; j--) list.add(grid[bottom][j]); // Bottom row
            for (int i = bottom; i > top; i--) list.add(grid[i][left]); // Left col

            // 3. Rotate the list using k % size
            int size = list.size();
            int shift = k % size;

            // 4. Put elements back, but start from the 'shift' position in the list
            int idx = shift;
            for (int j = left; j < right; j++) grid[top][j] = list.get(idx++ % size);
            for (int i = top; i < bottom; i++) grid[i][right] = list.get(idx++ % size);
            for (int j = right; j > left; j--) grid[bottom][j] = list.get(idx++ % size);
            for (int i = bottom; i > top; i--) grid[i][left] = list.get(idx++ % size);
        }
        return grid;
    }
}