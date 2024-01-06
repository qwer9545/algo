import java.util.Arrays;

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        
        int row1 = arr1.length;
        int col1 = arr1[0].length;

        //배열2 크기 구하기(n*p)
        int row2 = arr2.length;
        int col2 = arr2[0].length;

        //결과 배열 초기화(m*p)
        int[][] answer = new int[row1][col2];

        //행렬 곱셈 계산
        for(int r1 = 0; r1 <row1; r1++){
            for(int c2= 0;c2 <col2; c2++){
                int sum = 0;
                for(int v = 0; v < col1;v++){
                    sum+= arr1[r1][v] * arr2[v][c2];
                    //? 
                }
                //결과 배열에 sum 값 추가
                answer[r1][c2] = sum;
            }
        }
        for (int[] r : answer) {
            System.out.println(Arrays.toString(r));
        }
        return answer;
    }
}