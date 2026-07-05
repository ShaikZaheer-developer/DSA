class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // ASCII 128 array acts as a ultra-fast hash map for character counts
        int[] targetCounts = new int[128];
        for (char c : t.toCharArray()) {
            targetCounts[c]++;
        }

        // Sliding window pointers and variables
        int left = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        int requiredChars = t.length();

        // Expand the right pointer to explore the string
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // If this character is needed for 't', decrement our required total match count
            if (targetCounts[rightChar] > 0) {
                requiredChars--;
            }
            // Decrement the map count (negative means we have extra of this character)
            targetCounts[rightChar]--;

            // When the window is valid (contains all characters of 't')
            while (requiredChars == 0) {
                int currentWindowLength = right - left + 1;
                
                // Track the smallest window found so far
                if (currentWindowLength < minLength) {
                    minLength = currentWindowLength;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                // We are about to eject 'leftChar' from the window, so restore its count
                targetCounts[leftChar]++;
                
                // If it becomes positive, it means we actually broke the valid window state
                if (targetCounts[leftChar] > 0) {
                    requiredChars++;
                }
                
                // Shrink the window from the left
                left++;
            }
        }

        // Return the best substring found, or empty string if no window was valid
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
}