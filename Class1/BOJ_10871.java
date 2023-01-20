import java.util.Scanner;

public class BOJ_10871 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int num = sc.nextInt();
		
		for (int i = 0; i < size; i++) {
			int tmp = sc.nextInt();
			if (tmp < num)
				System.out.print(tmp + " ");
		}
		
		sc.close();
	}
	
}
