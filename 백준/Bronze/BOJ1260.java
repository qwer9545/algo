package src;

import java.util.*;
import java.io.*;
class BOJ1260{
    public static void main(String[] args) throws Exception {
/*
https://www.acmicpc.net/problem/1260
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고,
더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

그래프 풀이 방식
1. 연결을 명시
2. 연결 체크 -> 점 (n * m): 간선 체크

문제 설계
1. DFS -> 그냥 스택으로 푸시면 된다.
 1-1. 재귀: 무조건 앞만보고 나아간다(Depth에 따라 Memory 문제 발생 가능)
 1-2. 스택:
     1-2-1. Stack
     1-2-2. ArrayDeque(반복문 사용 시 시간 초과 이슈 가능)

2. BFS -> queue(반복문 사용 시 메모리나 시간 초과 날 수 있음)
-> ㅋㅋ bbq에 닌텐도 ds
 */
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // String으로 데이터를 받아옴
        // br.readLine() 줄 단위로 데이터를 받아줌
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 공백으로 분리를 시켜줌 -> token -> next...

        int N = Integer.parseInt(st.nextToken()); //정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); //시작점(Vertex)
        System.out.println(N + "  " + M + "  " + V);

        // 그래프 초기화
        boolean[][] graph = new boolean[N + 1][N + 1];
        graph = new boolean[N+1][N+1];  // 정점의 개수 +1
        //0번째 인덱스를 쓰지않는 대신, 인덱스와 정점 간 계산의 편의성
        // 정점이 1부터 시작했을 경우: N+1, 아니면 N:N 추천

        //간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1][v2] = graph[v2][v1] = true; //v1과 v2 사이에 간선이 존재한다(성립함)
//            graph[v1][v2] = true;
//            graph[v2][v1] = true; //간선이 성립함(양방향)
        }
        //그래프 구조 출력
        System.out.println(Arrays.deepToString(graph));

        // DFS 실행
        // 방문한 노드에 대해서 기록할 배열이 필요함(visited)
        visited = new boolean[N + 1]; // 간선 번호가 1부터 시작하니까

//        dfs(graph, node, visited);
//        dfs(graph, V, visited);
//        V가 아닌 1로 시작하는 이유?
//        visited로 복사하면 얕은 복사로 들어간다?
        dfs(graph, 1, new boolean[N + 1]);
        System.out.println(sb.toString());
        //재귀는 순서를 뒤집지 않아도 된다.
        // 왜나면 뒤에서부터 출력되니까 1 2 4 3

        sb = new StringBuilder();
        dfs2(graph, 1, new boolean[N + 1]);
        System.out.println(sb.toString());
        // 순서를 뒤집어야 한다? 1 4 3 2 -> 1 2 4 3

        sb = new StringBuilder();
        bfs(graph, 1, new boolean[N + 1]);
        System.out.println(sb.toString());
        //순서를 뒤집지 않아도 된다. 1 2 3 4
    }
        static StringBuilder sb = new StringBuilder();
        static boolean[] visited;
//        static boolean[][] graph;

        static void bfs(boolean[][] graph, int node, boolean[] visited){
            Queue<Integer> queue = new ArrayDeque();
            queue.add(node);
            while(!queue.isEmpty()) {
                int nextNode = queue.poll(); //pop이랑 같음
                // 출구에서 데이터를 빼냄
                if (visited[nextNode]) continue; //이렇게도 쓰네
                visited[nextNode] = true;
                sb.append(nextNode + " ");

                for (int i = 1; i < graph.length; i++) { //현재 찾을 수 있는 모든 정점
                    if (!graph[nextNode][i]) {
                        continue;
                    }
                    queue.add(i);
                }
            }
        }



        static void dfs2(boolean[][] graph, int node, boolean[] visited) {
            Stack<Integer> stack = new Stack<>();
//          Stack<Integer> stack = new ArrayDeque<>();
            stack.push(node);// 기존에 있는 데이터에 가장 끝에 배치

            while (!stack.isEmpty()) {
                int nextNode = stack.pop(); //가장 끝에 있는 데이터를 꺼내옴
                if (visited[nextNode]) continue;
                visited[nextNode] = true;//이미 방문한 노드니까

                sb.append(nextNode + " "); //방문하지 않은 노드니까 기록
                for (int i = 1; i < graph.length; i++) {
                    if (!graph[nextNode][i]) {
                        continue; //간선이 존재하지 않는다면
                    }
                    stack.push(i);
                }
            }
        }
            // copilot은 신인가?
//            while(stack.isEmpty()) {
//                // 스택이 비어있지 않다면
//                int current = stack.pop(); // 가장 끝에 있는 데이터를 꺼내옴
//                if(!visited[current]) {
//                    // 방문한 적이 없다면
//                    visited[current] = true; // 방문한 것으로 기록
//                    sb.append(current + " "); // 방문한 노드를 기록
//                    for(int i = 1; i < graph.length; i++) {
//                        // 모든 정점을 돌면서 간선 연결 여부를 체크
//                        if (graph[current][i] && !visited[i]) {
//                            // 간선이 존재하고, 방문하지 않은 노드라면
//                            stack.push(i);
//                        }
//                    }
//                }
//            }

        static void dfs(boolean[][] graph, int node, boolean[] visited){
            // 재귀를 통해서 dfs 구현
            /* 1. 노드 방문 -> visited
               2. 지금까지 방문한 이력 추가 -> sb (node)
               3. 가능한 정점 -> dfs
                    3-1. 간선이 존재하는가?
                    3-2. 방문한 적이 없는가?

             */
            visited[node] = true; // 방문한 노드를 기록, N+1
            System.out.println("node = " + node);
            sb.append(node + " "); // 방문한 노드는 sb에 저장되어 있음

            for(int i = 1; i < graph.length; i++) {
                // 모든 정점을 돌면서 간선 연결 여부를 체크
                if (graph[node][i] && !visited[i]) {
                    // 간선이 존재하고, 방문하지 않은 노드라면
                    dfs(graph, i, visited);

                }
            }
        }

        // 간선의 개수만큼 반복
//        for(int i = 0; i < v1; i++){
//            for(int j = 0; j < v2; j++){


//            }
//        }

}



