package utils;

import java.util.UUID;

public class StringUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static boolean equalsIgnoreCase(String text1, String text2) {
        return text1 == null ? text2 == null : text1.equalsIgnoreCase(text2);
    }
    public static boolean equals(String text1, String text2) {
        return text1 == null ? text2 == null : text1.equals(text2);
    }
    public static String lowerCamelCase(String plainText) {
        StringBuilder sb = new StringBuilder();
        sb.append(plainText.substring(0, 1).toLowerCase());
        sb.append(plainText.substring(1));
        return sb.toString();
    }
}
