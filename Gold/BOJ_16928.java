import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {
	static boolean[] visited = new boolean[101];
	static Map<Integer, Integer> map = new HashMap<>();
	static Queue<Place> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	
	static int minCnt = 0x7fffffff;
	
	static class Place {
		int pos;
		int cnt;
		
		public Place(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}
	}
	
	public static void bfs() {
		while(!queue.isEmpty()) {
			Place tmp = queue.poll();
			
			if (tmp.pos == 100) {
				sb.append(tmp.cnt);
				return;
			}
			
			for (int i = 1; i <= 6; i++) {
				int pos = tmp.pos + i;
				int cnt = tmp.cnt + 1;
				
				if (pos > 100)
					break;
				
				if (visited[pos]) continue;
				
				visited[pos] = true;
				
				if (map.containsKey(pos)) {
					pos = map.get(pos);
				}
				
				visited[pos] = true;
				
				queue.offer(new Place(pos, cnt));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int ladderNum = Integer.parseInt(st.nextToken());
		int snakeNum = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < ladderNum + snakeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map.put(from, to);
		}
		
		queue.offer(new Place(1, 0));
		bfs();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
