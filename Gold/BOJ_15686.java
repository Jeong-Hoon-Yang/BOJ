import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686 {

	static int[][] city;
	static int len;
	static int surviveNum;
	static ArrayList<Pair> chickens = new ArrayList<>();
	static ArrayList<Pair> houses = new ArrayList<>();
	static int bitMask;
	static int min = 0x7fffffff;

	static class Pair {
		int row;
		int col;

		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void recursive(int cnt, int index) {
		if (cnt == surviveNum) {
			int sum = 0;
			for (int i = 0; i < houses.size(); i++) {
				int tmp = 0x7fffffff;
				for (int j = 0; j < chickens.size(); j++) {
					if ((bitMask & (1 << j)) == (1 << j)) {
						int sumTmp = Math.abs(houses.get(i).row - chickens.get(j).row) + Math.abs(houses.get(i).col - chickens.get(j).col);
						if (sumTmp < tmp)
							tmp = sumTmp;
					}
				}
				sum += tmp;
			}
			if (sum < min)
				min = sum;
			return;
		}
		if (index >= chickens.size()) return;

		bitMask ^= (1 << index);
		recursive(cnt + 1, index + 1);
		bitMask ^= (1 << index);
		recursive(cnt, index + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		len = Integer.parseInt(st.nextToken());
		surviveNum = Integer.parseInt(st.nextToken());

		city = new int[len + 1][len + 1];

		for (int i = 1; i <= len; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= len; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] == 1) {
					houses.add(new Pair(i, j));
				} else if (city[i][j] == 2) {
					chickens.add(new Pair(i, j));
				}
			}
		}
		
		recursive(0, 0);
		
		System.out.println(min);
	}

}
