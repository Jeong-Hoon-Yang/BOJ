import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609 {
    static int size;
    static int colorNum;
    static int[][] board; // board의 값이 -2이면 빈칸
    static boolean[][] visited;
    static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int score;
    static int rainbowBlock;
    static Queue<Pair> deleteQueue = new ArrayDeque<>();

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Pair [row=" + row + ", col=" + col + "]";
        }
    }

    static boolean check(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size)
            return false;
        return true;
    }

    static void initVisited() {
        for (int i = 0; i < size; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    static void zeroToNotVisit() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0)
                    visited[i][j] = false;
            }
        }
    }

    static void gravity() {
        int[][] newBoard = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(newBoard[i], -2);
        }

        for (int col = 0; col < size; col++) {
            int idx = size - 1;
            for (int row = size - 1; row >= 0; row--) {
                if (board[row][col] == -1) {
                    idx = row - 1;
                    newBoard[row][col] = -1;
                    continue;
                }
                if (board[row][col] == -2)
                    continue;
                newBoard[idx--][col] = board[row][col];
            }
        }

        board = newBoard;
    }

    static void rotate() {
        int[][] newBoard = new int[size][size];

        for (int row = 0, newCol = 0; row < size; row++, newCol++) {
            for (int col = 0, newRow = size - 1; col < size; col++, newRow--) {
                newBoard[newRow][newCol] = board[row][col];
            }
        }

        board = newBoard;
    }

    static void bfs(int row, int col) {
        Queue<Pair> q = new ArrayDeque<>();
        Queue<Pair> save = new ArrayDeque<>();
        q.add(new Pair(row, col));
        save.add(new Pair(row, col));
        visited[row][col] = true;
        int value = board[row][col];
        int curRainbow = 0;

        while (!q.isEmpty()) {
            Pair tmp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = tmp.row + dir[i][0];
                int nextCol = tmp.col + dir[i][1];

                if (!check(nextRow, nextCol))
                    continue;

                if (!visited[nextRow][nextCol] && board[nextRow][nextCol] != -2
                        && (board[nextRow][nextCol] == value || board[nextRow][nextCol] == 0)) {
                    visited[nextRow][nextCol] = true;
                    if (board[nextRow][nextCol] == 0)
                        curRainbow++;
                    q.add(new Pair(nextRow, nextCol));
                    save.add(new Pair(nextRow, nextCol));
                }
            }
        }

        zeroToNotVisit();

        if (save.size() > deleteQueue.size()) {
            deleteQueue = save;
            rainbowBlock = curRainbow;
        } else if (save.size() == deleteQueue.size()) {
            if (curRainbow >= rainbowBlock) {
                deleteQueue = save;
                rainbowBlock = curRainbow;
            }
        }
    }

    static boolean delete() {
        if (deleteQueue.size() < 2)
            return false;

        score += Math.pow(deleteQueue.size(), 2);
        while (!deleteQueue.isEmpty()) {
            Pair tmp = deleteQueue.poll();
            board[tmp.row][tmp.col] = -2;
        }
        rainbowBlock = 0;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        colorNum = Integer.parseInt(st.nextToken());

        board = new int[size][size];
        visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            StringTokenizer tmp = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(tmp.nextToken());
            }
        }

        while (true) {
            initVisited();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] <= 0)
                        continue;
                    if (!visited[i][j] && board[i][j] != -1) {
                        bfs(i, j);
                    }
                }
            }
            if (!delete())
                break;
            gravity();
            rotate();
            gravity();
        }

        System.out.println(score);
    }

}
