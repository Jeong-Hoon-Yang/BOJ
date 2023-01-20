import java.util.Scanner;

public class BOJ_2577 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int res = 1;
		
		for (int i = 0; i < 3; i++) {
			res *= sc.nextInt();
		}
		
		String str = Integer.toString(res);
		
		int[] cnt = new int[10];
		
		for (int i = 0; i < str.length(); i++) {
			cnt[(int)(str.charAt(i) - '0')]++;
		}
		
		for (int i : cnt) {
			System.out.println(i);
		}
	}

}
