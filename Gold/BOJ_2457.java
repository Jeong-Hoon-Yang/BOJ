import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2457 {
	static List<Flower> fList = new ArrayList<>();
	
	static class Flower implements Comparable<Flower> {
		int start;
		int end;
		
		public Flower(int sMonth, int sDate, int eMonth, int eDate) {
			super();
			this.start = sMonth * 100 + sDate;
			this.end = eMonth * 100 + eDate;
		}

		@Override
		public int compareTo(Flower o) {
			// TODO Auto-generated method stub
			if (start == o.start)
				return end - o.end;
			return start - o.start;
		}
			
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sMonth = Integer.parseInt(st.nextToken());
			int sDate = Integer.parseInt(st.nextToken());
			int eMonth = Integer.parseInt(st.nextToken());
			int eDate = Integer.parseInt(st.nextToken());
			
			fList.add(new Flower(sMonth, sDate, eMonth, eDate));
		}
		
		Collections.sort(fList);
		
		for (int i = 0; i < fList.size(); i++) {
			if (fList.get(i).start > 1130) {
				fList.remove(i);
			}
		}
		
		for (int i = 0; i < fList.size(); i++) {
			if (fList.get(i).end < 301) {
				fList.remove(i);
			}
		}
		
		for (int i = 1; i < fList.size(); i++) {
			if (fList.get(i - 1).start == fList.get(i).start) {
				fList.remove(i - 1);
			}
		}
		
		for (int i = 1; i < fList.size(); i++) {
			if (fList.get(i - 1).start <= fList.get(i).start && fList.get(i - 1).end >= fList.get(i).end) {
				fList.remove(i);
			}
		}
		
		
		if (fList.get(0).start > 301) {
			System.out.println(0);
			return;
		}
		
		int start = 301;
		int index = 0;
		int end = 0;
		int count = 0;
		
		while (start < 1201) {
			boolean find = false;
			
			for (int i = index; i < fList.size(); i++) {
				if (fList.get(i).start > start) {
					break;
				}
				
				if (end < fList.get(i).end) {
					find = true;
					end = fList.get(i).end;
					index = i + 1;
				}
			}
			
			if (find) {
				start = end;
				count++;
			}
			else {
				break;
			}
		}
		
		if (end < 1201)
			System.out.println(0);
		else
			System.out.println(count);
	}

}
