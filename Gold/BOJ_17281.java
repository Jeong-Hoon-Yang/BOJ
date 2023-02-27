import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17281 {
    static int inning;
    static int[][] hits;

    static int[] order = new int[9 + 1];
    static boolean[] selected = new boolean[9 + 1];
    static boolean[] base = new boolean[4];
    static int maxScore = 0;

    public static void calc() {
        int score = 0;
        int curPlayer = 0;

        for (int i = 1; i <= inning; i++) {
            // 이닝이 초기화 될 때마다 베이스 초기화 해주어야 한다는 것을 생각하지 못함
            Arrays.fill(base, false);
            int out = 0;
            while (out < 3) {
                curPlayer++;
                if (curPlayer == 10)
                    curPlayer = 1;
                if (hits[i][order[curPlayer]] == 0) {
                    out++;
                } else if (hits[i][order[curPlayer]] == 1) {
                    if (base[3] == true) {
                        score++;
                        base[3] = false;
                    }
                    if (base[2] == true) {
                        base[3] = true;
                        base[2] = false;
                    }
                    if (base[1] == true) {
                        base[2] = true;
                        base[1] = false;
                    }
                    base[1] = true;
                } else if (hits[i][order[curPlayer]] == 2) {
                    if (base[3] == true) {
                        score++;
                        base[3] = false;
                    }
                    if (base[2] == true) {
                        score++;
                        base[2] = false;
                    }
                    if (base[1] == true) {
                        base[3] = true;
                        base[1] = false;
                    }
                    base[2] = true;
                } else if (hits[i][order[curPlayer]] == 3) {
                    if (base[3] == true) {
                        score++;
                        base[3] = false;
                    }
                    if (base[2] == true) {
                        score++;
                        base[2] = false;
                    }
                    if (base[1] == true) {
                        score++;
                        base[1] = false;
                    }
                    base[3] = true;
                } else if (hits[i][order[curPlayer]] == 4) {
                    if (base[3] == true) {
                        score++;
                        base[3] = false;
                    }
                    if (base[2] == true) {
                        score++;
                        base[2] = false;
                    }
                    if (base[1] == true) {
                        score++;
                        base[1] = false;
                    }
                    score++;
                }
            }
        }

        maxScore = Math.max(maxScore, score);
    }

    public static void permutation(int cnt) {
        if (cnt == 10) {
            calc();
            return;
        }
        if (cnt == 4) {
            permutation(cnt + 1);
            return;
        }
        for (int i = 2; i <= 9; i++) {
            if (selected[i])
                continue;
            order[cnt] = i;
            selected[i] = true;
            permutation(cnt + 1);
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inning = Integer.parseInt(br.readLine());

        hits = new int[inning + 1][9 + 1];

        for (int i = 1; i <= inning; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                hits[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected[1] = true;
        order[4] = 1;

        permutation(1);

        System.out.println(maxScore);
    }

}
