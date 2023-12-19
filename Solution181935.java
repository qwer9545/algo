/*
양의 정수 n이 매개변수로 주어질 때, n이 홀수라면 n 이하의 
홀수인 모든 양의 정수의 합을 return 하고 n이 짝수라면 n 이하의 
짝수인 모든 양의 정수의 제곱의 합을 return 하는 solution 함수를 작성해 주세요.
*/
package algo;

class Solution181935 {
    public static void main(String[] args) {


    int answer1 = 0;
    int answer2 = 0;
    int n = 7;

    if(n%2==0)    {
        for (int i = 2; i < n; i * 2) {
            answer1 += i * i;
        }
    } else {
        for (int j = 1; j < n; j++) {
            answer2 += j;
        }
    }
}
}