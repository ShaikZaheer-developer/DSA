class Solution {
    public int longestSubstring(String s, int k) {
        return divideAndConquer(s, 0, s.length(), k);
    }

    private int divideAndConquer(String s, int start, int end, int k) {
        if (end - start < k) return 0;

        // Count frequencies of characters in the current substring
        int[] counts = new int[26];
        for (int i = start; i < end; i++) {
            counts[s.charAt(i) - 'a']++;
        }

        // Find the first character that violates the condition
        for (int i = start; i < end; i++) {
            if (counts[s.charAt(i) - 'a'] < k) {
                // Split at this character and check left and right halves
                int leftLen = divideAndConquer(s, start, i, k);
                int rightLen = divideAndConquer(s, i + 1, end, k);
                return Math.max(leftLen, rightLen);
            }
        }

        // If all characters meet the criteria, the entire substring is valid
        return end - start;
    }
}