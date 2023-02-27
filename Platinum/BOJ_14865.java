import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14865 {
    // 선분이 0과 만나는 지점을 저장하기 위한 linkedList
    static List<Ground> list = new ArrayList<>();

    static class Ground implements Comparable<Ground> {
        long x;
        int id;

        public Ground(long x, int id) {
            this.x = x;
            this.id = id;
        }

        @Override
        public int compareTo(Ground o) {
            // TODO Auto-generated method stub
            return (int) (this.x - o.x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int pointNum = Integer.parseInt(br.readLine());

        int identifier = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        long prevXPoint = Long.parseLong(st.nextToken());
        long prevYPoint = Long.parseLong(st.nextToken());
        long startXPoint = prevXPoint;
        long startYPoint = prevYPoint;

        for (int i = 1; i < pointNum; i++) {
            st = new StringTokenizer(br.readLine());
            long curXPoint = Long.parseLong(st.nextToken());
            long curYPoint = Long.parseLong(st.nextToken());
            // 입력받은 점들을 pointList에 저장
            // Y좌표가 작은 곳에서 큰 곳으로 올라갈 때 0과 만남
            if (prevYPoint < 0 && curYPoint > 0) {
                // 이 경우에는 새롭게 봉우리가 생기는 지점이므로 새로운 식별자와 x좌표를 list에 저장
                list.add(new Ground(prevXPoint, ++identifier));
            }
            // Y좌표가 큰 곳에서 작은 곳으로 갈 때도 0과 만남
            else if (prevYPoint > 0 && curYPoint < 0) {
                // 이 경우에는 봉우리가 닫히는 지점이므로 현재 식별자를 그대로 x좌표와 함께 list에 저장
                list.add(new Ground(prevXPoint, identifier));
            }
            prevXPoint = curXPoint;
            prevYPoint = curYPoint;
        }

        if (prevYPoint < 0 && startYPoint > 0) {
            // 이 경우에는 새롭게 봉우리가 생기는 지점이므로 새로운 식별자와 x좌표를 list에 저장
            list.add(new Ground(prevXPoint, ++identifier));
        }
        // Y좌표가 큰 곳에서 작은 곳으로 갈 때도 0과 만남
        else if (prevYPoint > 0 && startYPoint < 0) {
            // 이 경우에는 봉우리가 닫히는 지점이므로 현재 식별자를 그대로 x좌표와 함께 list에 저장
            list.add(new Ground(prevXPoint, identifier));
        }

        if (list.get(0).id == 0)
            list.get(0).id = identifier;

        // list를 x좌표 기준으로 정렬
        Collections.sort(list);

        // 다른 봉우리에 의해 포함되지 않는 봉우리의 개수
        int nothingOut = 0;
        // 다른 봉우리를 포함하지 않는 봉우리의 개수
        int nothingIn = 0;

        // 봉우리가 시작되었는지 확인하기 위한 변수
        boolean open = false;
        // 봉우리가 시작된 지점의 id값
        int curId = 0;

        // 직전 id와 현재 id가 같다면 사이에 아무런 봉우리도 포함하고 있지 않음
        for (int i = 0; i < list.size(); i++) {
            if (!open) {
                open = true;
                curId = list.get(i).id;
                continue;
            }
            // 봉우리가 시작 된 경우 시작할 때의 id값과 현재의 id값이 같다면
            // 봉우리가 종료된 것이므로 시작확인 변수를 false로 바꾸고, curId를 0으로 초기화
            if (open) {
                if (curId == list.get(i).id) {
                    open = false;
                    curId = 0;
                    // 이후 다른 봉우리에 의해 포함되지 않는 봉우리의 개수를 증가
                    nothingOut++;
                }
                if (list.get(i - 1).id == list.get(i).id)
                    // 다른 봉우리를 포함하지 않는 봉우리의 개수를 증가
                    nothingIn++;
            }
        }

        sb.append(nothingOut).append(" ").append(nothingIn);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
