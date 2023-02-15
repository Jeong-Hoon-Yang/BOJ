import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11403 {
	static int len;
	static int[][] arr;
	static int[][] dist;
	static final int INF = 10001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		len = Integer.parseInt(br.readLine());

		arr = new int[len + 1][len + 1];
		dist = new int[len + 1][len + 1];

		for (int i = 1; i <= len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= len; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0)
					dist[i][j] = arr[i][j];
				else
					dist[i][j] = INF;
			}
		}
		
		for (int k = 1; k <= len; k++) {
			for (int i = 1; i <= len; i++) {
				for (int j = 1; j <= len; j++) {
					dist[i][j] = Math.min(dist[i][j], (dist[i][k] == 1 && dist[k][j] == 1) ? 1 : INF);
				}
			}
		}
		
		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= len; j++) {
				if (dist[i][j] == 1)
					sb.append(1).append(" ");
				else
					sb.append(0).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
