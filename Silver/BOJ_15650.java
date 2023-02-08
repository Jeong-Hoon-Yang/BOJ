import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_15650 {

	static int[] nums;
	static int n, m;
	static boolean[] isSelected;
	static StringBuilder sb;

	public static void recursive(int cnt) {
		if (cnt == m) {
			for (int i : nums) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = (cnt - 1) >= 0 ? nums[cnt - 1] : 1; i <= n; i++) {
			if (isSelected[i])
				continue;

			nums[cnt] = i;
			isSelected[i] = true;
			recursive(cnt + 1);
			isSelected[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		nums = new int[m];
		isSelected = new boolean[n + 1];

		recursive(0);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
