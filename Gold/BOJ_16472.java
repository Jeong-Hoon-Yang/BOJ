import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16472 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int alphaNum = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int[] appearance = new int[26];

        int pointer1 = 0;
        int pointer2 = 0;
        int cnt = 0;
        int maxLen = 0;

        ++appearance[str.charAt(pointer1) - 'a'];
        cnt++;

        while (true) {
            pointer2++;

            if (pointer2 >= str.length()) break;

            if (++appearance[str.charAt(pointer2) - 'a'] == 1)
                cnt++;

            while (cnt > alphaNum) {
                if (--appearance[str.charAt(pointer1) - 'a'] == 0)
                    cnt--;

                pointer1++;
            }
            maxLen = Math.max(maxLen, pointer2 - pointer1 + 1);
        }

        System.out.println(maxLen);
    }
}
