import java.util.Scanner;
import java.util.Stack;

public class BOJ_4949 {
	
	public static void main(String[] args) {
		// TODO Auto-generated constructor stub		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			String str = sc.nextLine();
			
			if (str.equals("."))
				break;
			
			System.out.println(Solve(str));
		}
		
		
		sc.close();
	}
	
	public static String Solve(String str) {
		Stack<Character> s = new Stack<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if (ch == '(' || ch == '[') {
				s.push(ch);
				continue;
			}
			
			if (ch == ')') {
				if (s.empty())
					return "no";
				
				char top = s.pop();
				if (top == '(')
					continue;
				else
					return "no";
			}
			
			if (ch == ']') {
				if (s.empty())
					return "no";
				
				char top = s.pop();
				if (top == '[')
					continue;
				else
					return "no";
			}
		}
		
		if(s.empty())
			return "yes";
		else
			return "no";
	}

}
