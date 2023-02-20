import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146 {
	static int size;
	static int[][] map;
	static boolean[][] visited;
	static int mapId = 2;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int bridgeMin = 0x7fffffff;

	static class Pair {
		int row;
		int col;
		int len;

		Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		Pair(int row, int col, int len) {
			this.row = row;
			this.col = col;
			this.len = len;
		}
	}
	
	public static void initVisited() {
		for (int i = 0; i < size; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	public static boolean check(int row, int col) {
		if (row < 0 || row >= size || col < 0 || col >= size)
			return false;
		if (visited[row][col])
			return false;
		return true;
	}

	public static void mapInit(int row, int col) {
		Queue<Pair> q = new ArrayDeque<>();

		q.add(new Pair(row, col));
		visited[row][col] = true;
		
		while (!q.isEmpty()) {
			Pair tmp = q.poll();
			row = tmp.row;
			col = tmp.col;
			for (int i = 0; i < 4; i++) {
				int nextRow = row + dir[i][0];
				int nextCol = col + dir[i][1];
				
				if (check(nextRow, nextCol) && map[nextRow][nextCol] == map[row][col]) {
					q.add(new Pair(nextRow, nextCol));
					visited[nextRow][nextCol] = true;
				}
			}
			map[tmp.row][tmp.col] = mapId;
		}
		
		mapId++;
	}
	
	public static void bfs(int row, int col) {
		Queue<Pair> q = new ArrayDeque<>();

		q.add(new Pair(row, col, 0));
		visited[row][col] = true;
		int island = map[row][col];
		
		while (!q.isEmpty()) {
			Pair tmp = q.poll();
			row = tmp.row;
			col = tmp.col;
			int len = tmp.len;
			
			for (int i = 0; i < 4; i++) {
				int nextRow = row + dir[i][0];
				int nextCol = col + dir[i][1];
				
				if (check(nextRow, nextCol) && map[nextRow][nextCol] != island) {
					if (map[nextRow][nextCol] != 0 && len < bridgeMin) {
						bridgeMin = len;
						return;
					}
					q.add(new Pair(nextRow, nextCol, len + 1));
					visited[nextRow][nextCol] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		visited = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] == 1)
					mapInit(i, j);
			}
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] != 0) {
					initVisited();
					bfs(i, j);
				}
			}
		}
		
		System.out.println(bridgeMin);
	}

}
