import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17143 {
	static List<Shark> sharks;

	static class Shark {
		int sharkRow;
		int sharkCol;
		int speed;
		int direct;
		int size;
		int state; // 0 : die, 1 : live, 2 : catched

		Shark(int sharkRow, int sharkCol, int speed, int direct, int size) {
			this.sharkRow = sharkRow;
			this.sharkCol = sharkCol;
			this.speed = speed;
			this.direct = direct;
			this.size = size;
			this.state = 1;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int sNum = Integer.parseInt(st.nextToken());
		int[][] shiftVal = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

		int[][] board = new int[row + 1][col + 1];
		
		for (int tmp = 0; tmp < board.length; tmp++) {
			Arrays.fill(board[tmp], -1);
		}
		
		sharks = new ArrayList<>();

		for (int i = 0; i < sNum; i++) {
			st = new StringTokenizer(br.readLine());
			int sharkRow = Integer.parseInt(st.nextToken());
			int sharkCol = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direct = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());

			board[sharkRow][sharkCol] = i;
			sharks.add(new Shark(sharkRow, sharkCol, speed, direct, size));
		}

		for (int j = 1; j <= col; j++) {
//			System.out.println("#" + j);
			for (int i = 1; i <= row; i++) {
				if (board[i][j] != -1) {
					sharks.get(board[i][j]).state = 2;
//					System.out.println("catch : " + board[i][j]);
					board[i][j] = -1;
					break;
				}
			}

			for (Shark s : sharks) {
				if (s.state != 1)
					continue;

				if (s.direct == 1 || s.direct == 2) { // 상하로 움직이는 경우
					int haveToMove = (s.speed * shiftVal[s.direct][0]);
					while (haveToMove != 0) {
						if (haveToMove < 0) {
							if (s.sharkRow == 1) {
								haveToMove *= -1;
								s.direct = 2;
							}
							haveToMove++;
							s.sharkRow--;
						} else {
							if (s.sharkRow == row) {
								haveToMove *= -1;
								s.direct = 1;
							}
							haveToMove--;
							s.sharkRow++;
						}
					}
				} else { // 좌우로 움직이는 경우
					int haveToMove = (s.speed * shiftVal[s.direct][1]);
					while (haveToMove != 0) {
						if (haveToMove < 0) {
							if (s.sharkCol == 1) {
								haveToMove *= -1;
								s.direct = 3;
							}
							haveToMove++;
							s.sharkCol--;
						} else {
							if (s.sharkCol == col) {
								haveToMove *= -1;
								s.direct = 4;
							}
							haveToMove--;
							s.sharkCol++;
						}
					}
				}
			}

			for (int tmp = 0; tmp < board.length; tmp++) {
				Arrays.fill(board[tmp], -1);
			}

			for (int sharkNum = 0; sharkNum < sharks.size(); sharkNum++) {
				if (sharks.get(sharkNum).state != 1)
					continue;
				
//				System.out.println("sharkNum : " + sharkNum + ", Row : " + sharks.get(sharkNum).sharkRow + ", Col : " + sharks.get(sharkNum).sharkCol);

				if (board[sharks.get(sharkNum).sharkRow][sharks.get(sharkNum).sharkCol] == -1) {
					board[sharks.get(sharkNum).sharkRow][sharks.get(sharkNum).sharkCol] = sharkNum;
				} else {
					Shark s1 = sharks.get(sharkNum);
					Shark s2 = sharks.get(board[sharks.get(sharkNum).sharkRow][sharks.get(sharkNum).sharkCol]);

					if (s1.size > s2.size) {
						s2.state = 0;
						board[sharks.get(sharkNum).sharkRow][sharks.get(sharkNum).sharkCol] = sharkNum;
					} else {
						s1.state = 0;
					}
				}
			}
		}

		int res = 0;

		for (Shark s : sharks) {
			if (s.state == 2)
				res += s.size;
		}

		System.out.println(res);
	}

}
