import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992 {

    static char[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void recursive(int startRow, int startCol, int endRow, int endCol, int size) {

        if (size == 1) {
            sb.append(board[startRow][startCol]);
            return;
        }

        boolean flag = true;
        int num = board[startRow][startCol];
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (board[i][j] != num) {
                    flag = false;
                    break;
                }
            }
            if (!flag)
                break;
        }

        if (!flag) {
            sb.append("(");
            recursive(startRow, startCol, startRow + (size / 2), startCol + (size / 2), size / 2);
            recursive(startRow, startCol + (size / 2), startRow + (size / 2), endCol, size / 2);
            recursive(startRow + (size / 2), startCol, endRow, startCol + (size / 2), size / 2);
            recursive(startRow + (size / 2), startCol + (size / 2), endRow, endCol, size / 2);
            sb.append(")");
        } else {
            sb.append(num - '0');
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());

        board = new char[len][len];

        for (int i = 0; i < len; i++) {
            board[i] = br.readLine().toCharArray();
        }

        recursive(0, 0, len, len, len);

        System.out.println(sb.toString());
    }

}