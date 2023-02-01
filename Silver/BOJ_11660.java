import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		int cnt = Integer.parseInt(st.nextToken());
		
		int[][] table = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++)
				table[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[][] sumTable = new int[size][size];
		sumTable[0][0] = table[0][0];
		
		for (int i = 1; i < size; i++) {
			sumTable[i][0] = sumTable[i - 1][0] + table[i][0];
			sumTable[0][i] = sumTable[0][i - 1] + table[0][i];
		}
		
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				sumTable[i][j] = sumTable[i - 1][j] + sumTable[i][j - 1] - sumTable[i - 1][j - 1] + table[i][j];
			}
		}

		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int[] sPoint = new int[2];
			sPoint[0] = Integer.parseInt(st.nextToken()) - 1;
			sPoint[1] = Integer.parseInt(st.nextToken()) - 1;
			
			int[] ePoint = new int[2];
			ePoint[0] = Integer.parseInt(st.nextToken()) - 1;
			ePoint[1] = Integer.parseInt(st.nextToken()) - 1;
			
			int ePointSum = sumTable[ePoint[0]][ePoint[1]];
			int minusSum1 = 0;
			if ((sPoint[1] - 1) >= 0)
				minusSum1 = sumTable[ePoint[0]][sPoint[1] - 1];
			int minusSum2 = 0;
			if ((sPoint[0] - 1) >= 0)
				minusSum2 = sumTable[sPoint[0] - 1][ePoint[1]];
			int recovSum = 0;
			if ((sPoint[0] - 1) >= 0 && (sPoint[1] - 1) >= 0)
				recovSum = sumTable[sPoint[0] - 1][sPoint[1] - 1];
			
			System.out.println(ePointSum - minusSum1 - minusSum2 + recovSum);
		}
	}

}
