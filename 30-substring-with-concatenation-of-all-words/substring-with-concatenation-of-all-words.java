class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        
        if (s.length() < totalLen) {
            return result;
        }

        // Step 1: Build the frequency map for the target words
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Step 2: Run the sliding window wordLen times to cover all phase offsets
        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> currentFreq = new HashMap<>();
            int left = i;
            int count = 0; // Tracks how many valid words are currently in the window

            for (int right = i; right <= s.length() - wordLen; right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (wordFreq.containsKey(word)) {
                    currentFreq.put(word, currentFreq.getOrDefault(word, 0) + 1);
                    count++;

                    // If we have more instances of 'word' than required, shrink the window from the left
                    while (currentFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentFreq.put(leftWord, currentFreq.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // If the window size matches the total length of all words, we found a valid index
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Invalid word encountered: clear the window and reset
                    currentFreq.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }
}