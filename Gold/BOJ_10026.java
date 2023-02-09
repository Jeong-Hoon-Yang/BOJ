import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_10026 {
	static char[][] picture;
	// picture에서 방문한 노드를 체크하기 위한 배열
	static boolean[][] visited;
	// 인접 노드를 접근하기 위한 배열
	static int[][] near = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	// BFS를 사용하기 위한 Stack
	static Stack<Pixel> s = new Stack<>();

	static class Pixel {
		int row;
		int col;
		char color;
		
		public Pixel(int row, int col, char color) {
			super();
			this.row = row;
			this.col = col;
			this.color = color;
		}
	}
	
	public static void init() {
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
		
		for (int i = 1; i < picture.length - 1; i++) {
			for (int j = 1; j < picture.length - 1; j++) {
				if (picture[i][j] == 'G')
					picture[i][j] = 'R';
			}
		}
	}

	public static void bfs() {		
		while (!s.empty()) {
			Pixel p = s.pop();
			for (int i = 0; i < near.length; i++) {
				int nextRow = p.row + near[i][0];
				int nextCol = p.col + near[i][1];
				
				// 방문한적이 없는 노드이고, stack에 있는 노드와 색이 같다면 stack에 push
				if (!visited[nextRow][nextCol] && picture[nextRow][nextCol] == p.color) {
					visited[nextRow][nextCol] = true;
					s.push(new Pixel(nextRow, nextCol, p.color));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int len = Integer.parseInt(br.readLine());

		// 0번 인덱스와 len + 1번 인덱스는 버리기
		picture = new char[len + 2][len + 2];
		visited = new boolean[len + 2][len + 2];

		for (int i = 1; i <= len; i++) {
			String str = br.readLine();
			for (int j = 1; j <= len; j++) {
				picture[i][j] = str.charAt(j - 1);
			}
		}
		
		int cnt = 0;
		
		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= len; j++) {
				if (visited[i][j]) continue;
				
				s.push(new Pixel(i, j, picture[i][j]));
				bfs();
				
				cnt++;
			}
		}
		
		sb.append(cnt).append(" ");
		
		cnt = 0;
		init();
		
		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= len; j++) {
				if (visited[i][j]) continue;
				
				s.push(new Pixel(i, j, picture[i][j]));
				bfs();
				
				cnt++;
			}
		}
		
		sb.append(cnt);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
