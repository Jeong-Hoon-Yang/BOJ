import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_3687 {
	
	static StringBuilder printString = new StringBuilder();
	
	public static void findMax(int num) {
		if (num % 2 == 0) {
			printString.append(1);
			num -= 2;
		} else {
			printString.append(7);
			num -= 3;
		}
		
		while (num > 0) {
			printString.append(1);
			num -= 2;
		}
		printString.append("\n");
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		
		long[] dp = new long[101];
		Arrays.fill(dp, Long.MAX_VALUE);
		
		dp[2] = 1;
		dp[3] = 7;
		dp[4] = 4;
		dp[5] = 2;
		dp[6] = 6;
		dp[7] = 8;
		dp[8] = 10;
		
		long[] arr = {1, 7, 4, 2, 0, 8};
		
		for (int i = 9; i <= 100; i++) {
			for (int j = 2; j <= 7; j++) {
				long tmp = dp[i - j] * 10 + arr[j - 2];
				dp[i] = Math.min(dp[i], tmp);
			}
		}
		
		for (int tc = 0; tc < testCase; tc++) {
			int num = Integer.parseInt(br.readLine());
			printString.append(dp[num]).append(" ");
			findMax(num);
		}
		
		bw.write(printString.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
