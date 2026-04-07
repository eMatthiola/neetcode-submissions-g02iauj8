class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> res = new HashMap<>();
 
        for(String str : strs) {

            int[] counter = new int[26];
            
            for(int i = 0; i < str.length(); i++) {
                counter[str.charAt(i) - 'a']++;
            }

            String key = Arrays.toString(counter);
            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(str);
        }

        return new ArrayList<>(res.values());
     }
}
