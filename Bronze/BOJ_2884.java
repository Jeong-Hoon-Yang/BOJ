import java.util.Scanner;

public class BOJ_2884 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int hour = sc.nextInt();
		int minute = sc.nextInt();
		
		minute -= 45;
		
		if (minute < 0) {
			hour--;
			minute += 60;
		}
		
		if (hour < 0) {
			hour += 24;
		}
		sc.close();
		
		System.out.println(hour + " " + minute);
	}

}
