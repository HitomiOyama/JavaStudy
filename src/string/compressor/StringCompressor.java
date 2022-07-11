package string.compressor;

import java.util.Arrays;

public class StringCompressor {
    public String encode(String givenString) {
        String encodedString = "a";
        System.out.println(givenString);
        String[] givenStringArray = givenString.split("");

        System.out.println(Arrays.toString(givenString.split("")));

        for (int i = 1; i < givenStringArray.length; i++) {
            if (givenStringArray[i].equals(givenStringArray[i - 1])) {

            }
        }

        return encodedString;
    }
}
