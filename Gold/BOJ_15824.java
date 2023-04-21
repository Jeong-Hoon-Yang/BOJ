import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15824 {

    static final int MOD_NUM = 1_000_000_007;

    static long[] powArr = new long[300_001];

    public static long pow(int n) {
        if (powArr[n] != 0)
            return powArr[n];
        if (n % 2 == 1) {
            long tmp = pow((n - 1) / 2);
            return powArr[n] = 2 * tmp * tmp % MOD_NUM;
        }
        long tmp = pow(n / 2);
        return powArr[n] = pow(n / 2) * pow(n / 2) % MOD_NUM;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] numArr = new int[num];
//        List<Integer> numArr = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
//            numArr.add(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(numArr);
//        Collections.sort(numArr);

        powArr[0] = 1;
        powArr[1] = 2;

        long sum = 0;

        for (int i = 0; i < num; i++) {
            sum += numArr[i] * (pow(i) - pow(num - i - 1) + MOD_NUM) % MOD_NUM;
//            sum += numArr.get(i) * (pow(i) - pow(num - i - 1) + MOD_NUM) % MOD_NUM;
            sum %= MOD_NUM;
        }

        System.out.println(sum);
    }

}
