class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start ascending; if starts are equal, sort by end descending
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        int remainingCount = 0;
        int maxEnd = 0;
        
        for (int[] curr : intervals) {
            // If the current interval extends past the furthest end seen so far,
            // it is not covered.
            if (curr[1] > maxEnd) {
                remainingCount++;
                maxEnd = curr[1];
            }
        }
        
        return remainingCount;
    }
}