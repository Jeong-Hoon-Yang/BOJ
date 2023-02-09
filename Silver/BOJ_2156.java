import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3687 {
	static int[] drinks;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		drinks = new int[num + 1];
		dp = new int[num + 1];
		
		for (int i = 1; i <= num; i++) {
			drinks[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1] = drinks[1];
		
		if (num > 1)
			dp[2] = drinks[1] + drinks[2];
		
		for (int i = 3; i <= num; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + drinks[i - 1] + drinks[i], dp[i - 2] + drinks[i]));
		}
		
		System.out.println(dp[num]);
	}

}
