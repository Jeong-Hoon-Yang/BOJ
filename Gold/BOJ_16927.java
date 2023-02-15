import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16927 {
	static int row, col, rotNum;
	static int[][] arr;
	static StringBuilder sb;

	public static void rotate(int minRow, int maxRow, int minCol, int maxCol) {
		if (minRow > maxRow || minCol > maxCol)
			return;

		int divNum = (maxRow - minRow + 1) * 2 + (maxCol - minCol + 1) * 2 - 4;
		int rotTmp = rotNum;
		if (divNum != 0)
			rotTmp = rotNum % divNum;

		while (rotTmp-- > 0) {
			int tmp = arr[minRow][minCol];
			// top
			for (int i = minCol; i < maxCol; i++) {
				arr[minRow][i] = arr[minRow][i + 1];
			}

			for (int i = minRow; i < maxRow; i++) {
				arr[i][maxCol] = arr[i + 1][maxCol];
			}

			for (int i = maxCol; i > minCol; i--) {
				arr[maxRow][i] = arr[maxRow][i - 1];
			}

			for (int i = maxRow; i > minRow; i--) {
				arr[i][minCol] = arr[i - 1][minCol];
			}
			
			arr[minRow + 1][minCol] = tmp;
		}

		rotate(minRow + 1, maxRow - 1, minCol + 1, maxCol - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		rotNum = Integer.parseInt(st.nextToken());

		arr = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rotate(0, row - 1, 0, col - 1);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
