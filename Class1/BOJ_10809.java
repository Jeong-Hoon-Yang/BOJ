import java.util.Scanner;

public class BOJ_10809 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] alp = new int[26];
		
		for (int i = 0; i < 26; i++) {
			alp[i] = -1;
		}
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		for (int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			if (alp[tmp - 'a'] == -1) {
				alp[tmp - 'a'] = i;
			}
		}
		
		for (int i = 0; i < 26; i++) {
			System.out.print(alp[i] + " ");
		}
		
		sc.close();
	}

}
