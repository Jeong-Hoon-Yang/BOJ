import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_17626 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[50001];
		Arrays.fill(dp, 50000);
		int tmp = 1;
		while (Math.pow(tmp, 2) <= 50000) {
			int idx = (int)Math.pow(tmp++, 2);
			dp[idx] = 1;
		}
		
		for (int i = 1; i <= n; i++) {
			tmp = 1;
			while (Math.pow(tmp, 2) < i) {
				int idx = (int)Math.pow(tmp++, 2);
				if ((dp[i - idx] + 1) == 50000) continue;
				dp[i] = Math.min(dp[i - idx] + 1, dp[i]);
			}
		}
		
		sb.append(dp[n]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
