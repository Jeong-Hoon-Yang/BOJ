import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16935 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int row, col;
	static int cmdNum;
	static int[][] arr;

	public static void flip(int type) {
		if (type == 1) { // type이 1이면 상하 뒤집기
			for (int i = 0; i < row / 2; i++) {
				for (int j = 0; j < col; j++) {
					int tmp = arr[i][j];
					arr[i][j] = arr[row - 1 - i][j];
					arr[row - 1 - i][j] = tmp;
				}
			}
		} else if (type == 2) { // type이 2이면 좌우 뒤집기
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col / 2; j++) {
					int tmp = arr[i][j];
					arr[i][j] = arr[i][col - 1 - j];
					arr[i][col - 1 - j] = tmp;
				}
			}
		}
	}

	public static void rotate(int type) {
		int[][] newArr = new int[col][row];

		if (type == 3) { // type이 3이면 오른쪽으로 90도
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					newArr[j][row - 1 - i] = arr[i][j];
				}
			}
		} else if (type == 4) { // type이 4이면 왼쪽으로 90도
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					newArr[col - 1 - j][i] = arr[i][j];
				}
			}
		}
		
		// row와 col의 길이가 바뀌었으니 바꾸는 작업
		int tmp = row;
		row = col;
		col = tmp;
		
		// arr를 돌아간 새로운 배열로 교체
		arr = newArr;
	}

	public static void subRotate(int type) {
		if (type == 5) { // 4개로 나눠서 오른쪽 회전
			int[][] rotNum = { { 0, 0 }, { row / 2, 0 }, { row / 2, col / 2 }, { 0, col / 2 } };
			for (int i = 0; i < row / 2; i++) {
				for (int j = 0; j < col / 2; j++) {
					int tmp = arr[i][j];

					for (int k = 0; k < 3; k++) {
						arr[i + rotNum[k][0]][j + rotNum[k][1]] = arr[i + rotNum[k + 1][0]][j + rotNum[k + 1][1]];
					}
					arr[i + rotNum[3][0]][j + rotNum[3][1]] = tmp;
				}
			}
		} else if (type == 6) { // 4개로 나눠서 왼쪽 회전
			int[][] rotNum = { { 0, 0 }, { 0, col / 2 }, { row / 2, col / 2 }, { row / 2, 0 } };
			for (int i = 0; i < row / 2; i++) {
				for (int j = 0; j < col / 2; j++) {
					int tmp = arr[i][j];

					for (int k = 0; k < 3; k++) {
						arr[i + rotNum[k][0]][j + rotNum[k][1]] = arr[i + rotNum[k + 1][0]][j + rotNum[k + 1][1]];
					}
					arr[i + rotNum[3][0]][j + rotNum[3][1]] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// Row, Col, 명령의 갯수 입력
		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		cmdNum = Integer.parseInt(st.nextToken());

		arr = new int[row][col];

		// 배열을 입력
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령어 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cmdNum; i++) {
			int cmd = Integer.parseInt(st.nextToken());

			if (cmd == 1 || cmd == 2) {
				flip(cmd);
			} else if (cmd == 3 || cmd == 4) {
				rotate(cmd);
			} else if (cmd == 5 || cmd == 6) {
				subRotate(cmd);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
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
