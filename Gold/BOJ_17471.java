import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_17471 {
    static int cityNum;
    static int[] populations;
    static boolean[][] connection;
    static int[] parent;
    static boolean[] selected;
    static int min = 0x7fffffff;

    static class Pair {
        int city1;
        int city2;

        public Pair(int city1, int city2) {
            this.city1 = city1;
            this.city2 = city2;
        }
    }

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static boolean isUnion(int x, int y) {
        if (find(x) == find(y))
            return true;
        return false;
    }

    static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y)
            parent[y] = x;
        else
            parent[x] = y;
    }

    static boolean canMerge(List<Integer> list) {
        Set<Integer> set = new HashSet<>();
        set.add(list.get(0));
        Queue<Integer> q = new ArrayDeque<>();
        q.add(list.get(0));
        boolean[] visited = new boolean[cityNum];
        visited[list.get(0)] = true;

        while (!q.isEmpty()) {
            int tmp = q.poll();
            for (int i = 0; i < cityNum; i++) {
                if (!visited[i] && connection[tmp][i] && list.contains(i)) {
                    visited[i] = true;
                    set.add(i);
                    q.add(i);
                }
            }
        }

        if (list.size() == set.size())
            return true;
        return false;
    }

    static void subSet(int cnt) {
        if (cnt == cityNum) {
            List<Integer> list1 = new LinkedList<>();
            List<Integer> list2 = new LinkedList<>();

            for (int i = 0; i < cityNum; i++) {
                if (selected[i])
                    list1.add(i);
                else
                    list2.add(i);
            }

            if (list1.size() == 0 || list2.size() == 0)
                return;

            if (canMerge(list1) && canMerge(list2)) {
                int list1Sum = 0;
                for (int i = 0; i < list1.size(); i++) {
                    list1Sum += populations[list1.get(i)];
                }
                int list2Sum = 0;
                for (int i = 0; i < list2.size(); i++) {
                    list2Sum += populations[list2.get(i)];
                }
                min = Math.min(min, Math.abs(list1Sum - list2Sum));
            }
            return;
        }
        selected[cnt] = true;
        subSet(cnt + 1);
        selected[cnt] = false;
        subSet(cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cityNum = Integer.parseInt(br.readLine());
        populations = new int[cityNum];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < cityNum; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
        }

        connection = new boolean[cityNum][cityNum];
        parent = new int[cityNum];
        selected = new boolean[cityNum];

        for (int i = 0; i < cityNum; i++)
            parent[i] = i;

        for (int i = 0; i < cityNum; i++) {
            StringTokenizer tmp = new StringTokenizer(br.readLine());
            int connNum = Integer.parseInt(tmp.nextToken());
            for (int j = 0; j < connNum; j++) {
                int neighbor = Integer.parseInt(tmp.nextToken());
                connection[i][neighbor - 1] = true;
                if (!isUnion(i, neighbor - 1))
                    merge(i, neighbor - 1);
            }
        }

        for (int i = 0; i < cityNum; i++) {
            find(i);
        }

        int flag = 0;
        for (int i = 0; i < cityNum; i++) {
            flag = (flag) | 1 << parent[i];
        }

        int areaNum = 0;
        for (int i = 0; i < cityNum; i++) {
            if ((flag & 1 << i) == (1 << i))
                areaNum++;
        }

        if (areaNum > 2)
            System.out.println(-1);
        else if (areaNum == 2) {
            int[] tmp = new int[cityNum];
            for (int i = 0; i < cityNum; i++) {
                tmp[parent[i]] += populations[i];
            }
            int[] popSum = new int[2];
            for (int i = 0; i < cityNum; i++) {
                if (tmp[i] != 0 && popSum[0] == 0) {
                    popSum[0] = tmp[i];
                } else if (tmp[i] != 0) {
                    popSum[1] = tmp[i];
                }
            }
            System.out.println(Math.abs(popSum[1] - popSum[0]));
        } else {
            subSet(0);
            if (min != 0x7fffffff)
                System.out.println(min);
            else
                System.out.println(-1);
        }
    }

}
