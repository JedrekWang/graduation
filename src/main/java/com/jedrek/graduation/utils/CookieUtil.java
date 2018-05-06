package com.jedrek.graduation.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;

public class CookieUtil {
    public static void main(String[] args) throws Exception {
        String md5Hex = DigestUtils.md5Hex(new FileInputStream("D:\\code\\douban_spider.rar"));
        System.out.println(md5Hex);
    }

    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), key)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
