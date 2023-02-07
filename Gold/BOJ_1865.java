import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1865 {
	static class Pair {
		int to;
		int time;
		
		Pair(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.parseInt(st.nextToken());
		
		testcase:
		for (int testCase = 0; testCase < tc; testCase++) {
			st = new StringTokenizer(br.readLine());
			int pointNum, roadNum, wormNum;
			pointNum = Integer.parseInt(st.nextToken());
			roadNum = Integer.parseInt(st.nextToken());
			wormNum = Integer.parseInt(st.nextToken());
			
			int[] dist = new int[pointNum + 1];
			
			ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
			for (int i = 0; i <= pointNum; i++) {
				adj.add(new ArrayList<>());
			}
			
			for (int i = 0; i < roadNum + wormNum; i++) {
				st = new StringTokenizer(br.readLine());
				int startPoint, endPoint, weight;
				startPoint = Integer.parseInt(st.nextToken());
				endPoint = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());
				
				if (i < roadNum) {
					adj.get(startPoint).add(new Pair(endPoint, weight));
					adj.get(endPoint).add(new Pair(startPoint, weight));
				} else {
					adj.get(startPoint).add(new Pair(endPoint, -weight));
				}
			}
			
			for (int i = 1; i <= pointNum; i++) {
				if (bellmanFord(adj, dist, i)) {
					System.out.println("YES");
					continue testcase;
				}
			}
			System.out.println("NO");
			continue;
		}
	}
	
	public static boolean bellmanFord(ArrayList<ArrayList<Pair>> adj, int[] dist, int start) {
		Arrays.fill(dist, 1000000000);
		dist[start] = 0;
		boolean updated = false;
		
		for (int i = 1; i < dist.length; i++) {
			updated = false;
			for (int pointNum = 1; pointNum <= dist.length - 1; pointNum++) {
				for (int adjNum = 0; adjNum < adj.get(pointNum).size(); adjNum++) {
					int toPoint = adj.get(pointNum).get(adjNum).to;
					int cost = adj.get(pointNum).get(adjNum).time;
					if (dist[toPoint] > dist[pointNum] + cost) {
						dist[toPoint] = dist[pointNum] + cost;
						updated = true;
					}
				}
			}
			
			if (!updated)
				break;
		}
		
		if (updated) {
			for (int i = 1; i <= dist.length; i++) {
				for (Pair p : adj.get(i)) {
					if (dist[i] != 1000000000 && dist[p.to] > dist[i] + p.time) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
