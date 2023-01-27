import java.util.Scanner;
import java.util.Stack;

public class BOJ_10773 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int arrLen = sc.nextInt();
		
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < arrLen; i++) {
			int tmp = sc.nextInt();
			
			if (tmp != 0) {
				s.add(tmp);
				continue;
			}
			else {
				s.pop();
			}
		}
		
		int result = 0;
		
		while (!s.empty()) {
			result += s.pop();
		}
		
		System.out.println(result);
		sc.close();
	}

}
