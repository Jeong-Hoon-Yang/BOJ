import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {

    static int rowSize, colSize;
    static int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
    static char[][] board;
    static boolean[][] visited;
    static int res;
    static boolean flag;

    public static boolean checkRange(int row, int col) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize)
            return false;
        return true;
    }

    public static void dfs(int row, int col) {
        if (col == colSize - 1) {
            res++;
            visited[row][col] = true;
            flag = true;
            return;
        }

        // System.out.println("row : " + row + ", col : " + col);

        for (int i = 0; i < 3; i++) {
            int nextRow = row + dir[i][0];
            int nextCol = col + dir[i][1];
            if (checkRange(nextRow, nextCol) && board[nextRow][nextCol] == '.' && !visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol);
                if (flag)
                    return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        board = new char[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            String str = br.readLine();
            for (int j = 0; j < colSize; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < rowSize; i++) {
            flag = false;
            visited[i][0] = true;
            dfs(i, 0);
            visited[i][0] = false;
            // System.out.println();
        }

        System.out.println(res);
    }

}
