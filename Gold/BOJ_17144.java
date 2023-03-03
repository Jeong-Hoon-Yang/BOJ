import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144 {
    static int rowLen;
    static int colLen;
    static int[][] board;
    static AirCleaner airCleaner;
    static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    static class AirCleaner {
        int startRow;
        int endRow;

        public AirCleaner(int startRow) {
            this.startRow = startRow;
            this.endRow = startRow + 1;
        }
    }

    static class Dust {
        int row;
        int col;
        int amount;

        public Dust(int row, int col, int amount) {
            this.row = row;
            this.col = col;
            this.amount = amount;
        }
    }

    public static boolean check(int row, int col) {
        if (col == 0 && (row == airCleaner.startRow || row == airCleaner.endRow))
            return false;
        return row >= 0 && row < rowLen && col >= 0 && col < colLen;
    }

    public static void spread() {
        Queue<Dust> q = new ArrayDeque<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    q.add(new Dust(i, j, board[i][j]));
                    board[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            Dust tmp = q.poll();
            int spreadAmount = tmp.amount / 5;
            int spreadNum = 0;
            for (int i = 0; i < 4; i++) {
                int nextRow = tmp.row + dir[i][0];
                int nextCol = tmp.col + dir[i][1];

                if (!check(nextRow, nextCol))
                    continue;

                spreadNum++;
                board[nextRow][nextCol] += spreadAmount;
            }
            int remainAmount = tmp.amount - spreadAmount * spreadNum;
            board[tmp.row][tmp.col] += remainAmount;
        }

        board[airCleaner.startRow][0] = 0;
        board[airCleaner.endRow][0] = 0;
    }

    public static void rotate() {
        for (int i = airCleaner.startRow - 1; i > 0; i--) {
            board[i][0] = board[i - 1][0];
        }
        for (int i = 0; i < colLen - 1; i++) {
            board[0][i] = board[0][i + 1];
        }
        for (int i = 0; i < airCleaner.startRow; i++) {
            board[i][colLen - 1] = board[i + 1][colLen - 1];
        }
        for (int i = colLen - 1; i > 0; i--) {
            board[airCleaner.startRow][i] = board[airCleaner.startRow][i - 1];
        }
        for (int i = airCleaner.endRow + 1; i < rowLen - 1; i++) {
            board[i][0] = board[i + 1][0];
        }
        for (int i = 0; i < colLen - 1; i++) {
            board[rowLen - 1][i] = board[rowLen - 1][i + 1];
        }
        for (int i = rowLen - 1; i > airCleaner.endRow; i--) {
            board[i][colLen - 1] = board[i - 1][colLen - 1];
        }
        for (int i = colLen - 1; i > 0; i--) {
            board[airCleaner.endRow][i] = board[airCleaner.endRow][i - 1];
        }
    }

    public static int calc() {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sum += board[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());
        board = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colLen; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                    if (airCleaner == null)
                        airCleaner = new AirCleaner(i);
                }
            }
        }

        while (time-- > 0) {
            spread();
            rotate();
        }

        System.out.println(calc());
    }

}
