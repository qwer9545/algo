package src;

import java.util.*;

/*
# BOJ_2210 : 숫자판 점프
> https://www.acmicpc.net/problem/2210
##문제
5×5 크기의 숫자판이 있다. 각각의 칸에는 숫자(digit, 0부터 9까지)가 적혀 있다.
이 숫자판의 임의의 위치에서 시작해서, 인접해 있는 네 방향으로 다섯 번 이동하면서,
각 칸에 적혀있는 숫자를 차례로 붙이면 6자리의 수가 된다. => dfs
이동을 할 때에는 한 번 거쳤던 칸을 다시 거쳐도 되며, -> ?
0으로 시작하는 000123과 같은 수로 만들 수 있다.

숫자판이 주어졌을 때, 만들 수 있는 서로 다른 여섯 자리의 수들의 개수를 구하는 프로그램을 작성하시오.

문제 분석: 2차원 행렬, dfs, 재귀, 스택
    ++ Map<Key, Value> : Key는 중복이 불가능하고, Value는 중복이 가능하다.
    HashSet : 중복을 허용하지 않는다. (순서가 없다.)
탐색 중 5*5 크기를 벗어나지 않게

## 설계
- 5x5 숫자판 위에서 임의의 위치에서 시작해 상하좌우로 이동하면서 만들 수 있는 모든 6자리 수의 개수를 찾음
- 두 가지 방식으로 깊이 우선 탐색(DFS)을 구현하여 문제를 해결(재귀를 사용한 방식과 스택을 사용한 방식)
- 모든 시작점에 대해 상하좌우로 이동하며 6자리 수를 만들고, 중복되지 않는 수들을 set에 저장하여 최종적으로 set의 크기를 출력
- 재귀 방식과 스택 방식 모두 각 위치에서 가능한 모든 경로를 탐색하여 문제를 해결 : 재귀 방식은 메서드 호출 스택을 사용하여 탐색을 진행하는 반면,
스택 방식은 명시적인 Stack 객체를 사용하여 동일한 작업을 수행합니다.

 */
public class BOJ2210 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(line[j]);

            }
        }
        sc.close();
        System.out.println(Arrays.deepToString(board));
        // 모든 시작점에 대해 DFS 실행(재귀 방식)
        // 시작점(r, c)에 대해 dfsByRecursion(r, c, 0, 0, board)를 실행
        // 총 25개의 시작점 -> 25번의 dfsByRecursion 호출
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                dfsByRecursion(r, c, 0, 0, board);
            }
        }

        System.out.println(set);
        System.out.println("set.size() = " + set.size());
    }

    // 1. DFS의 결과를 저장
    // 2. 중복을 제거하기 위해 Set을 사용
    static Set<Integer> set = new HashSet<>();

    public static void dfsByRecursion(
        int r, int c, int depth, int num, int[][] board){
        //6자리 수 완성

        if(depth == 6){
            set.add(num);
            return;
        }
        // 제약조건을 어겼을 때, 경계 체크
        if(r < 0 || r >= 5 || c < 0 || c >= 5){
            return;
        }

        // 상하좌우 이동
        int new_number = board[r][c]; //현재 방문한 숫자 패드의 숫자
        dfsByRecursion(r + 1, c, depth + 1, num * 10 + new_number, board);
        dfsByRecursion(r -1, c, depth + 1, num * 10 + new_number, board);
        dfsByRecursion(r, c +1, depth + 1, num * 10 + new_number, board);
        dfsByRecursion(r, c -1, depth + 1, num * 10 + new_number, board);

     }
        //스택을 활용한 DFS 구현
        public static void dfsByStack(int r, int c, int[][] board){
            Stack<int[]> stack = new Stack<>();
            stack.push(new int[]{r, c, 0, 0});
            while(!stack.isEmpty()) {
                int[] nextNode = stack.pop();

                // 다음 방문할 위치 및 현재까지 만든 수를 스택에 저장
                int nextR = nextNode[0];
                int nextC = nextNode[1];
                int depth = nextNode[2];
                int num = nextNode[3];
                // 취향에 따라 멤버변수로 stack에 넣어도 됨
                // 멤버변수로 선언하면 dfsByStack 메서드의 매개변수에서 제거 가능

                //6자리 수 완성 및 경계 체크
                if(depth == 6) {
                    set.add(num);
                    continue;
                }
                if(nextR < 0 || nextR == 5 || nextC < 0 || nextC == 5){
                    continue;
                }
                //상하좌우 이동
                int newNumber = num * 10 + board[nextR][nextC];
                stack.push(new int[]{nextR+1, nextC, depth + 1, newNumber});
                stack.push(new int[]{nextR-1, nextC, depth + 1, newNumber});
                stack.push(new int[]{nextR, nextC+1, depth + 1, newNumber});
                stack.push(new int[]{nextR, nextC-1, depth + 1, newNumber});


            }
        }
}
