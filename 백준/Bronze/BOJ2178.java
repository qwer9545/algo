package src;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
N×M크기의 배열로 표현되는 미로가 있다.
1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때,
(1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

문제 분석
1: 이동할수 있는 칸
0: 이동할 수 없는 칸
(1,1) -> (N,M) 최단거리 구하기
BFS: 여러가지 경우의 수를 줄이면서(함수를 쪼개면서) 단계적으로 나눠서 푼다, queue 사용
DFS도 가능,, 근데 최단거리를 어떻게 단정할 건데?

## 설계
- 주어진 미로에서 (1, 1) 위치에서 시작하여 (N, M) 위치까지 가는 최단 경로의 길이를 찾음. 미로의 각 칸은 벽(0)이나 이동 가능한 공간(1)을 나타냄
- BFS를 사용하여 각 칸을 탐색하며, 이동 가능한 경우 큐에 다음 위치와 함께 이동 거리를 증가시켜서 추가하고, 목적지에 도달하면 현재까지의 이동 거리를 출력
- 각 노드를 한 번만 방문하며, 가장 먼저 목적지에 도달한 경로가 최단 경로가 됨
 */
public class BOJ2178 {
    //미로의 크기를 나타내는 2차원 boolean 배열(멤버변수 선언)
    static boolean[][] maze;
    static int n;
    static int m;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 행
        m = sc.nextInt(); // 열
        maze = new boolean[n][m];
        
        for(int i = 0; i<n; i++){
            String line = sc.next();
            for(int j = 0; j<m; j++){
                maze[i][j] = line.charAt(j) == '1';
            }
        }
        sc.close();
        
        // 메소드 bfs로 최단경로 찾기
        bfs();
    }
        public static void bfs(){
            Queue<int[] > q = new ArrayDeque<>();
            q.add(new int[]{0, 0, 1}); // 시작 위치와 이동 거리

            while(!q.isEmpty()){
                int[] cur = q.poll();
                int r = cur[0]; // 현재 위치의 행
                int c = cur[1]; // 현재 위치의 열
                int num = cur[2]; // 현재 이동거리

                // 목적지에 도달한 경우
                if(r == n-1 && c == m-1){
                    System.out.println(num);
                    break;
                }

                // 범위를 벗어나거나 벽인 경우
                if(r < 0 || r >= n || c < 0 || c >= m || !maze[r][c]){
                    continue;
                }

                // 이동 가능한 경우 미로 업데이트 및 큐에 추가
                maze[r][c] = false; // 방문 처리
                q.add(new int[]{r+1, c, num+1});
                q.add(new int[]{r-1, c, num+1});
                q.add(new int[]{r, c+1, num+1});
                q.add(new int[]{r, c-1, num+1});
            }
    }
}
