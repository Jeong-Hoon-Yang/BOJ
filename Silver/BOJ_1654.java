import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654 {
	static int haveNum;
	static int needNum;
	static int[] lanLines;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		haveNum = Integer.parseInt(st.nextToken());
		needNum = Integer.parseInt(st.nextToken());
		
		lanLines = new int[haveNum];
		int max = 0;
		
		for (int i = 0; i < lanLines.length; i++) {
			lanLines[i] = Integer.parseInt(br.readLine());
			if (max < lanLines[i])
				max = lanLines[i];
		}
		
		long start = 0;
		long end = Long.MAX_VALUE;
		
		while (start + 1 < end) {
			long mid = (start + end) / 2;
			
			if (check(mid))
				start = mid;
			else
				end = mid;
		}
		
		System.out.println(start);
	}
	
	static boolean check(long len) {
		long cnt = 0;
		
		for (int i = 0; i < lanLines.length; i++) {
			cnt += lanLines[i] / len;;
		}
		
		if (cnt >= needNum)
			return true;
		else
			return false;
	}
	
}
