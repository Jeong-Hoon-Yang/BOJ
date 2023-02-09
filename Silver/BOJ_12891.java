import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12891 {
	static int[] cnt;
	static int[] targetCnt;
	
	public static boolean check() {
		for (int i = 0; i < 4; i++) {
			if (cnt[i] < targetCnt[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int sLen = Integer.parseInt(st.nextToken());
		int pLen = Integer.parseInt(st.nextToken());
		
		
		String pwd = br.readLine();
		
		// 0 => A, 1 => C, 2 => G, 3 => T
		cnt = new int[4];
		targetCnt = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			targetCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < pLen; i++) {
			switch (pwd.charAt(i)) {
			case 'A':
				cnt[0]++;
				break;
			case 'C':
				cnt[1]++;
				break;
			case 'G':
				cnt[2]++;
				break;
			case 'T':
				cnt[3]++;
				break;
			}
		}
		
		int count = 0;
		
		if (check())
			count++;
		
		for (int i = 1; i <= sLen - pLen; i++) {
			switch (pwd.charAt(i - 1)) {
			case 'A':
				cnt[0]--;
				break;
			case 'C':
				cnt[1]--;
				break;
			case 'G':
				cnt[2]--;
				break;
			case 'T':
				cnt[3]--;
				break;
			}
			
			switch (pwd.charAt(i - 1 + pLen)) {
			case 'A':
				cnt[0]++;
				break;
			case 'C':
				cnt[1]++;
				break;
			case 'G':
				cnt[2]++;
				break;
			case 'T':
				cnt[3]++;
				break;
			}
			
			if (check())
				count++;
		}
		
		System.out.println(count);
	}

}
