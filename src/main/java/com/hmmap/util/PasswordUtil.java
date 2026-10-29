package com.hmmap.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class PasswordUtil {

    private PasswordUtil() {}

    // 평문 비밀번호 → SHA-256 해시 (64글자 16진수 문자열)
    public static String hash(String rawPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            // SHA-256은 모든 자바에 기본 포함이라 실제로는 발생하지 않음
            throw new IllegalStateException(e);
        }
    }
}