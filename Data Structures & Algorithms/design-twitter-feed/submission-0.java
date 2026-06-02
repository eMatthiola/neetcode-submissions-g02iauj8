public class Twitter {
    private static int timeStamp = 0; // 全局时间戳，每发一条推文+1
    private Map<Integer, User> userMap; // userId -> User对象

    // 推文节点，链表结构
    private class Tweet {
        public int id;
        public int time;
        public Tweet next; // 指向上一条推文

        public Tweet(int id) {
            this.id = id;
            time = timeStamp++; // 发推文时记录时间戳
            next = null;
        }
    }

    public class User {
        public int id;
        public Set<Integer> followed; // 关注的人
        public Tweet tweet_head;      // 最新推文（链表头）

        public User(int id) {
            this.id = id;
            followed = new HashSet<>();
            follow(id);      // 关注自己，这样getNewsFeed能看到自己的推文
            tweet_head = null;
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unfollow(int id) {
            followed.remove(id);
        }

        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = tweet_head; // 新推文的next指向原来的头
            tweet_head = t;      // 新推文成为新的头，保证头永远最新
        }
    }

    public Twitter() {
        userMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        // 用户不存在则创建
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        userMap.get(userId).post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();
        if (!userMap.containsKey(userId)) return res;

        Set<Integer> users = userMap.get(userId).followed;

        // 大顶堆，按时间戳排序，堆顶永远是最新推文
        PriorityQueue<Tweet> q = new PriorityQueue<>(users.size(), (a, b) -> b.time - a.time);

        // K路归并：每个关注的人只放最新一条（tweet_head）进堆
        for (int user : users) {
            Tweet t = userMap.get(user).tweet_head;
            if (t != null) q.add(t); // 注意：不能放null进堆
        }

        int n = 0;
        while (!q.isEmpty() && n < 10) {
            Tweet t = q.poll();  // 取出全局最新的推文
            res.add(t.id);
            n++;
            // 核心：从同一个人补入下一条推文
            // 不一次性全放进去，保证堆大小始终 <= K
            if (t.next != null) q.add(t.next);
        }

        return res;
    }

    public void follow(int followerId, int followeeId) {
        // 用户不存在则创建
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        // 不能取关自己（构造时follow了自己，取关会导致看不到自己的推文）
        if (!userMap.containsKey(followerId) || followerId == followeeId) return;
        userMap.get(followerId).unfollow(followeeId);
    }
}