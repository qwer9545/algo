public class Solution {
//    public static void main(String[] args) {

        public int solution (String s){
            int answer = 0;

            String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

            for(int i=0; i<arr.length;i++){
                if(s.contains(arr[i])) {
                    s= s.replace(arr[i], Integer.toString(i));
                }
            }
            return Integer.parseInt(s);
//        String s = "one4seveneight";
//        StringBuffer[] s = {"one4seveneight};
            // 알파벳 2개씩 스플릿? case나 if?, 숫자뒤에 붙인다 .append? +?
//        for(int i = 0; i < s.size(); i++){
//            if(s.get(i) == "o" && s.get(i+1) == "n")


//        String s2 = s.replaceAll("one", "1");
//        String s3 = s2.replaceAll("seven", "7");
//        String s4 = s3.replaceAll("eight", "8");
//        System.out.println("s4 = " + s4); //1478
//        Integer.parseInt(s4);

            //값 검색: contains
            //값 위치찾기: indexOf
        }


    }