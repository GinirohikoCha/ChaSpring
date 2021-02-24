package com.github.ginirohikocha.spring.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/24 17:59
 */
public class SecurityUtil {

    public static String generateRandomSalt() {
        return Base64.encodeBase64String(RandomStringUtils.random(5).getBytes());
    }

    public static String MD5 (String password, String salt) {
        return new Md5Hash(password, salt, 3).toString();
    }
}
