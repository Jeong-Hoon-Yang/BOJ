import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1260 {

    static boolean[][] vertex;
    static boolean[] visited;
    static int edgeNum;
    static StringBuilder sb = new StringBuilder();

    public static void BFS(int n) {
        Queue<Integer> q = new ArrayDeque<>();

        q.add(n);

        while (!q.isEmpty()) {
            int tmp = q.poll();
            if (!visited[tmp]) {
                sb.append(tmp).append(" ");
                visited[tmp] = true;
            }
            for (int i = 1; i <= edgeNum; i++) {
                if (vertex[tmp][i] && !visited[i]) {
                    q.add(i);
                }
            }
        }

        sb.append("\n");
    }

    public static void DFS(int n) {
        Stack<Integer> q = new Stack<>();

        q.add(n);

        while (!q.isEmpty()) {
            int tmp = q.pop();
            if (!visited[tmp]) {
                sb.append(tmp).append(" ");
                visited[tmp] = true;
            }
            for (int i = edgeNum; i >= 1; i--) {
                if (vertex[tmp][i] && !visited[i]) {
                    q.add(i);
                }
            }
        }

        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        edgeNum = Integer.parseInt(st.nextToken());
        int vertexNum = Integer.parseInt(st.nextToken());
        int startEdge = Integer.parseInt(st.nextToken());

        vertex = new boolean[edgeNum + 1][edgeNum + 1];
        visited = new boolean[edgeNum + 1];

        for (int i = 0; i < vertexNum; i++) {
            st = new StringTokenizer(br.readLine());
            int e1 = Integer.parseInt(st.nextToken());
            int e2 = Integer.parseInt(st.nextToken());

            vertex[e1][e2] = vertex[e2][e1] = true;
        }

        DFS(startEdge);
        Arrays.fill(visited, false);
        BFS(startEdge);

        System.out.println(sb.toString());
    }

}
