import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1043 {
    static boolean[] knowPeople;
    static boolean[] visited;
    static List<List<Integer>> attendParties = new ArrayList<>();
    static List<List<Integer>> parties = new ArrayList<>();
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 사람의 수와 파티의 수 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int peopleNum = Integer.parseInt(st.nextToken());
        int partyNum = Integer.parseInt(st.nextToken());
        knowPeople = new boolean[peopleNum + 1];
        visited = new boolean[partyNum];
        for (int i = 0; i <= peopleNum; i++) {
            attendParties.add(new ArrayList<>());
        }
        for (int i = 0; i < partyNum; i++) {
            parties.add(new ArrayList<>());
        }

        // 진실을 아는 사람 입력
        st = new StringTokenizer(br.readLine());
        int knowPeopleNum = Integer.parseInt(st.nextToken());
        for (int i = 0; i < knowPeopleNum; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            knowPeople[tmp] = true;
            q.add(tmp);
        }

        // 파티 입력
        for (int i = 0; i < partyNum; i++) {
            st = new StringTokenizer(br.readLine());
            int attendNum = Integer.parseInt(st.nextToken());
            for (int j = 0; j < attendNum; j++) {
                int attendPerson = Integer.parseInt(st.nextToken());
                attendParties.get(attendPerson).add(i);
                parties.get(i).add(attendPerson);
            }
        }

        // BFS로 파티 순회
        while (!q.isEmpty()) {
            int curPerson = q.poll();
            for (int i = 0; i < attendParties.get(curPerson).size(); i++) {
                int nextParty = attendParties.get(curPerson).get(i);
                if (visited[nextParty])
                    continue;
                visited[nextParty] = true;
                for (int j = 0; j < parties.get(nextParty).size(); j++) {
                    int nextPerson = parties.get(nextParty).get(j);
                    if (knowPeople[nextPerson])
                        continue;
                    knowPeople[nextPerson] = true;
                    q.add(nextPerson);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < partyNum; i++) {
            if (!visited[i])
                cnt++;
        }
        System.out.println(cnt);
    }
}
