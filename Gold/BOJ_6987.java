import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Nation[] nations = new Nation[6];
    static int[][][] result = { { { 1, 0, 0 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } },
            { { 0, 1, 0 }, { 0, 1, 0 } } };
    static int res;

    static class Nation {
        int win;
        int draw;
        int lose;

        public Nation(int win, int draw, int lose) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }

        // 한 국가당 총 경기수
        public int matchSum() {
            return win + draw + lose;
        }
    }

    public static void init() {
        res = 0;

        for (int i = 0; i < 6; i++) {
            int win = Integer.parseInt(st.nextToken());
            int draw = Integer.parseInt(st.nextToken());
            int lose = Integer.parseInt(st.nextToken());

            nations[i] = new Nation(win, draw, lose);
        }
    }

    public static boolean check(Nation[] scoreBoard) {
        for (int i = 0; i < 6; i++) {
            if (scoreBoard[i].win > nations[i].win || scoreBoard[i].draw > nations[i].draw
                    || scoreBoard[i].lose > nations[i].lose)
                return false;
        }
        return true;
    }

    public static boolean checkSum() {
        for (int i = 0; i < 6; i++) {
            if (nations[i].matchSum() != 5)
                return false;
        }
        return true;
    }

    public static void isPossible(Nation[] scoreBoard, int team1, int team2) {
        if (!check(scoreBoard))
            return;

        if (team1 == 5) {
            if (check(scoreBoard)) {
                res = 1;
                return;
            }
        }

        int nextTeam1;
        int nextTeam2;

        if (team2 + 1 == 6) {
            nextTeam1 = team1 + 1;
            nextTeam2 = nextTeam1 + 1;
        } else {
            nextTeam1 = team1;
            nextTeam2 = team2 + 1;
        }

        for (int i = 0; i < 3; i++) {
            scoreBoard[team1].win += result[i][0][0];
            scoreBoard[team2].win += result[i][1][0];
            scoreBoard[team1].draw += result[i][0][1];
            scoreBoard[team2].draw += result[i][1][1];
            scoreBoard[team1].lose += result[i][0][2];
            scoreBoard[team2].lose += result[i][1][2];
            isPossible(scoreBoard, nextTeam1, nextTeam2);
            scoreBoard[team1].win -= result[i][0][0];
            scoreBoard[team2].win -= result[i][1][0];
            scoreBoard[team1].draw -= result[i][0][1];
            scoreBoard[team2].draw -= result[i][1][1];
            scoreBoard[team1].lose -= result[i][0][2];
            scoreBoard[team2].lose -= result[i][1][2];
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 0; tc < 4; tc++) {
            st = new StringTokenizer(br.readLine());

            init();

            Nation[] scoreBoard = new Nation[6];
            for (int i = 0; i < 6; i++) {
                scoreBoard[i] = new Nation(0, 0, 0);
            }

            if (!checkSum()) {
                sb.append(res).append(" ");
                continue;
            }

            isPossible(scoreBoard, 0, 1);

            sb.append(res).append(" ");
        }
        System.out.println(sb.toString());
    }

}
