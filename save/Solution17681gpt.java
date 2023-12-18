package algo;

import org.junit.jupiter.api.Test;

@Test
public class Solution17681gpt {
    public static void main(String[] args) {
        // Sample input
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        String[] result = solution(n, arr1, arr2);

        // Print the result if needed
        for (String row : result) {
            System.out.println(row);
        }
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            // Use Integer.toBinaryString to convert integers to binary strings
            String binary1 = Integer.toBinaryString(arr1[i]);
            String binary2 = Integer.toBinaryString(arr2[i]);

            // Pad the binary strings with leading zeros if needed
            binary1 = String.format("%" + n + "s", binary1).replace(' ', '0');
            binary2 = String.format("%" + n + "s", binary2).replace(' ', '0');

            // Combine the two binary strings using bitwise OR
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (binary1.charAt(j) == '1' || binary2.charAt(j) == '1') {
                    row.append("#");
                } else {
                    row.append(" ");
                }
            }

            // Add the combined row to the answer array
            answer[i] = row.toString();
        }

        return answer;
    }
}
