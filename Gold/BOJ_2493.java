import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
	
	static class Tower implements Comparable<Tower>{
		int idx;
		int height;
		
		public Tower(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}

		@Override
		public int compareTo(Tower o) {
			// TODO Auto-generated method stub
			return this.height - o.height;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int num = Integer.parseInt(br.readLine());

		Stack<Tower> s = new Stack<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= num; i++) {
			s.add(new Tower(i, Integer.parseInt(st.nextToken())));
		}
		
		int[] arr = new int[num + 1];
		
		Stack<Tower> send = new Stack<>();
		
		while(!s.isEmpty()) {
			if (send.isEmpty()) {
				send.add(s.pop());
				continue;
			}
			
			while(!send.isEmpty() && send.peek().height < s.peek().height) {
				Tower t = send.pop();
				arr[t.idx] = s.peek().idx;
			}
			
			send.add(s.pop());
		}
		
		for (int i = 1; i <= num; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
