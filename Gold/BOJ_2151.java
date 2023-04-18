import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2151 {
    static char[][] board;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static Point sPoint;
    static Point ePoint;

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Mirror {
        Point point;
        int direction;
        // direction : true => 다음에 들어올 애들은 0, 2
        // direction : false => 다음에 들어올 애들은 1, 3

        public Mirror(Point point, int direction) {
            this.point = point;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        board = new char[size][size];

        for (int i = 0; i < size; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < size; j++) {
                board[i][j] = tmp.charAt(j);
                if (board[i][j] == '#' && sPoint == null)
                    sPoint = new Point(i, j);
                else if (board[i][j] == '#' && ePoint == null)
                    ePoint = new Point(i, j);
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Mirror> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[board.length][board.length][4];
        q.add(new Mirror(sPoint, 0));
        q.add(new Mirror(sPoint, 1));
        q.add(new Mirror(sPoint, 2));
        q.add(new Mirror(sPoint, 3));
        visited[sPoint.row][sPoint.col][0] = true;
        visited[sPoint.row][sPoint.col][1] = true;
        visited[sPoint.row][sPoint.col][2] = true;
        visited[sPoint.row][sPoint.col][3] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            int qSize = q.size();
            while (qSize --> 0) {
                Mirror tmp = q.poll();
                int curRow = tmp.point.row;
                int curCol = tmp.point.col;
                int curDir = tmp.direction;

                for (int i = 0; i < 4; i++) {
                    if (curDir % 2 == 1 && (i == 1 || i == 3))
                        continue;
                    if (curDir % 2 == 0 && (i == 0 || i == 2))
                        continue;
                    int nextRow = curRow;
                    int nextCol = curCol;
                    while (check(nextRow + dir[i][0], nextCol + dir[i][1]) ) {
                        nextRow += dir[i][0];
                        nextCol += dir[i][1];
                        if (board[nextRow][nextCol] == '*')
                            break;

                        if (visited[nextRow][nextCol][curDir])
                            continue;
                        if (board[nextRow][nextCol] == '#') {
                            return cnt;
                        }
                        if (board[nextRow][nextCol] == '.') {
                            visited[nextRow][nextCol][curDir] = true;
                            continue;
                        }
                        if (board[nextRow][nextCol] == '!') {
                            visited[nextRow][nextCol][curDir] = true;
                            if (curDir % 2 == 0) {
                                q.add(new Mirror(new Point(nextRow, nextCol), 1));
                                q.add(new Mirror(new Point(nextRow, nextCol), 3));
                            }
                            else {
                                q.add(new Mirror(new Point(nextRow, nextCol), 0));
                                q.add(new Mirror(new Point(nextRow, nextCol), 2));
                            }
                            continue;
                        }
                    }
                }
            }
            cnt++;
        }

        return cnt;
    }

    private static boolean check(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board.length;
    }
}