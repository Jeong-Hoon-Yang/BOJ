import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2869 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int upLen = Integer.parseInt(st.nextToken());
		int downLen = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());

		int cnt = ((target - downLen) % (upLen - downLen) == 0)?
					(target - downLen) / (upLen - downLen) :
					(target - downLen) / (upLen - downLen) + 1;
		
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
