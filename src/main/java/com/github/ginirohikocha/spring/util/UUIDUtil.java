package com.github.ginirohikocha.spring.util;

import java.util.UUID;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/21 23:17
 */
public class UUIDUtil {

    /**
     * @return String 无'-'号，32字符长度的随即UUID
     */
    public static String generateRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @param uuid java.util.UUID
     * @return String 无'-'号，32字符长度的UUID
     */
    public static String parseUUID(UUID uuid) {
        return uuid.toString().replace("-", "");
    }

    /**
     * @param uuid 无'-'号，32字符长度的UUID
     * @return java.util.UUID
     */
    public static UUID parseString(String uuid) {
        return UUID.fromString(uuid.substring(0, 8)
                + '-' + uuid.substring(8, 12)
                + '-' + uuid.substring(12, 16)
                + '-' + uuid.substring(16, 20)
                + '-' + uuid.substring(20));
    }
}
