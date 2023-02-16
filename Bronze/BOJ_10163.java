import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10163 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int paperNum = Integer.parseInt(br.readLine());
		
		int[][] paper = new int[1002][1002];
		
		for (int i = 1; i <= paperNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int xLen = Integer.parseInt(st.nextToken());
			int yLen = Integer.parseInt(st.nextToken());
			
			for (int x = startX; x < startX + xLen; x++) {
				for (int y = startY; y < startY + yLen; y++) {
					paper[x][y] = i;
				}
			}
		}
		
		int[] showPaper = new int[paperNum + 1];
		
		for (int i = 0; i <= 1001; i++) {
			for (int j = 0; j <= 1001; j++) {
				showPaper[paper[i][j]]++;
			}
		}
		
		for (int i = 1; i <= paperNum; i++) {
			System.out.println(showPaper[i]);
		}
	}

}
