
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16953 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long sNum = Integer.parseInt(st.nextToken());
		long fNum = Integer.parseInt(st.nextToken());
		
		if (sNum == fNum) {
			System.out.println(1);
			return;
		}
		
		int result = Math.min(sumOp(sNum, fNum, 1), mulOp(sNum, fNum, 1));
		
		if (result == 0x7fffffff)
			System.out.println(-1);
		else
			System.out.println(result);
	}
	
	static int sumOp(long from, long to, int cnt) {
		if (from == to) {
			return cnt;
		}
		if (from > to) {
			return 0x7fffffff;
		}
		
		from = from * 10 + 1;
		 
		return Math.min(sumOp(from, to, cnt + 1), mulOp(from, to, cnt + 1));
	}
	
	static int mulOp(long from, long to, int cnt) {
		if (from == to) {
			return cnt;
		}
		if (from > to) {
			return 0x7fffffff;
		}
		
		from *= 2;
		
		return Math.min(sumOp(from, to, cnt + 1), mulOp(from, to, cnt + 1));
	}
}
