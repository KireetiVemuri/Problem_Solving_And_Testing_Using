import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'timeConversion' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String timeConversion(String s) {
    // Write your code here
    // Extract the AM/PM modifier (last two characters)
    String modifier = s.substring(s.length() - 2);
    
    // Extract hour, minute, and second components
    int hour = Integer.parseInt(s.substring(0, 2));
    String minuteAndSecond = s.substring(2, 8);
    
    // Handle conversion logic based on AM/PM
    if (modifier.equalsIgnoreCase("AM")) {
        if (hour == 12) {
            hour = 0; // 12:00:00AM -> 00:00:00
        }
    } else if (modifier.equalsIgnoreCase("PM")) {
        if (hour != 12) {
            hour += 12; // 01:00:00PM -> 13:00:00, but 12:00:00PM stays 12:00:00
        }
    }
    
    // Format hour to 2 digits with leading zeros
    return String.format("%02d%s", hour, minuteAndSecond);

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
