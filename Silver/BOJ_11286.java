import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int num = Integer.parseInt(br.readLine());

		Queue<Integer> q = new PriorityQueue<>((i1, i2) -> {
			if (Math.abs(i1) == Math.abs(i2))
				return i1 - i2;
			return Math.abs(i1) - Math.abs(i2);
		});

		for (int i = 0; i < num; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if (tmp != 0) {
				q.add(tmp);
			} else {
				if (!q.isEmpty()) {
					sb.append(q.poll()).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
