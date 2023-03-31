import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2239 {

    static final int BOARD_SIZE = 9;
    static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    static List<Pos> list = new ArrayList<>();
    static boolean finish;

    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < BOARD_SIZE; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = Integer.parseInt(tmp.substring(j, j + 1));
                if (board[i][j] == 0) {
                    list.add(new Pos(i, j));
                }
            }
        }

        solve(0);
    }

    private static void solve(int num) {
        if (num == list.size()) {
            finish = true;
            print();
            return;
        }
        if (finish) return;

        for (int i = 1; i <= 9; i++) {
            int row = list.get(num).row;
            int col = list.get(num).col;
            board[row][col] = i;
            if (!check(row, col)) {
                board[row][col] = 0;
                continue;
            }
            solve(num + 1);
            if (finish) return;
            board[row][col] = 0;
        }
    }

    private static void print() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean check(int row, int col) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (i != col && board[row][col] == board[row][i])
                return false;
            if (i != row && board[row][col] == board[i][col])
                return false;
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (i == row && j == col)
                    continue;
                if (board[row][col] == board[i][j])
                    return false;
            }
        }
        return true;
    }
}
