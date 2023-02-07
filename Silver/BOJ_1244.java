import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1244 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int num = Integer.parseInt(br.readLine());
		
		int[] switches = new int[num + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i < switches.length; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int studentsNum = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < studentsNum; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			
			switch (type) {
			case 1:
				int indexCopy1 = index;
				while (indexCopy1 < switches.length) {
					switches[indexCopy1] = (switches[indexCopy1] + 1) % 2;
					indexCopy1 += index;
				}
				break;
			case 2:
				int midIndex = index;
				int subIndex = 1;
				switches[midIndex] = (switches[midIndex] + 1) % 2;
				while ((midIndex - subIndex) >= 1 && (midIndex + subIndex) < switches.length) {
					if (switches[midIndex - subIndex] == switches[midIndex + subIndex]) {
						switches[midIndex - subIndex] = (switches[midIndex - subIndex] + 1) % 2;
						switches[midIndex + subIndex] = (switches[midIndex + subIndex] + 1) % 2;
						subIndex++;
					}
					else {
						break;
					}
				}
				break;
			}
		}
		
		for (int i = 1; i < switches.length; i++) {
			sb.append(switches[i]);
			sb.append(" ");
			if (i % 20 == 0) {
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
