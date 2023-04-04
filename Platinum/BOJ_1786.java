import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1786 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] text = in.readLine().toCharArray();
        char[] pattern = in.readLine().toCharArray();

        int[] partialTable = new int[pattern.length];
        for (int i = 1, j = 0; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) j = partialTable[j - 1];

            if (pattern[i] == pattern[j]) partialTable[i] = ++j;
            else partialTable[i] = 0;
        }

        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0, j = 0; i < text.length; ++i) {
            while (j > 0 && text[i] != pattern[j])
                j = partialTable[j - 1];
            if (text[i] == pattern[j]) {
                if (j == pattern.length - 1) {
                    cnt++;
                    list.add(i - j + 1);
                    j = partialTable[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(cnt);
        if (cnt > 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
        }
    }
}