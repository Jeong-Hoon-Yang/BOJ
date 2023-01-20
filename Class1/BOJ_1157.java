import java.util.Scanner;

public class BOJ_1157 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		int[] arr = new int[26];
		
		for (int i = 0; i < str.length(); i++) {
			int tmp = str.charAt(i);
			if (tmp >= 'a' && tmp <= 'z') {
				arr[tmp - 'a']++;
			}
			else if (tmp >= 'A' && tmp <= 'Z') {
				arr[tmp - 'A']++;
			}
		}
		
		int max_index = 0;
		boolean invalid = false;
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[max_index])
				invalid = true;
			if (arr[i] > arr[max_index]) {
				max_index = i;
				invalid = false;
			}
		}
		
		if (invalid) {
			System.out.println("?");
			sc.close();
			return;
		}
		System.out.println((char)(max_index + 'A'));
		
		sc.close();
	}
}
