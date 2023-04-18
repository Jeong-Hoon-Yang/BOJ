import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        int[] appear = new int[20001];
        int[] attends = new int[num];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            int skill = Integer.parseInt(st.nextToken());
            attends[i] = skill;
            appear[skill + 10000]++;
        }

        long cnt = 0;

        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                if (attends[i] == 0 || attends[j] == 0)
                    continue;
                if (attends[i] > 0 && attends[j] < 0 || attends[i] < 0 && attends[j] > 0)
                    continue;

                int skillSum = attends[i] + attends[j];
                int tmp = skillSum * -1 + 10000;
                if (tmp >= 0 && tmp < 20001) {
                    cnt += appear[tmp];
                }
            }
        }

        long possCnt = 0;
        for (int i = 0; i < 10000; i++) {
            possCnt += appear[i] * appear[20000 - i];
        }
        possCnt *= appear[10000];

        cnt += possCnt;

        if (appear[10000] >= 3) {
            cnt += (appear[10000] * (appear[10000] - 1) * (appear[10000] - 2) / 6);
        }

        System.out.println(cnt);
    }
}
