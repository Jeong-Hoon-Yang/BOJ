import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {
    static int[] dp;
    static Consulting[] consultings;

    static class Consulting {
        int time;
        int price;

        public Consulting(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int remainDate = Integer.parseInt(br.readLine());

        dp = new int[remainDate + 1];
        consultings = new Consulting[remainDate + 1];

        for (int i = 1; i <= remainDate; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            consultings[i] = new Consulting(time, price);
            if (i + time - 1 <= remainDate)
                dp[i + time - 1] = Math.max(price, dp[i + time - 1]);
        }

        for (int i = 1; i <= remainDate; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if (i + consultings[i].time - 1 <= remainDate) {
                dp[i + consultings[i].time - 1] = Math.max(dp[i - 1] + consultings[i].price,
                        dp[i + consultings[i].time - 1]);
            }
        }

        System.out.println(dp[remainDate]);
    }

}
