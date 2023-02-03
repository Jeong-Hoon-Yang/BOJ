import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2290 {
	static char[][][] numArr;
	static char[][] tmp;
	
	static int[][] map =
		{{1, 1, 1, 0, 1, 1, 1},
		 {0, 0, 1, 0, 0, 1, 0},
		 {1, 0, 1, 1, 1, 0, 1},
		 {1, 0, 1, 1, 0, 1, 1},
		 {0, 1, 1, 1, 0, 1, 0},
		 {1, 1, 0, 1, 0, 1, 1},
		 {1, 1, 0, 1, 1, 1, 1},
		 {1, 0, 1, 0, 0, 1, 0},
		 {1, 1, 1, 1, 1, 1, 1},
		 {1, 1, 1, 1, 0, 1, 1},
		};
	
	static void fill1(int sY, int sX, int fY, int fX) {
		if (sX == fX) {
			for (int i = sY; i <= fY; i++) {
				tmp[i][sX] = '|';
			}
		}
		if (sY == fY) {
			for (int i = sX; i <= fX; i++) {
				tmp[sY][i] = '-';
			}
		}
	}
	
	static void fill2(int sY, int sX, int fY, int fX) {
		if (sY == fY) {
			for (int i = sX; i <= fX; i++) {
				tmp[sY][i] = '-';
			}
		}
		if (sX == fX) {
			for (int i = sY; i <= fY; i++) {
				tmp[i][sX] = '|';
			}
		}
	}
	
	static void initNumArr(int len) {
		numArr = new char[10][len * 2 + 3][len + 2];
		for (int num = 0; num < 10; num++) {
			tmp = new char[len * 2 + 3][len + 2];
			for (int i = 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp[i].length; j++) {
					tmp[i][j] = ' ';
				}
			}
			
			if (map[num][0] == 1) fill1(0, 1, 0, len);
			if (map[num][3] == 1) fill1(len + 1, 1, len + 1, len);
			if (map[num][6] == 1) fill1((len + 1) * 2, 1, (len + 1) * 2, len);
			
			if (map[num][1] == 1) fill2(1, 0, len, 0);
			if (map[num][4] == 1) fill2(len + 2, 0, (len + 1) * 2 - 1, 0);
			
			if (map[num][2] == 1) fill2(1, len + 1, len, len + 1);
			if (map[num][5] == 1) fill2(len + 2, len + 1, (len + 1) * 2 - 1, len + 1);
			
			numArr[num] = tmp;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int len = Integer.parseInt(st.nextToken());
		String num = st.nextToken();
		
		char [][] display = new char[len * 2 + 3][(len + 3) * num.length()];
		
		for (int i = 0; i < display.length; i++) {
			for (int j = 0; j < display[i].length; j++) {
				display[i][j] = ' ';
			}
		}
		
		initNumArr(len);
		
		for (int i = 0; i < num.length(); i++) {
			int tmp = Integer.parseInt(num.substring(i, i + 1));
			for (int j = 0; j < len * 2 + 3; j++) {
				for (int k = 0; k < len + 2; k++) {
					display[j][k + (len + 3) * i] = numArr[tmp][j][k];
				}
			}
		}
		
		for (int i = 0; i < display.length; i++) {
			for (int j = 0; j < display[i].length; j++) {
				System.out.print(display[i][j]);
			}
			System.out.println();
		}
	}

}
