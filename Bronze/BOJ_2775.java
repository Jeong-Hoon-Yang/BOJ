import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2775 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		while (tc-- > 0) {
			int floor = Integer.parseInt(br.readLine());
			int ho = Integer.parseInt(br.readLine());
			
			int[] peopleNum = new int[ho + 1];
			
			for (int i = 1; i <= ho; i++) {
				peopleNum[i] = i;
			}
			
			for (int i = 1; i <= floor; i++) {
				for (int j = 1; j <= ho; j++) {
					peopleNum[j] += peopleNum[j - 1];
				}
			}

			sb.append(peopleNum[ho]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
}
