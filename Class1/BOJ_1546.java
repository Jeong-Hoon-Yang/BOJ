import java.util.Scanner;

public class BOJ_1546 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		double[] arr = new double[N];
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			max = Integer.max(max, (int)arr[i]);
		}
		
		double sum = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = arr[i] / max * 100;
			sum += arr[i];
		}
		
		sc.close();
		
		System.out.println(sum / N);
	}
}
