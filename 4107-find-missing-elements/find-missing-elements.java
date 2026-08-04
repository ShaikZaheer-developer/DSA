class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> present = new HashSet<>();

        // Find min, max, and store all numbers for O(1) lookup
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            present.add(num);
        }

        List<Integer> result = new ArrayList<>();

        // Iterate through the range [min, max] and collect missing numbers
        for (int i = min; i <= max; i++) {
            if (!present.contains(i)) {
                result.add(i);
            }
        }

        return result;
    }
}