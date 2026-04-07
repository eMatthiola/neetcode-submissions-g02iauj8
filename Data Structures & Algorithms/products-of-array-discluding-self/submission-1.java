class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        // Build prefix products
        // res[i] = product of all elements to the LEFT of i
        res[0] = 1; // nothing to the left of index 0
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // Multiply suffix products from right to left
        // postfix = product of all elements to the RIGHT of i
        int postfix = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= postfix;       // have to be first 
            postfix *= nums[i];      // expand postfix to include nums[i] for next iteration
        }

        return res;
    }
}