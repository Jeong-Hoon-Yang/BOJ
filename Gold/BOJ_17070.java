import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] board = new int[size + 1][size + 1];
        int[][][] dpBoard = new int[3][size + 1][size + 1];   // 0 : 가로 1 : 대각선 2 : 세로

        for (int i = 1; i <= size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dpBoard[0][1][2] = 1;

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (board[i][j] != 1) {
                    dpBoard[0][i][j] += dpBoard[0][i][j - 1] + dpBoard[1][i][j - 1];
                    dpBoard[2][i][j] += dpBoard[2][i - 1][j] + dpBoard[1][i - 1][j];
                }
                if (board[i][j] != 1 && board[i - 1][j] != 1 && board[i][j - 1] != 1)
                    dpBoard[1][i][j] += dpBoard[0][i - 1][j - 1] + dpBoard[1][i - 1][j - 1] + dpBoard[2][i - 1][j - 1];
            }
        }

        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += dpBoard[i][size][size];
        }
        System.out.println(sum);
    }
}
