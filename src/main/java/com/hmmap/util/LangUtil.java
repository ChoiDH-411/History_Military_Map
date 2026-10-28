package com.hmmap.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LangUtil {

    public static final String DEFAULT_LANG = "ko";

    private LangUtil() {}

    // 세션에 저장된 언어를 반환. 없으면 "ko"
    public static String getLang(HttpServletRequest request) {
        HttpSession session = request.getSession(false);   // false: 세션이 없으면 새로 만들지 않음
        if (session != null && session.getAttribute("lang") instanceof String lang) {
            return lang;
        }
        return DEFAULT_LANG;
    }

    // 허용된 언어 코드인지 검사 (나중에 영어를 추가하면 여기에 "en" 추가)
    public static boolean isSupported(String code) {
        return "ko".equals(code) || "ja".equals(code);
    }
}