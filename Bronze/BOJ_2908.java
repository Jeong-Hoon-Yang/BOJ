import java.util.Scanner;

public class BOJ_2908 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		String a_str = Integer.toString(a);
		StringBuffer sb = new StringBuffer(a_str);
		String a_revstr = sb.reverse().toString();
		
		a = Integer.parseInt(a_revstr);
		
		String b_str = Integer.toString(b);
		StringBuffer sb2 = new StringBuffer(b_str);
		String b_revstr = sb2.reverse().toString();
		
		b = Integer.parseInt(b_revstr);
		
		System.out.println(Integer.max(a, b));
		
	}

}
