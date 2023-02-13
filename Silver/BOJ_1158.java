import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1158 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> list = new LinkedList<>();
		
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		
		int idx = 0;
		
		for (int i = 0; i < n; i++) {
			idx += k - 1;
			if (idx >= list.size())
				idx = idx % list.size();
			sb.append(list.get(idx)).append(", ");
			list.remove(idx);
		}
		String str = sb.substring(0, sb.length() - 2);
		System.out.println("<" + str + ">");
	}
	
}
