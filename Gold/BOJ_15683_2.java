import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683_2 {

    static int rowLen, colLen;
    static char[][] board;
    static List<Cctv> cctvs = new ArrayList<>();
    static int min = 0x7fffffff;

    static class Cctv {
        int row;
        int col;
        char type;

        public Cctv(int row, int col, char type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }

    // dir -> 0 : Right, 1 : Down, 2 : Left, 3 : Up
    public static void watch(int row, int col, char[][] board, int dir) {
        if (dir == 0) {
            int idx = col + 1;
            while (idx < colLen) {
                if (board[row][idx] == '6')
                    break;
                if (board[row][idx] == '0')
                    board[row][idx] = '#';
                idx++;
            }
        } else if (dir == 1) {
            int idx = row + 1;
            while (idx < rowLen) {
                if (board[idx][col] == '6')
                    break;
                if (board[idx][col] == '0')
                    board[idx][col] = '#';
                idx++;
            }
        } else if (dir == 2) {
            int idx = col - 1;
            while (idx >= 0) {
                if (board[row][idx] == '6')
                    break;
                if (board[row][idx] == '0')
                    board[row][idx] = '#';
                idx--;
            }
        } else if (dir == 3) {
            int idx = row - 1;
            while (idx >= 0) {
                if (board[idx][col] == '6')
                    break;
                if (board[idx][col] == '0')
                    board[idx][col] = '#';
                idx--;
            }
        }
    }

    public static void recurcive(int cnt, char[][] board) {
        if (cnt == cctvs.size()) {
            int count = 0;
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    if (board[i][j] == '0')
                        count++;
                }
            }
            min = Math.min(min, count);
            return;
        }

        if (cctvs.get(cnt).type == '1') {
            for (int i = 0; i < 4; i++) {
                char[][] newBoard = new char[rowLen][colLen];
                for (int j = 0; j < rowLen; j++) {
                    newBoard[j] = Arrays.copyOf(board[j], colLen);
                }
                watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, i);
                recurcive(cnt + 1, newBoard);
            }
        } else if (cctvs.get(cnt).type == '2') {
            for (int i = 0; i < 2; i++) {
                char[][] newBoard = new char[rowLen][colLen];
                for (int j = 0; j < rowLen; j++) {
                    newBoard[j] = Arrays.copyOf(board[j], colLen);
                }
                watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, i);
                watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, i + 2);
                recurcive(cnt + 1, newBoard);
            }
        } else if (cctvs.get(cnt).type == '3') {
            for (int i = 0; i < 4; i++) {
                char[][] newBoard = new char[rowLen][colLen];
                for (int j = 0; j < rowLen; j++) {
                    newBoard[j] = Arrays.copyOf(board[j], colLen);
                }
                watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, i);
                watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, (i + 1) % 4);
                recurcive(cnt + 1, newBoard);
            }
        } else if (cctvs.get(cnt).type == '4') {
            for (int i = 0; i < 4; i++) {
                char[][] newBoard = new char[rowLen][colLen];
                for (int j = 0; j < rowLen; j++) {
                    newBoard[j] = Arrays.copyOf(board[j], colLen);
                }
                watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, i);
                watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, (i + 1) % 4);
                watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, (i + 2) % 4);
                recurcive(cnt + 1, newBoard);
            }
        } else if (cctvs.get(cnt).type == '5') {
            char[][] newBoard = new char[rowLen][colLen];
            for (int j = 0; j < rowLen; j++) {
                newBoard[j] = Arrays.copyOf(board[j], colLen);
            }
            watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, 0);
            watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, 1);
            watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, 2);
            watch(cctvs.get(cnt).row, cctvs.get(cnt).col, newBoard, 3);
            recurcive(cnt + 1, newBoard);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new char[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colLen; j++) {
                board[i][j] = st.nextToken().charAt(0);
                if (board[i][j] != '0' && board[i][j] != '6')
                    cctvs.add(new Cctv(i, j, board[i][j]));
            }
        }

        recurcive(0, board);

        System.out.println(min);
    }

}
