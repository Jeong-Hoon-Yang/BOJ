import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {
    static int boardSize;
    static int[][] board;
    static boolean[][] visited;
    static int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
    static Shark babyShark;
    static int time = 0;

    static class Shark {
        int row;
        int col;
        int size;
        int eat;

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
            this.size = 2;
            this.eat = 0;
        }
    }

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static boolean checkRange(int row, int col) {
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize)
            return false;
        return true;
    }

    public static boolean bfs(int row, int col) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(row, col));
        visited[row][col] = true;

        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            boolean flag = false;
            int sharkNextRow = boardSize;
            int sharkNextCol = boardSize;

            while (size-- > 0) {
                Pair tmp = q.poll();

                if (board[tmp.row][tmp.col] != 0 && board[tmp.row][tmp.col] < babyShark.size) {
                    flag = true;
                    if (tmp.row < sharkNextRow) {
                        sharkNextRow = tmp.row;
                        sharkNextCol = tmp.col;
                    } else if (tmp.row == sharkNextRow) {
                        if (tmp.col < sharkNextCol) {
                            sharkNextCol = tmp.col;
                        }
                    }
                }

                if (flag)
                    continue;

                for (int i = 0; i < 4; i++) {
                    int nextRow = tmp.row + dir[i][0];
                    int nextCol = tmp.col + dir[i][1];

                    if (checkRange(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                        if (board[nextRow][nextCol] == 0 || board[nextRow][nextCol] <= babyShark.size) {
                            q.add(new Pair(nextRow, nextCol));
                            visited[nextRow][nextCol] = true;
                            continue;
                        }
                    }
                }
            }

            if (flag) {
                time += distance;
                babyShark.eat++;
                board[babyShark.row][babyShark.col] = 0;
                board[sharkNextRow][sharkNextCol] = 9;
                babyShark.row = sharkNextRow;
                babyShark.col = sharkNextCol;
                if (babyShark.size <= 7 && babyShark.size == babyShark.eat) {
                    babyShark.eat = 0;
                    babyShark.size++;
                }
                return true;
            }

            distance++;

        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boardSize = Integer.parseInt(br.readLine());

        board = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9)
                    babyShark = new Shark(i, j);
            }
        }

        visited = new boolean[boardSize][boardSize];
        while (bfs(babyShark.row, babyShark.col)) {
            visited = new boolean[boardSize][boardSize];
        }

        System.out.println(time);
    }

}
