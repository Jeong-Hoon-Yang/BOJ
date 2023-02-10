import java.util.Scanner;

public class BOJ_2292 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int roomNum = sc.nextInt();
		
		int start = 1;
		int cnt = 1;
		while (roomNum > start) {
			start += 6 * cnt;
			cnt += 1;
		}
		System.out.println(cnt);
		
		sc.close();
	}

}
