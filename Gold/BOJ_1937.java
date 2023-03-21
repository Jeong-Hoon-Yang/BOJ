import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int[][] maxDepth;
    static int size;
    static int[][] dir = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());

        board = new int[size][size];
        maxDepth = new int[size][size];

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 모든 index들에 대해서 탐색
         */
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                dfs(y, x);
            }
        }

        System.out.println(result);
    }

    private static int dfs(int y, int x) {
        /** 
         * 이미 탐색한 곳이면 maxDepth가 0이 아닌 값
         * 해당 값을 반환한 후 함수 종료
         */
        if (maxDepth[y][x] != 0)
            return maxDepth[y][x];
        
        int max = 1;
        /**
         * 4방향 탐색을 하며 최대로 깊게 들어가는 값을 찾기
         */
        for (int direction = 0; direction < 4; direction++) {
            int nextY = y + dir[direction][0];
            int nextX = x + dir[direction][1];
            if (check(nextY, nextX) && board[nextY][nextX] > board[y][x]) {
                if (maxDepth[nextY][nextX] != 0)
                    max = Math.max(max, 1 + maxDepth[nextY][nextX]);
                else {
                    max = Math.max(max, 1 + dfs(nextY, nextX));
                }
            }
        }

        result = Math.max(result, max);
        return maxDepth[y][x] = max;
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < size && x >= 0 && x < size;
    }
}