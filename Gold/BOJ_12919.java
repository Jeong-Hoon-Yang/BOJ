import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String from = br.readLine();
		String to = br.readLine();

		Set<String> set = new HashSet<>();

		Queue<String> q = new ArrayDeque<>();
		q.add(from);

		while (!q.isEmpty()) {
			String tmp = q.poll();
			if (tmp.equals(to)) {
				System.out.println(1);
				return;
			}
			if (to.indexOf(0) == 'A' && tmp.indexOf(0) == 'B')
				continue;
			if (tmp.length() >= to.length())
				continue;
			if (!(to.contains(tmp) || to.contains(new StringBuilder(tmp).reverse().toString())))
				continue;

			StringBuilder sb = new StringBuilder();
			String toPush = sb.append(tmp).append("A").toString();
			if (!set.contains(toPush)) {
				q.add(toPush);
				set.add(toPush);
			}

			sb = new StringBuilder();
			toPush = sb.append(tmp).append("B").reverse().toString();
			if (!set.contains(toPush)) {
				q.add(toPush);
				set.add(toPush);
			}
		}

		System.out.println(0);
	}
}
