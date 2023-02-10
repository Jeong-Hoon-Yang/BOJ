import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2839 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sugar = Integer.parseInt(br.readLine());

		int[] dp = new int[5001];

		Arrays.fill(dp, -1);
		dp[3] = 1;
		dp[5] = 1;

		for (int i = 6; i <= sugar; i++) {
			dp[i] = Math.min(
					(dp[i - 3] != -1 ? dp[i - 3] : 5000),
					(dp[i - 5] != -1 ? dp[i - 5] : 5000)
					) + 1;
			if (dp[i] == 5001)
				dp[i] = -1;
		}
		
		System.out.println(dp[sugar]);
	}

}
