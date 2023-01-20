import java.util.Scanner;

public class BOJ_4153 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			if (a == 0 && b == 0 && c == 0)
				break;
			
			if (a <= b) {
				if (b <= c) {
					if ((Math.pow(a, 2) + Math.pow(b, 2)) == Math.pow(c, 2)) {
						System.out.println("right");
						continue;
					}
				}
				else {
					if ((Math.pow(a, 2) + Math.pow(c, 2)) == Math.pow(b, 2)) {
						System.out.println("right");
						continue;
					}
				}
			}
			else {
				if (a <= c) {
					if ((Math.pow(a, 2) + Math.pow(b, 2)) == Math.pow(c, 2)) {
						System.out.println("right");
						continue;
					}
				}
				else {
					if ((Math.pow(c, 2) + Math.pow(b, 2)) == Math.pow(a, 2)) {
						System.out.println("right");
						continue;
					}
				}
			}
			System.out.println("wrong");
		}
		sc.close();
	}

}
