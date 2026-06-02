class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int f : freq) {
            if (f > 0) maxHeap.offer(f);
        }

        //冷却队列，存 [剩余次数, 可重新入堆的时间]
        Queue<int[]> coolDown = new LinkedList<>();

        int time = 0;
        while(!maxHeap.isEmpty() || !coolDown.isEmpty()) {
            time++;

            // 执行频次最高的任务
            if (!maxHeap.isEmpty()) {
                int cnt = maxHeap.poll() - 1;
                if (cnt > 0) {
                    coolDown.offer(new int[]{cnt, time + n});
                }
            }

            // 冷却结束的任务重新入堆
            if (!coolDown.isEmpty() && coolDown.peek()[1] == time) {
                maxHeap.offer(coolDown.poll()[0]);
            }
        }

        return time;
    }
}