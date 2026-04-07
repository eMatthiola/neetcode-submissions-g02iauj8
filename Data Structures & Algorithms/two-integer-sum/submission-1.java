class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        //     number , idx
        HashMap<Integer, Integer> pre = new HashMap<>();

        for (int i = 0; i < nums.length; i++ ) {
            int diff = target - nums[i];

            if (pre.containsKey(diff)) 
                return new int[] {pre.get(diff) , i}; 
            
            pre.put(nums[i], i);
        } 

       

        return  new int[] {-1, -1};
    }
}
