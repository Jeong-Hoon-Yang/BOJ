import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5587 {	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int cnt = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> sangCard = new PriorityQueue<Integer>();
		PriorityQueue<Integer> geunCard = new PriorityQueue<Integer>();
		
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			sangCard.add(tmp);
		}
		
		for (int i = 1; i <= cnt * 2; i++) {
			if (!sangCard.contains(i)) {
				geunCard.add(i);
			}
		}
		
		int top = 0;
		int turn = 1;
		
		while (!sangCard.isEmpty() && !geunCard.isEmpty()) {
			if (turn == 1) 
				top = find(sangCard, top);
			else
				top = find(geunCard, top);
			turn = (turn + 1) % 2;
		}
		
		
		System.out.println(geunCard.size());
		System.out.println(sangCard.size());
	}

	static int find(PriorityQueue<Integer> pq, int top) {
		PriorityQueue<Integer> tmp = new PriorityQueue<>();
		boolean flag = false;
		
		while (!pq.isEmpty()) {
			int iTmp = pq.poll();
			if (top < iTmp) {
				top = iTmp;
				flag = true;
				break;
			}
			else {
				tmp.add(iTmp);
			}
		}
		while (!tmp.isEmpty()) {
			pq.add(tmp.poll());
		}
		if (!flag)
			return 0;
		else
			return top;
	}
}
