class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] charMap = new int[128]; // Tracks the next index after the last seen character
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // Move left pointer forward if the character was already seen in the current window
            left = Math.max(left, charMap[currentChar]);
            
            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
            
            // Store the next valid starting position (1-indexed based position)
            charMap[currentChar] = right + 1;
        }
        
        return maxLength;
    }
}