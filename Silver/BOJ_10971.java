import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971 {
    private static int cityNum;
    private static int[][] cost;
    private static boolean[] visited;
    private static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cityNum = Integer.parseInt(br.readLine());
        cost = new int[cityNum][cityNum];
        visited = new boolean[cityNum];

        for (int i = 0; i < cityNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cityNum; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < cityNum; i++) {
            visited[i] = true;
            dfs(i, i, 1, 0);
            visited[i] = false;
        }

        System.out.println(minCost);
    }

    private static void dfs(int start, int cur, int cnt, int costSum) {
        if (cnt == cityNum) {
            if (cost[cur][start] != 0) {
                minCost = Math.min(minCost, costSum + cost[cur][start]);
                return;
            }
        }

        for (int i = 0; i < cityNum; i++) {
            if (!visited[i] && cost[cur][i] != 0) {
                visited[i] = true;
                dfs(start, i, cnt + 1, costSum + cost[cur][i]);
                visited[i] = false;
            }
        }
    }
}
