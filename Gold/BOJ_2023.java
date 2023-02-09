import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2023 {
	static int len;
	static StringBuilder sb;
	
	public static boolean isPrime(int num) {
		if (num < 2) return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	public static void recurcive(int cnt, int num) {
		if (cnt == len) {
			sb.append(num).append("\n");
			return;
		}

		int[] arr = { 1, 3, 7, 9 };
		
		for (int i = 0; i < arr.length; i++) {
			int tmp = num * 10 + arr[i];
			if (isPrime(tmp))
				recurcive(cnt + 1, tmp);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		len = Integer.parseInt(br.readLine());

		int[] arr = { 2, 3, 5, 7 };
		
		for (int i = 0; i < arr.length; i++) {
			recurcive(1, arr[i]);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
