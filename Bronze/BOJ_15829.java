import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15829 {
	static final int M = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		long r = 1;
		long sum = 0;
		for (int i = 0; i < len; i++) {
			int a = str.charAt(i) - 'a' + 1;
			sum = (sum + a * r) % M;
			r = (r * 31) % M;	
		}
		
		System.out.println(sum);
	}

}
