import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {

    static char[] words;
    static int[] pick;
    static int pwdLen;
    static int wordSize;
    static StringBuilder sb = new StringBuilder();

    public static void combination(int start, int count) {
        if (count == pwdLen) {
            int conNum = 0; // 자음
            int getNum = 0; // 모음
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < pwdLen; i++) {
                tmp.append(words[pick[i]]);
                if (words[pick[i]] == 'a' || words[pick[i]] == 'e' || words[pick[i]] == 'i' || words[pick[i]] == 'o'
                        || words[pick[i]] == 'u')
                    getNum++;
                else
                    conNum++;
            }
            if (conNum >= 2 && getNum >= 1) {
                sb.append(tmp.toString());
                sb.append("\n");
            }
            return;
        }
        for (int i = start; i < wordSize; i++) {
            pick[count] = i;
            combination(i + 1, count + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        pwdLen = Integer.parseInt(st.nextToken());
        wordSize = Integer.parseInt(st.nextToken());

        words = new char[wordSize];
        pick = new int[pwdLen];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < wordSize; i++) {
            words[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(words);

        combination(0, 0);

        System.out.println(sb.toString());
    }

}