import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14585 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int plcNum = Integer.parseInt(st.nextToken());
		int candyCnt = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[301][301];
		int[][] board = new int[301][301];
		
		for (int i = 0; i < 301; i++) {
		Arrays.fill(dp[i], 0);
		}
		
		for (int i = 0; i < plcNum; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			board[x][y] = candyCnt;
		}
		
		for (int i = 0; i < 301; i++) {
			for (int j = 0; j < 301; j++) {
				dp[i][j] = Long.max((i > 0) ? dp[i - 1][j] : 0, (j > 0) ? dp[i][j - 1] : 0);
				if (board[i][j] != 0) {
					dp[i][j] += Long.max(0, board[i][j] - (i + j));
				}
			}
		}
		
		System.out.println(dp[300][300]);
	}

}
 
