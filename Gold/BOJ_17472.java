import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17472 {
    private static int rowLen;
    private static int colLen;
    private static int[][] board;
    private static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int[][] bridges;

    private static class Pair {
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

        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colLen; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1)
                    board[i][j] = -1;
            }
        }

        int cnt = 0;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == -1) {
                    bfs(i, j, ++cnt);
                }
            }
        }

        bridges = new int[cnt + 1][cnt + 1];

        for (int i = 0; i < rowLen; i++) {
            int bridgeFlag = 0;
            int bridgeLength = 0;
            for (int j = 1; j < colLen; j++) {
                if (bridgeFlag != 0) {
                    if (board[i][j] == 0) {
                        bridgeLength++;
                    } else {
                        if (bridgeLength >= 2) {
                            bridges[bridgeFlag][board[i][j]] = Math.min(
                                    bridges[bridgeFlag][board[i][j]] == 0 ? 0x7fffffff : bridges[bridgeFlag][board[i][j]],
                                    bridgeLength);
                            bridges[board[i][j]][bridgeFlag] = Math.min(
                                    bridges[board[i][j]][bridgeFlag] == 0 ? 0x7fffffff : bridges[board[i][j]][bridgeFlag],
                                    bridgeLength);
                        }
                        bridgeFlag = 0;
                        bridgeLength = 0;
                    }
                }
                if (board[i][j - 1] != 0 && board[i][j] == 0) {
                    bridgeFlag = board[i][j - 1];
                    bridgeLength = 1;
                }
            }
        }

        for (int i = 0; i < colLen; i++) {
            int bridgeFlag = 0;
            int bridgeLength = 0;
            for (int j = 1; j < rowLen; j++) {
                if (bridgeFlag != 0) {
                    if (board[j][i] == 0) {
                        bridgeLength++;
                    } else {
                        if (bridgeLength >= 2) {
                            bridges[bridgeFlag][board[j][i]] = Math.min(
                                    bridges[bridgeFlag][board[j][i]] == 0 ? 0x7fffffff : bridges[bridgeFlag][board[j][i]],
                                    bridgeLength);
                            bridges[board[j][i]][bridgeFlag] = Math.min(
                                    bridges[board[j][i]][bridgeFlag] == 0 ? 0x7fffffff : bridges[board[j][i]][bridgeFlag],
                                    bridgeLength);
                        }
                        bridgeFlag = 0;
                        bridgeLength = 0;
                    }
                }
                if (board[j - 1][i] != 0 && board[j][i] == 0) {
                    bridgeFlag = board[j - 1][i];
                    bridgeLength = 1;
                }
            }
        }

        prim(1);
    }

    private static void prim(int start) {
        Queue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.second - o2.second);
        boolean[] visited = new boolean[bridges.length];
        pq.add(new Pair(start, 0));
        int weight = 0;
        int sumCnt = 0;

        while (!pq.isEmpty()) {
            Pair tmp = pq.poll();
            int curPos = tmp.first;
            int curCost = tmp.second;
            if (visited[curPos]) continue;
            weight += curCost;
            sumCnt++;
            visited[curPos] = true;

            for (int i = 1; i < bridges[curPos].length; i++) {
                if (bridges[curPos][i] != 0) {
                    pq.add(new Pair(i, bridges[curPos][i]));
                }
            }
        }

        if (sumCnt == bridges.length - 1)
            System.out.println(weight == 0 ? -1 : weight);
        else
            System.out.println(-1);
    }

    private static void bfs(int row, int col, int cnt) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(row, col));
        board[row][col] = cnt;

        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int curRow = tmp.first;
            int curCol = tmp.second;
            for (int i = 0; i < 4; i++) {
                int nextRow = tmp.first + dir[i][0];
                int nextCol = tmp.second + dir[i][1];
                if (check(nextRow, nextCol)) {
                    board[nextRow][nextCol] = cnt;
                    q.add(new Pair(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean check(int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && board[row][col] == -1;
    }
}
