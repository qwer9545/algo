class Solution {
    public int[] solution(int[] num_list, int n) {
	    int[] answer;
        int index = 0;
        
        //answer를 받을 리스트를 생각 못했음
        if(num_list.length % n == 0){
            answer = new int[num_list.length/n]; //nl:6, n:2  3칸
        } else{
            answer = new int[num_list.length/n+1]; //nl:6, n:4 2칸
        }
        
        
        //초기 값 0에 +n만큼 더한 인덱스의 값만 추가
        for(int i = 0; i < num_list.length; i+=n) {
	       	// answer[i]로 값 추가 실패, 별도의 인덱스를 추가해야한다.
	       	answer[index++] = num_list[i];  
	    }
        return answer;
    }
}