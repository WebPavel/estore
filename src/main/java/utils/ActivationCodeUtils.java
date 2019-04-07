package utils;

import java.util.UUID;

public class ActivationCodeUtils {

    public static String getActivation_code() {
        return UUID.randomUUID().toString();
    }
}
