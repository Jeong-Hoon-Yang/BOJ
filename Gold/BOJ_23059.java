import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_23059 {

    static class Info {
        int needNum;
        List<String> nextItems;

        public Info() {
            needNum = 0;
            nextItems = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int inputNum = Integer.parseInt(br.readLine());

        Map<String, Info> map = new HashMap<>();

        for (int i = 0; i < inputNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String baseItem = st.nextToken();
            String combItem = st.nextToken();

            if (!map.containsKey(baseItem)) {
                map.put(baseItem, new Info());
            }
            if (!map.containsKey(combItem)) {
                map.put(combItem, new Info());
            }
            map.get(baseItem).nextItems.add(combItem);
            map.get(combItem).needNum++;
        }

        Queue<String> pq = new PriorityQueue<>();
        for (String str : map.keySet()) {
            if (map.get(str).needNum == 0) {
                pq.add(str);
                map.get(str).needNum = -1;
            }
        }

        int cnt = 0;

        while (!pq.isEmpty()) {
            Queue<String> tmpPq = new PriorityQueue<>();
            int size = pq.size();
            while (size --> 0) {
                String tmp = pq.poll();
                sb.append(tmp).append('\n');
                cnt++;
                for (int i = 0; i < map.get(tmp).nextItems.size(); i++) {
                    map.get(map.get(tmp).nextItems.get(i)).needNum -= 1;
                    if (map.get(map.get(tmp).nextItems.get(i)).needNum == 0) {
                        tmpPq.add(map.get(tmp).nextItems.get(i));
                        map.get(map.get(tmp).nextItems.get(i)).needNum = -1;
                    }
                }
            }
            pq.addAll(tmpPq);
        }

        System.out.println(cnt != map.size() ? -1 : sb.toString());
    }
}
