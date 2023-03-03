import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int itemNum = Integer.parseInt(st.nextToken());
        int limitWeight = Integer.parseInt(st.nextToken());

        Pair[] items = new Pair[itemNum];
        int[] dp = new int[limitWeight + 1];

        for (int i = 0; i < itemNum; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[i] = new Pair(weight, value);
        }

        for (int i = 0; i < itemNum; i++) {
            for (int j = limitWeight; j >= items[i].first; j--) {
                dp[j] = Math.max(dp[j], dp[j - items[i].first] + items[i].second);
            }
        }
        System.out.println(dp[limitWeight]);
    }
}