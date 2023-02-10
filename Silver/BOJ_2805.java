import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805 {
	static int num, maxlen;
	static int[] trees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		num = Integer.parseInt(st.nextToken());
		maxlen = Integer.parseInt(st.nextToken());
		
		trees = new int[num];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < num; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		
		long start = 0;
		long end = Long.MAX_VALUE;
		long result = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			
			if (check(mid)) {
				result = mid;
				start = mid + 1;
			}
			else
				end = mid - 1;
		}
		
		System.out.println(result);
	}
	
	public static boolean check(long len) {
		long sum = 0;
		
		for (int i = 0; i < num; i++) {
			if (trees[i] > len) {
				sum += (trees[i] - len);
			}
		}
		
		if (sum >= maxlen)
			return true;
		else
			return false;
	}

}
