import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] board;
	static int row, col;
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		board = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		while (true) {
			if (allZero()) {
				System.out.println(0);
				return;
			}
			if (!isOne()) {
				System.out.println(cnt);
				return;
			}
			play();
			cnt++;
		}
	}

	private static void play() {
		int[][] tmpBoard = new int[row][col];
		for (int i = 0; i < row; i++) {
			tmpBoard[i] = Arrays.copyOf(board[i], col);
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nextRow = i + dir[k][0];
					int nextCol = j + dir[k][1];
					if (check(nextRow, nextCol) && board[nextRow][nextCol] == 0)
						cnt++;
				}
				tmpBoard[i][j] = board[i][j] - cnt >= 0 ? board[i][j] - cnt : 0;
			}
		}

		for (int i = 0; i < row; i++) {
			board[i] = Arrays.copyOf(tmpBoard[i], col);
		}
	}

	public static boolean allZero() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] != 0)
					return false;
			}
		}
		return true;
	}

	public static boolean isOne() {
		int[][] tmpBoard = new int[row][col];
		for (int i = 0; i < row; i++) {
			tmpBoard[i] = Arrays.copyOf(board[i], col);
		}

		return bfs(tmpBoard);
	}

	private static boolean bfs(int[][] tmpBoard) {
		boolean[][] visited = new boolean[row][col];
		Queue<Pair> q = new ArrayDeque<>();

		boolean flag = false;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (tmpBoard[i][j] == 0) {
					visited[i][j] = true;
					continue;
				}
				if (visited[i][j])
					continue;
				if (flag)
					return false;

				flag = true;
				visited[i][j] = true;
				q.add(new Pair(i, j));

				while (!q.isEmpty()) {
					Pair tmp = q.poll();
					for (int k = 0; k < 4; k++) {
						int nextRow = tmp.row + dir[k][0];
						int nextCol = tmp.col + dir[k][1];
						if (check(nextRow, nextCol) && !visited[nextRow][nextCol] && tmpBoard[nextRow][nextCol] != 0) {
							visited[nextRow][nextCol] = true;
							q.add(new Pair(nextRow, nextCol));
						}
					}
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!visited[i][j])
					return false;
			}
		}
		return true;
	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < row && y >= 0 && y < col;
	}

	static class Pair {
		int row;
		int col;

		public Pair(int x, int y) {
			this.row = x;
			this.col = y;
		}
	}
}
