class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count character frequencies
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Sort frequencies in ascending order
        Arrays.sort(freq);

        int totalPushes = 0;

        // Step 3: Iterate from highest frequency to lowest
        for (int i = 0; i < 26; i++) {
            int count = freq[25 - i]; // Highest frequency first
            if (count == 0) break;   // No more characters left

            // Determine cost: 1-8 chars cost 1 push, 9-16 cost 2, etc.
            int pushesPerChar = (i / 8) + 1;
            totalPushes += count * pushesPerChar;
        }

        return totalPushes;
    }
}