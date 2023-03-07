import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_1541 {

    public static int solve(String str) {
        // + 기호 이전까지의 수를 저장
        StringBuilder sb1 = new StringBuilder();
        // + 기호를 만난 이후의 수를 저장
        StringBuilder sb2 = new StringBuilder();

        // 아직 Plus 기호를 만난 적 없음
        boolean meetPlus = false;

        // 더한 후의 수들을 저장 (최종적으로 Queue에 저장된 수들은 - 명령만 실행)
        Queue<Integer> q = new ArrayDeque<>();

        // length + 1번 반복함으로써 남아있는 수에 대해서도 처리
        for (int i = 0; i <= str.length(); i++) {
            // str이 모두 끝났거나 - 기호를 만났을 경우
            if (i == str.length() || str.charAt(i) == '-') {
                // Plus 기호를 만난 적이 있는 경우 sb2에도 값이 저장되어 있음
                if (meetPlus) {
                    // sb1과 sb2를 더한 이후 Queue에 add
                    int num = Integer.parseInt(sb1.toString()) + Integer.parseInt(sb2.toString());
                    q.add(num);
                    sb1 = new StringBuilder();
                    sb2 = new StringBuilder();
                    // Plus 기호를 만난 적이 없는 경우 sb1에만 값이 저장되어 있음
                } else {
                    // sb1을 Queue에 add
                    int num = Integer.parseInt(sb1.toString());
                    q.add(num);
                    sb1 = new StringBuilder();
                }
                // + 기호를 만났다는 flag False로 설정
                meetPlus = false;
                // + 기호를 만났을 경우
            } else if (str.charAt(i) == '+') {
                // 기존에 + 기호를 만났더라면 두 수를 더한 후 sb1에 저장
                if (meetPlus) {
                    int num = Integer.parseInt(sb1.toString()) + Integer.parseInt(sb2.toString());
                    sb1 = new StringBuilder();
                    sb1.append(num);
                    sb2 = new StringBuilder();
                }
                // + 기호를 만났다는 flag True로 설정
                meetPlus = true;
                // 현재 index의 string 값이 기호가 아닌 경우
            } else {
                // Plus 기호를 만난 이후라면 sb2에 그렇지 않다면 sb1에 수를 이어줌
                if (meetPlus) {
                    sb2.append(str.charAt(i));
                } else {
                    sb1.append(str.charAt(i));
                }
            }
        }

        // 첫 숫자 넣기
        int res = q.poll();

        // Queue가 빌 때까지 들어있는 값들을 전부 빼줌
        while (!q.isEmpty())
            res -= q.poll();

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(solve(str));
    }

}
