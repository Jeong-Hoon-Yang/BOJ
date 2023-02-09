import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961 {
	static int[] bitter;
	static int[] sour;
	static int[] isSelected;
	static long sourRes = 1;
	static long bitterRes = 0;
	static long min = Long.MAX_VALUE;
	static int num;
	
	public static void subSet(int cnt) {
		if (cnt == num) {
			if (bitterRes == 0)
				return;
			long tmp = Math.abs(sourRes - bitterRes);
			if (min > tmp)
				min = tmp;
			return;
		}
		
		sourRes *= sour[cnt];
		bitterRes += bitter[cnt];
		subSet(cnt + 1);
		sourRes /= sour[cnt];
		bitterRes -= bitter[cnt];
		subSet(cnt + 1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		
		bitter = new int[num];
		sour = new int[num];
		
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= num; i++) {
			subSet(0);
		}
		
		System.out.println(min);
		br.close();
	}
}
