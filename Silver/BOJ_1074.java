import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
    static int n;
    static int row;
    static int col;

    public static int divide(int num, int startRow, int startCol) {
        if (num == 0) {
            return 0;
        }

        if (row < (startRow + (int) Math.pow(2, num - 1)) && col < (startCol + (int) Math.pow(2, num - 1))) {
            return divide(num - 1, startRow, startCol);
        } else if (row < (startRow + (int) Math.pow(2, num - 1)) && col >= (startCol + (int) Math.pow(2, num - 1))) {
            return divide(num - 1, startRow, startCol + (int) Math.pow(2, num - 1))
                    + (int) (Math.pow(2, num - 1) * Math.pow(2, num - 1));
        } else if (row >= (startRow + (int) Math.pow(2, num - 1)) && col < (startCol + (int) Math.pow(2, num - 1))) {
            return divide(num - 1, startRow + (int) Math.pow(2, num - 1), startCol)
                    + (int) (Math.pow(2, num - 1) * Math.pow(2, num - 1)) * 2;
        } else {
            return divide(num - 1, startRow + (int) Math.pow(2, num - 1), startCol + (int) Math.pow(2, num - 1))
                    + (int) (Math.pow(2, num - 1) * Math.pow(2, num - 1)) * 3;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        int result = divide(n, 0, 0);

        System.out.println(result);
    }

}
