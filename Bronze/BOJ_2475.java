import java.util.Scanner;

public class BOJ_2475 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		
		for(int i = 0; i < 5; i++) {
			int tmp = sc.nextInt();
			sum += (int)Math.pow(tmp, 2);
		}
		
		System.out.println(sum % 10);
		sc.close();
	}
}
