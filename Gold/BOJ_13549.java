import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {

    static int[] graph = new int[100001];
    static boolean[] visited = new boolean[100001];
    static int seeker;
    static int hider;

    public static void initGraph() {
        Arrays.fill(graph, 1000001);
    }

    public static void bfs(int start) {
        Queue<int[]> q = new PriorityQueue<int[]>((int[] i1, int[] i2) -> {
            return i1[1] - i2[1];
        });
        q.add(new int[] { start, 0 });
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();

            if (tmp[0] * 2 <= 100000 && (!visited[tmp[0] * 2] || graph[tmp[0] * 2] > graph[tmp[0]])) {
                graph[tmp[0] * 2] = graph[tmp[0]];
                visited[tmp[0] * 2] = true;
                q.add(new int[] { tmp[0] * 2, graph[tmp[0]] });
            }
            if (tmp[0] + 1 <= 100000 && (!visited[tmp[0] + 1] || graph[tmp[0] + 1] > graph[tmp[0]] + 1)) {
                graph[tmp[0] + 1] = graph[tmp[0]] + 1;
                visited[tmp[0] + 1] = true;
                q.add(new int[] { tmp[0] + 1, graph[tmp[0]] + 1 });
            }
            if (tmp[0] - 1 >= 0 && (!visited[tmp[0] - 1] || graph[tmp[0] - 1] > graph[tmp[0]] + 1)) {
                graph[tmp[0] - 1] = graph[tmp[0]] + 1;
                visited[tmp[0] - 1] = true;
                q.add(new int[] { tmp[0] - 1, graph[tmp[0]] + 1 });
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        seeker = Integer.parseInt(st.nextToken());
        hider = Integer.parseInt(st.nextToken());

        initGraph();
        graph[seeker] = 0;
        bfs(seeker);

        System.out.println(graph[hider]);
    }

}