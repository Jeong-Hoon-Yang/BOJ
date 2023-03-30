import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4485 {

    static final int MAX_DIST = 1_000_000_000;
    static int[][] board;
    static int[][] dist;
    static int size;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void init() {
        board = new int[size][size];
        dist = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dist[i][j] = MAX_DIST;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int problemNum = 0;
        while (true) {
            size = Integer.parseInt(br.readLine());
            if (size == 0) {
                System.out.println(sb.toString());
                return;
            }
            sb.append("Problem ").append(++problemNum).append(": ");

            init();

            for (int i = 0; i < size; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();

            sb.append(dist[size - 1][size - 1]).append("\n");
        }
    }

    private static void dijkstra() {
        Queue<Pair<Integer, Pair<Integer, Integer>>> pq = new PriorityQueue<>((o1, o2) -> o1.first - o2.first);
        pq.add(new Pair(board[0][0], new Pair(0, 0)));

        dist[0][0] = board[0][0];

        while (!pq.isEmpty()) {
            Pair<Integer, Pair<Integer, Integer>> tmp = pq.poll();
            int curRow = tmp.second.first;
            int curCol = tmp.second.second;
            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + dir[i][0];
                int nextCol = curCol + dir[i][1];
                if (check(nextRow, nextCol)) {
                    int nextCost = board[nextRow][nextCol];
                    if (dist[nextRow][nextCol] > dist[curRow][curCol] + nextCost) {
                        dist[nextRow][nextCol] = dist[curRow][curCol] + nextCost;
                        pq.add(new Pair(dist[nextRow][nextCol], new Pair(nextRow, nextCol)));
                    }
                }
            }
        }
    }

    private static boolean check(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}
