import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13172 {

    static final long DIV_NUM = 1_000_000_007;

    public static long fPow(long x, long y) {
        if (y == 1)
            return x;
        long tmp = fPow(x, y / 2);
        if (y % 2 == 0)
            return tmp * tmp % DIV_NUM;
        else
            return tmp * tmp % DIV_NUM * x % DIV_NUM;
    }

    public static long gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int diceNum = Integer.parseInt(br.readLine());

        long sum = 0;

        for (int i = 0; i < diceNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            long gcd = gcd(a > b ? a : b, a < b ? a : b);

            b /= gcd;
            a /= gcd;

            sum = (sum + fPow(b, DIV_NUM - 2) * a % DIV_NUM) % DIV_NUM;
        }
        System.out.println(sum);
    }
}