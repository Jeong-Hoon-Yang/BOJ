import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019 {

	static Queue<Pair> q = new LinkedList<>();;
	static boolean[] visited = new boolean[10000];

	static class Pair {
		int num;
		int count;
		String op;

		public Pair(int num, int count, String op) {
			super();
			this.num = num;
			this.count = count;
			this.op = op;
		}
	}

	public static void process(Pair p) {
		int dOp = (p.num * 2) % 10000;
		int sOp = ((p.num - 1) < 0) ? 9999 : (p.num - 1);
		int lOp = ((p.num * 10) + (p.num * 10 / 10000)) % 10000;
		int rOp = ((p.num % 10) * 1000) + (p.num / 10);

		if (!visited[dOp]) {
			q.add(new Pair(dOp, p.count++, p.op + "D"));
			visited[dOp] = true;
		}
		if (!visited[sOp]) {
			q.add(new Pair(sOp, p.count++, p.op + "S"));
			visited[sOp] = true;
		}
		if (!visited[lOp]) {
			q.add(new Pair(lOp, p.count++, p.op + "L"));
			visited[lOp] = true;
		}
		if (!visited[rOp]) {
			q.add(new Pair(rOp, p.count++, p.op + "R"));
			visited[rOp] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int base = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());

			q.clear();
			Arrays.fill(visited, false);

			process(new Pair(base, 0, ""));
			while (!q.isEmpty()) {
				Pair p = q.poll();
				if (p.num == target) {
					sb.append(p.op).append("\n");
					break;
				}
				process(p);
			}
		}
		System.out.println(sb.toString());
	}

}
