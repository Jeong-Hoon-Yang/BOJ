import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1966 {
	static Queue<Print> q = new LinkedList<>();
	static Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	
	static class Print {
		int index;
		int priority;
		
		public Print(int index, int priority) {
			super();
			this.index = index;
			this.priority = priority;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		while (tc-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			
			q.clear();
			pq.clear();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < num; i++) {
				int priority = Integer.parseInt(st.nextToken());
				q.add(new Print(i, priority));
				pq.add(priority);
			}
			
			int cnt = 0;
			
			while (!pq.isEmpty()) {
				int priority = pq.poll();
				Print p = null;
				
				while (!q.isEmpty()) {
					p = q.poll();
					if (p.priority == priority)
						break;
					q.add(p);
				}
				cnt++;
				if (p.index == idx) break;
			}
			
			sb.append(cnt).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
