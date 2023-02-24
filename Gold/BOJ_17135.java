import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135 {

    static int rowNum;
    static int colNum;
    static int limit;
    static int[][] board;
    static int kill;
    static int[] archer = new int[3];
    static int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 } };
    static int killMax = 0;

    static class Shot {
        int row;
        int col;
        int cnt;

        Shot(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }

    public static boolean checkRange(int row, int col) {
        if (row < 0 || row > rowNum || col < 0 || col >= colNum)
            return false;
        return true;
    }

    public static int[][] move(int[][] board) {
        for (int i = rowNum - 1; i > 0; i--) {
            board[i] = Arrays.copyOf(board[i - 1], colNum);
        }
        Arrays.fill(board[0], 0);

        return board;
    }

    public static boolean liveEnemy(int[][] board) {
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (board[i][j] != 0)
                    return true;
            }
        }
        return false;
    }

    public static void bfs(int[][] board) {
        while (liveEnemy(board)) {
            Queue<Shot> killPoss = new ArrayDeque<>();
            for (int aNum = 0; aNum < 3; aNum++) {
                Queue<Shot> q = new ArrayDeque<>();
                q.add(new Shot(rowNum, archer[aNum], 0));

                while (!q.isEmpty()) {
                    Shot tmp = q.poll();
                    if (board[tmp.row][tmp.col] != 0) {
                        killPoss.add(new Shot(tmp.row, tmp.col, 0));
                        break;
                    }
                    if (tmp.cnt == limit)
                        continue;
                    for (int i = 0; i < 3; i++) {
                        int nextRow = tmp.row + dir[i][0];
                        int nextCol = tmp.col + dir[i][1];
                        if (!checkRange(nextRow, nextCol))
                            continue;
                        q.add(new Shot(nextRow, nextCol, tmp.cnt + 1));
                    }
                }
            }

            while (!killPoss.isEmpty()) {
                Shot tmp = killPoss.poll();
                if (board[tmp.row][tmp.col] != 0) {
                    board[tmp.row][tmp.col] = 0;
                    kill++;
                }
            }

            board = move(board);
        }

        killMax = Math.max(killMax, kill);
    }

    public static void comb(int start, int cnt) {
        if (cnt == 3) {
            kill = 0;
            int[][] newBoard = new int[rowNum + 1][colNum];
            for (int i = 0; i <= rowNum; i++) {
                newBoard[i] = Arrays.copyOf(board[i], colNum);
            }
            bfs(newBoard);
            return;
        }
        for (int i = start; i < colNum; i++) {
            archer[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rowNum = Integer.parseInt(st.nextToken());
        colNum = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        board = new int[rowNum + 1][colNum];

        for (int i = 0; i < rowNum; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colNum; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);

        System.out.println(killMax);
    }

}
