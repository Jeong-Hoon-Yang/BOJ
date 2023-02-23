import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {

    static class Pair {
        int pos;
        int cnt;

        Pair(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[100001];

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(n, 0));
        visited[n] = true;

        Pair tmp = new Pair(n, 0);

        while (!q.isEmpty()) {
            tmp = q.poll();
            visited[tmp.pos] = true;
            if (tmp.pos == k)
                break;
            if (tmp.pos - 1 >= 0 && !visited[tmp.pos - 1])
                q.add(new Pair(tmp.pos - 1, tmp.cnt + 1));
            if (tmp.pos + 1 <= 100000 && !visited[tmp.pos + 1])
                q.add(new Pair(tmp.pos + 1, tmp.cnt + 1));
            if (tmp.pos * 2 <= 100000 && !visited[tmp.pos * 2])
                q.add(new Pair(tmp.pos * 2, tmp.cnt + 1));
        }

        System.out.println(tmp.cnt);
    }

}
