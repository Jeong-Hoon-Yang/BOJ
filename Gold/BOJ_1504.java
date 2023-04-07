import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 200_000_001;
    static int[] dist;
    static List<List<Vertex>> graph = new ArrayList<>();
    static boolean[] visited;

    static class Vertex {
        int dst;
        int cost;

        public Vertex(int dst, int cost) {
            this.dst = dst;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());
        int vertexNum = Integer.parseInt(st.nextToken());
        dist = new int[nodeNum + 1];
        visited = new boolean[nodeNum + 1];

        for (int i = 0; i <= nodeNum; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < vertexNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Vertex(to, cost));
            graph.get(to).add(new Vertex(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int cost1 = 0;
        Arrays.fill(dist, MAX_VALUE);
        cost1 += dijkstra(1, v1);
        Arrays.fill(dist, MAX_VALUE);
        cost1 += dijkstra(v1, v2);
        Arrays.fill(dist, MAX_VALUE);
        cost1 += dijkstra(v2, nodeNum);

        int cost2 = 0;
        Arrays.fill(dist, MAX_VALUE);
        cost2 += dijkstra(1, v2);
        Arrays.fill(dist, MAX_VALUE);
        cost2 += dijkstra(v2, v1);
        Arrays.fill(dist, MAX_VALUE);
        cost2 += dijkstra(v1, nodeNum);

        if (cost1 >= MAX_VALUE && cost2 >= MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(cost1 <= cost2 ? cost1 : cost2);
    }

    private static int dijkstra(int start, int end) {
        Queue<Vertex> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        dist[start] = 0;
        pq.add(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex tmp = pq.poll();
            int curPos = tmp.dst;
            int curCost = tmp.cost;
            if (curPos == end)
                return curCost;
            for (int i = 0; i < graph.get(curPos).size(); i++) {
                int nextPos = graph.get(curPos).get(i).dst;
                int nextCost = graph.get(curPos).get(i).cost + curCost;
                if (dist[nextPos] > nextCost) {
                    dist[nextPos] = nextCost;
                    pq.add(new Vertex(nextPos, nextCost));
                }
            }
        }
        return MAX_VALUE;
    }
}