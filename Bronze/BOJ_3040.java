import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3040 {
	static int[] peoples;
	static boolean[] isSelected;

	public static void recursive(int cnt, int idx, int sum) {
		if (cnt == 7) {
			if (sum == 100) {
				for (int i = 0; i < 9; i++) {
					if (isSelected[i])
						System.out.println(peoples[i]);
				}
			}
			return;
		}
		if (idx >= 9)
			return;
		
		isSelected[idx] = true;
		recursive(cnt + 1, idx + 1, sum + peoples[idx]);
		isSelected[idx] = false;
		recursive(cnt, idx + 1, sum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		peoples = new int[9];
		isSelected = new boolean[9];

		for (int i = 0; i < 9; i++) {
			peoples[i] = Integer.parseInt(br.readLine());
		}

		recursive(0, 0, 0);
	}

}
