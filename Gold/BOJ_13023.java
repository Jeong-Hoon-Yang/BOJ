import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023 {

    static List<List<Integer>> graph = new ArrayList<List<Integer>>();
    static boolean[] visited;
    static boolean possible = false;

    public static void dfs(int start, int cnt) {
        if (cnt == 5) {
            possible = true;
            return;
        }

        for (int i = 0; i < graph.get(start).size(); i++) {
            if (!visited[graph.get(start).get(i)]) {
                visited[graph.get(start).get(i)] = true;
                dfs(graph.get(start).get(i), cnt + 1);
                visited[graph.get(start).get(i)] = false;
            }
            if (possible)
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int peopleNum = Integer.parseInt(st.nextToken());
        int vertexNum = Integer.parseInt(st.nextToken());

        for (int i = 0; i < peopleNum; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < vertexNum; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            graph.get(p1).add(p2);
            graph.get(p2).add(p1);
        }

        for (int i = 0; i < peopleNum; i++) {
            visited = new boolean[peopleNum];
            if (possible)
                break;
            visited[i] = true;
            dfs(i, 1);
        }

        if (possible)
            System.out.println(1);
        else
            System.out.println(0);
    }

}
