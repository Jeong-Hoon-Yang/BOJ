import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2096 {

  public static boolean check(int col, int size) {
    return col >= 0 && col < size;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int size = Integer.parseInt(br.readLine());

    int[] board = new int[3];
    int[][] minDP = new int[2][3];
    int[][] maxDP = new int[2][3];
    Arrays.fill(minDP[1], 0x7fffffff);
    Arrays.fill(maxDP[1], -1);

    for (int i = 0; i < size; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      // board 값 입력
      for (int idx = 0; idx < 3; idx++) {
        board[idx] = Integer.parseInt(st.nextToken());
      }

      // 첫 입력의 경우 dp의 첫 row 초기화
      if (i == 0) {
        for (int idx = 0; idx < 3; idx++) {
          minDP[0][idx] = board[idx];
          maxDP[0][idx] = board[idx];
        }
        continue;
      }

      // column의 길이만큼 반복
      for (int idx = 0; idx < 3; idx++) {
        for (int dir = -1; dir <= 1; dir++) {
          // 인접한 아래층 배열로 접근
          int nextCol = idx + dir;
          if (!check(nextCol, 3))
            continue;
          // 인접한 아래층 dp 배열의 값들을 최솟값 / 최댓값을 구하기
          minDP[1][nextCol] = Math.min(minDP[1][nextCol], minDP[0][idx] + board[nextCol]);
          maxDP[1][nextCol] = Math.max(maxDP[1][nextCol], maxDP[0][idx] + board[nextCol]);
        }
      }

      // 아래층 dp 배열을 윗층으로 올려추고, 아랫층의 값을 초기화
      for (int idx = 0; idx < 3; idx++) {
        minDP[0][idx] = minDP[1][idx];
        maxDP[0][idx] = maxDP[1][idx];
        minDP[1][idx] = 0x7fffffff;
        maxDP[1][idx] = -1;
      }
    }

    // 최종적으로 구해진 DP 값에서 각각 최댓값, 최솟값을 구하기
    int min = 0x7fffffff;
    int max = -1;
    for (int i = 0; i < 3; i++) {
      min = Math.min(min, minDP[0][i]);
      max = Math.max(max, maxDP[0][i]);
    }

    System.out.println(max + " " + min);
  }
}