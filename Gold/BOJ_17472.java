import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17472 {
    private static int rowLen;
    private static int colLen;
    private static int[][] board;
    private static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int[][] bridges;

    private static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rowLen = Integer.parseInt(st.nextToken());
        colLen = Integer.parseInt(st.nextToken());
        board = new int[rowLen][colLen];

        // 섬을 입력 받을 때 섬인 부분은 값을 -1로 하여 추후 BFS를 이용해 섬의 번호를 매겨주기
        for (int i = 0; i < rowLen; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colLen; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1)
                    board[i][j] = -1;
            }
        }

        // BFS를 이용해 섬의 번호를 매겨주는 작업
        int islandIdx = 0;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == -1) {
                    bfs(i, j, ++islandIdx);
                }
            }
        }

        bridges = new int[islandIdx + 1][islandIdx + 1];
        
        // bridge를 구하는 함수
        findBridge();

        prim(1);
    }

    private static void findBridge() {
        // 각 행별로 탐색
        for (int i = 0; i < rowLen; i++) {
            // bridgeFlag : 현재 탐색을 시작한 섬의 번호를 저장
            // bridgeLength : 탐색을 시작한 섬으로부터 다리의 길이를 저장
            int bridgeFlag = 0;
            int bridgeLength = 0;
            for (int j = 1; j < colLen; j++) {
                if (bridgeFlag == 0) {
                    // 섬에서 바다가 시작되는 지점이라면 bridgeFlag 값을 섬의 번호로 설정 후
                    // bridgeLength 할당
                    if (board[i][j - 1] != 0 && board[i][j] == 0) {
                        bridgeFlag = board[i][j - 1];
                        bridgeLength = 1;
                    }
                    // bridgeFlag 가 0이라면 다리에 관련된 탐색을 진행할 필요가 없으므로 continue;
                    continue;
                }
                // 탐색 중인 지점이 바다라면 bridgeLength를 증가
                if (board[i][j] == 0) {
                    bridgeLength++;
                } else {
                    // 섬을 만났을 경우 bridgeLength가 2 이상일 경우에 bridges 행렬에 추가
                    if (bridgeLength >= 2) {
                        // 추가할 경우 기존의 값과 비교하여 최솟 값으로 갱신
                        // 다만 bridges 행렬에 아직 값이 초기화 되지 않은 경우를 방지하기 위해
                        // 0일 때는 Integer.MAX_VALUE값과 비교하도록 함
                        bridges[bridgeFlag][board[i][j]] = Math.min(
                                bridges[bridgeFlag][board[i][j]] == 0 ? 0x7fffffff : bridges[bridgeFlag][board[i][j]],
                                bridgeLength);
                        bridges[board[i][j]][bridgeFlag] = Math.min(
                                bridges[board[i][j]][bridgeFlag] == 0 ? 0x7fffffff : bridges[board[i][j]][bridgeFlag],
                                bridgeLength);
                    }
                    // 다리에 대한 탐색이 끝났으므로 bridgeFlag, bridgeLength값 초기화
                    bridgeFlag = 0;
                    bridgeLength = 0;
                }
            }
        }

        // 각 열에 대해서도 위와 동일하게 수행
        for (int i = 0; i < colLen; i++) {
            int bridgeFlag = 0;
            int bridgeLength = 0;
            for (int j = 1; j < rowLen; j++) {
                if (bridgeFlag == 0) {
                    if (board[j - 1][i] != 0 && board[j][i] == 0) {
                        bridgeFlag = board[j - 1][i];
                        bridgeLength = 1;
                    }
                    continue;
                }
                if (board[j][i] == 0) {
                    bridgeLength++;
                } else {
                    if (bridgeLength >= 2) {
                        bridges[bridgeFlag][board[j][i]] = Math.min(
                                bridges[bridgeFlag][board[j][i]] == 0 ? 0x7fffffff : bridges[bridgeFlag][board[j][i]],
                                bridgeLength);
                        bridges[board[j][i]][bridgeFlag] = Math.min(
                                bridges[board[j][i]][bridgeFlag] == 0 ? 0x7fffffff : bridges[board[j][i]][bridgeFlag],
                                bridgeLength);
                    }
                    bridgeFlag = 0;
                    bridgeLength = 0;
                }
            }
        }
    }

    /**
     * Prim 알고리즘
     *
     * 한 정점부터 시작하여 현재 그래프에서 이동할 수 있는 가장 거리가 짧은 간선들을 골라가며
     * 최소 신장트리를 구하는 알고리즘
     *
     * start부터 시작하여 start에서 이동할 수 있는 (다른 섬의 번호, 다른 섬까지의 거리)를
     * 다른 섬까지의 거리의 오름차순으로 PriorityQueue에 넣고
     * 방문하지 않은 정점들에 대해서 계속해서 탐색해 나가도록 설계
     */
    private static void prim(int start) {
        Queue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.second - o2.second);
        boolean[] visited = new boolean[bridges.length];
        pq.add(new Pair(start, 0));
        int weight = 0;
        int sumCnt = 0;

        while (!pq.isEmpty()) {
            Pair tmp = pq.poll();
            int curPos = tmp.first;
            int curCost = tmp.second;
            if (visited[curPos]) continue;
            weight += curCost;
            sumCnt++;
            visited[curPos] = true;

            for (int i = 1; i < bridges[curPos].length; i++) {
                if (bridges[curPos][i] != 0) {
                    pq.add(new Pair(i, bridges[curPos][i]));
                }
            }
        }

        if (sumCnt == bridges.length - 1)
            System.out.println(weight == 0 ? -1 : weight);
        else
            System.out.println(-1);
    }

    private static void bfs(int row, int col, int cnt) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(row, col));
        board[row][col] = cnt;

        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int curRow = tmp.first;
            int curCol = tmp.second;
            for (int i = 0; i < 4; i++) {
                int nextRow = tmp.first + dir[i][0];
                int nextCol = tmp.second + dir[i][1];
                if (check(nextRow, nextCol)) {
                    board[nextRow][nextCol] = cnt;
                    q.add(new Pair(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean check(int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && board[row][col] == -1;
    }
}
