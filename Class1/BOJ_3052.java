import java.util.Scanner;

public class BOJ_3052 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int[] modulars = new int[42];
		
		for (int i = 0; i < 42; i++) {
			modulars[i] = 0;
		}
		
		for (int i = 0; i < 10; i++) {
			int tmp = sc.nextInt();
			tmp %= 42;
			modulars[tmp]++;
		}
		
		int cnt = 0;
		
		for (int i = 0; i < 42; i++) {
			if (modulars[i] != 0) cnt++;
		}
		
		System.out.println(cnt);
		
		sc.close();
	}

}
