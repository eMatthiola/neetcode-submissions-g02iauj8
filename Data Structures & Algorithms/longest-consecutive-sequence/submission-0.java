class Solution {
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }

        for (int num : nums) {
            if (!numSet.contains(num - 1)) {//new start
                int length = 1;
                while (numSet.contains(num + length)) {
                    length++;
                }
                longest = Math.max(longest, length);
            } 
        }

        return longest;
    }
}
