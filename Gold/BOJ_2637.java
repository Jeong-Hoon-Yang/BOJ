import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2637 {
	static int partCnt;
	static int relationCnt;
	static List<List<Pair>> needPartList;
	static boolean[] checked;
	static boolean[] isNotMiddle;
	static int[][] needMap;

	static class Pair {
		int no;
		int cnt;

		public Pair(int no, int cnt) {
			this.no = no;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		partCnt = Integer.parseInt(br.readLine());
		relationCnt = Integer.parseInt(br.readLine());

		needPartList = new ArrayList<>();
		for (int i = 0; i < partCnt + 1; i++) {
			needPartList.add(new ArrayList<>());
		}
		checked = new boolean[partCnt + 1];
		isNotMiddle = new boolean[partCnt + 1];
		needMap = new int[partCnt + 1][partCnt + 1];

		for (int i = 0; i < relationCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int middlePartNo = Integer.parseInt(st.nextToken());
			int needPartNo = Integer.parseInt(st.nextToken());
			int needPartCnt = Integer.parseInt(st.nextToken());

			needPartList.get(middlePartNo).add(new Pair(needPartNo, needPartCnt));
		}

		for (int i = 1; i < partCnt + 1; i++) {
			if (needPartList.get(i).isEmpty()) {
				checked[i] = true;
				isNotMiddle[i] = true;
				needMap[i][i] = 1;
			}
		}

		proc(partCnt);

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < partCnt + 1; i++) {
			if (needMap[partCnt][i] != 0) {
				sb.append(i).append(" ").append(needMap[partCnt][i]).append("\n");
			}
		}

		System.out.print(sb.toString());
	}

	private static void proc(int partNum) {
		if (checked[partNum])
			return;
		for (int i = 0; i < needPartList.get(partNum).size(); i++) {
			if (!checked[needPartList.get(partNum).get(i).no]) {
				proc(needPartList.get(partNum).get(i).no);
			}
			for (int j = 1; j < partCnt + 1; j++) {
				needMap[partNum][j] +=
					needMap[needPartList.get(partNum).get(i).no][j] * needPartList.get(partNum).get(i).cnt;
			}
		}
		checked[partNum] = true;
	}
}
