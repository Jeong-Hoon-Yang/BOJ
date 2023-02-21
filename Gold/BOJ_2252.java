import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252 {

	static int peopleNum;
	static int inputNum;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] connectNum;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> q = new ArrayDeque<>();

	public static void pushQueue() {
		for (int i = 1; i <= peopleNum; i++) {
			if (!visited[i] && connectNum[i] == 0) {
				q.add(i);
				visited[i] = true;
			}
		}
	}

	public static void solve() {
		while(!q.isEmpty()) {
			int pNum = q.poll();
			sb.append(pNum).append(" ");
			
			for (int i = 0; i < graph.get(pNum).size(); i++) {
				connectNum[graph.get(pNum).get(i)]--;
			}
			
			pushQueue();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		peopleNum = Integer.parseInt(st.nextToken());
		inputNum = Integer.parseInt(st.nextToken());
		connectNum = new int[peopleNum + 1];
		visited = new boolean[peopleNum + 1];

		graph = new ArrayList<>();
		for (int i = 0; i < peopleNum + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < inputNum; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());

			graph.get(front).add(back);
			connectNum[back]++;
		}
		
		pushQueue();
		solve();
		
		System.out.println(sb.toString());
	}

}
