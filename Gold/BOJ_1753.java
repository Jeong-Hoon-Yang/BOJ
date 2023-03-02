import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1753 {

    static int[] distance;
    static ArrayList<ArrayList<Pair>> edge;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void dijkstra(int start) {
        distance[start] = 0;

        Queue<Pair> pq = new PriorityQueue<>((Pair o1, Pair o2) -> {
            return o1.y - o2.y;
        });

        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair tmp = pq.poll();
            int cur = tmp.x;
            int weight = tmp.y;
            if (distance[cur] < weight)
                continue;
            for (int i = 0; i < edge.get(cur).size(); i++) {
                int next = edge.get(cur).get(i).x;
                int nextWeight = weight + edge.get(cur).get(i).y;
                if (distance[next] > nextWeight) {
                    distance[next] = nextWeight;
                    pq.add(new Pair(next, nextWeight));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int vNum = Integer.parseInt(st.nextToken());
        int eNum = Integer.parseInt(st.nextToken());

        distance = new int[vNum + 1];
        Arrays.fill(distance, 0x7fffffff);

        int startNum = Integer.parseInt(br.readLine());

        edge = new ArrayList<>();
        for (int i = 0; i <= vNum; i++) {
            edge.add(new ArrayList<>());
        }

        for (int i = 0; i < eNum; i++) {
            StringTokenizer tmp = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tmp.nextToken());
            int to = Integer.parseInt(tmp.nextToken());
            int weight = Integer.parseInt(tmp.nextToken());
            edge.get(from).add(new Pair(to, weight));
        }

        dijkstra(startNum);

        for (int i = 1; i <= vNum; i++) {
            if (distance[i] == 0x7fffffff)
                System.out.println("INF");
            else
                System.out.println(distance[i]);
        }
    }

}