import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1717 {
	static int n, m;
	static int[] set;
	
	public static int find(int x) {
		if (x == set[x])
			return x;
		return set[x] = find(set[x]);
	}
	
	public static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) return;
		set[y] = x;
	}
	
	public static boolean isUnion(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return true;
		else return false;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		set = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			set[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			switch (type) {
			case 0:
				merge(Math.min(a, b), Math.max(b, a));
				break;
			case 1:
				if (isUnion(a, b))
					sb.append("YES\n");
				else
					sb.append("NO\n");
				break;
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
