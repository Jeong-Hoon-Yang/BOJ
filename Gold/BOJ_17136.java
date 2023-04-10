import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[10][10];
    static int[] used = new int[5 + 1];
    static int minCnt = 0x7fffffff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(minCnt == 0x7fffffff ? -1 : minCnt);
    }

    private static void dfs(int row, int col, int cnt) {
        if (row == 9 && col == 10) {
            minCnt = Math.min(minCnt, cnt);
            return;
        }

        if (cnt >= minCnt) return;
        if (col > 9) {
            dfs(row + 1, 0, cnt);
            return;
        }

        if (board[row][col] == 1) {
            for (int size = 5; size > 0; size--) {
                if (used[size] < 5 && poss(row, col, size)) {
                    used[size]++;
                    attach(row, col, size);
                    dfs(row, col + 1, cnt + 1);
                    attach(row, col, size);
                    used[size]--;
                }
            }
        } else {
            dfs(row, col + 1, cnt);
        }
    }

    private static void attach(int row, int col, int size) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] *= -1;
            }
        }
    }

    private static boolean poss(int row, int col, int size) {
        if (row + size > 10 || col + size > 10) return false;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (board[i][j] != 1)
                    return false;
            }
        }
        return true;
    }
}
