import java.util.Scanner;

public class BOJ_2596 {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		// A~H를 표현한 String 배열
		String[] arr = {"000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010"};
		
		int num = Integer.parseInt(sc.nextLine());
		String encMsg = sc.nextLine();
		
		if (encMsg.length() != num * 6) {	// 입력받은 문자의 길이가 (문자의 개수 * 6)이 아니라면 함수 종료
			sc.close();
			return;	
		}
		
		
		char[] decMsg = new char[num];	// 해독한 문자를 담기 위한 배열
		
		for(int i = 0; i < num; i++) {	// 문자의 개수만큼 for문 반복
			String tmp = encMsg.substring(i * 6, i * 6 + 6);	// 입력받은 수를 6자리씩 split
			boolean find = false;	// 문자를 해독하지 못했을 경우 함수를 종료하기 위한 boolean
			for (int j = 0; j < arr.length; j++) {	// A ~ H 중 해독 가능한 문자가 있는지 확인
				if (tmp.equals(arr[j])) {	// 완전히 일치하는 경우 암호 해독 후 다음 문자 확인 단계로 넘어가기
					decMsg[i] = (char)(j + 'A');
					find = true;
					break;
				}
				
				int cnt = 0;
				for (int k = 0; k < 6; k++) {	// 일치하는 경우가 없을 경우 한글자만 다른 경우가 있는지 확인
					if (tmp.charAt(k) != arr[j].charAt(k))
						cnt++;
				}
				// 문자들 중 한글자만 차이나는 규칙이 없으므로 해당 규칙으로 발견하더라도 암호 해독 후 다음 문자 확인 단계로 넘어가기
				if (cnt == 1) { 
					decMsg[i] = (char)(j + 'A');
					find = true;
					break;
				}
			}
			
			if (!find) {	// 모든 규칙을 확인하였음에도 해독하지 못한 경우 해당 위치를 출력한 후 함수 종료
				System.out.println(i + 1);
				sc.close();
				return;
			}
		}
		
		for (char c : decMsg) {	// 해독한 문자열 출력
			System.out.print(c);
		}
		
		sc.close();
	}
	
}
