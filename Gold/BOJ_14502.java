import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {

    private static int rowLen;
    private static int colLen;
    private static int[][] board;
    private static int result;
    private static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

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

        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());

        board = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colLen; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0, board);

        System.out.println(result);
    }

    private static void combination(int cnt, int startIdx, int[][] board) {
        if (cnt == 3) {
            bfs(board);
            return;
        }

        int[][] copyBoard = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            copyBoard[i] = Arrays.copyOf(board[i], colLen);
        }

        for (int i = startIdx; i < rowLen * colLen; i++) {
            if (copyBoard[i / colLen][i % colLen] == 0) {
                copyBoard[i / colLen][i % colLen] = 1;
                combination(cnt + 1, i, copyBoard);
                copyBoard[i / colLen][i % colLen] = 0;
            }
        }
    }

    private static void bfs(int[][] board) {
        int[][] copyBoard = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            copyBoard[i] = Arrays.copyOf(board[i], colLen);
        }
        Queue<Pair> q = new ArrayDeque<>();
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (copyBoard[i][j] == 2) {
                    q.add(new Pair(i, j));
                }
            }
        }
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = tmp.first + dir[i][0];
                int nextCol = tmp.second + dir[i][1];
                if (check(nextRow, nextCol) && copyBoard[nextRow][nextCol] == 0) {
                    copyBoard[nextRow][nextCol] = 2;
                    q.add(new Pair(nextRow, nextCol));
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (copyBoard[i][j] == 0) {
                    cnt++;
                }
            }
        }
        result = Math.max(result, cnt);
    }

    private static boolean check(int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen;
    }
}
