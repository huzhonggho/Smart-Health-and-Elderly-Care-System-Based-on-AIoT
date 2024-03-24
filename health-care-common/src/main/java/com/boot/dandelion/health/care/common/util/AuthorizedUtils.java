package com.boot.dandelion.health.care.common.util;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class AuthorizedUtils {

    private static final String ACCOUNT = "TestUser";
    private static final String PASSWORD = "T123456";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String generateAuthorizationCode(String type) {
        String date = getCurrentDate();

        String json = String.format("[{\"Account\":\"%s\",\"Pwd\":\"%s\",\"date\":\"%s\",\"Type\":\"%s\"}]",
                ACCOUNT, PASSWORD, date, type);
        return base64Encode(json);
    }
    private static String getCurrentDate() {
        // Replace this with your preferred date formatting logic if needed
        return java.time.LocalDateTime.now().toString();
    }
    private static String base64Encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

}
