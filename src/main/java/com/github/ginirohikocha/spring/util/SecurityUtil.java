package com.github.ginirohikocha.spring.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author GinirohikoCha
 * @since 2021/5/20
 */
public class SecurityUtil {

    public static void main(String[] args) {
        String credential = "123456";
        String salt = generateRandomSalt();
        String result = md5(credential, salt);
        System.out.println(salt);
        System.out.println(result);
    }

    public static String generateRandomSalt() {
        return Base64.encodeBase64String(RandomStringUtils.random(5).getBytes());
    }

    public static String md5 (String credential, String salt) {
        return new Md5Hash(credential, salt, 3).toString();
    }
}
