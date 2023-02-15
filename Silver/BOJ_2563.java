import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2563 {
	
	static int[][] paper = new int[100][100];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int leftDiff = Integer.parseInt(st.nextToken());
			int downDiff = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					paper[downDiff + j][leftDiff + k] = 1;
				}
			}
		}
		
		int cnt = 0;
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (paper[i][j] == 1)
					cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
