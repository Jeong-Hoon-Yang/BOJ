import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
    static int rowLen, colLen;
    static int[][] board;

    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static boolean check(int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen;
    }

    public static int bfs() {
        int max = 1;

        Queue<Pair> q = new ArrayDeque<>();

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == 1)
                    q.add(new Pair(i, j));
            }
        }

        while (!q.isEmpty()) {
            Pair tmp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = tmp.first + dir[i][0];
                int nextCol = tmp.second + dir[i][1];

                if (check(nextRow, nextCol) && board[nextRow][nextCol] == 0) {
                    q.add(new Pair(nextRow, nextCol));
                    board[nextRow][nextCol] = board[tmp.first][tmp.second] + 1;

                    if (board[nextRow][nextCol] > max)
                        max = board[nextRow][nextCol];
                }
            }
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == 0)
                    return -1;
            }
        }
        return max - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        colLen = Integer.parseInt(st.nextToken());
        rowLen = Integer.parseInt(st.nextToken());

        board = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colLen; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

}
