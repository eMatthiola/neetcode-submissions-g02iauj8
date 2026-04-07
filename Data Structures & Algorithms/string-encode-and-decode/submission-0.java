class Solution {

    public String encode(List<String> strs) {
        if (strs.isEmpty()) return "";
        List<Integer> sizes = new ArrayList<>();
        StringBuilder res = new StringBuilder();

        //add size to sizes
        for (String str : strs) {
            sizes.add(str.length());
        }

        //add , after the size
        for (int size : sizes) {
            res.append(size).append(',');
        }

        //end with #
        res.append('#');

        //add string to res
        //"5,5,1,#helloworld!"
        for (String str : strs) {
            res.append(str);
        }

        return res.toString();
    }

    public List<String> decode(String str) {
        if (str.length() == 0) {
            return new ArrayList<>();
        }

        List<Integer> sizes = new ArrayList<>();
        List<String> res = new ArrayList<>();
        int i = 0;
        while (str.charAt(i) != '#') {
            StringBuilder cur = new StringBuilder();
            while (str.charAt(i) != ',') {
                cur.append(str.charAt(i));
                i++;
            }
            sizes.add(Integer.parseInt(cur.toString()));
            i++;
        }
        //jump #
        i++;

        for (int sz : sizes) {
            res.add(str.substring(i, i + sz));
            i += sz;
        }

        return res;
    }
}
