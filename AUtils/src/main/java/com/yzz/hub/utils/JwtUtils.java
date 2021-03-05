package com.yzz.hub.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具
 *
 * @author LK
 * @date 2019/9/25
 */
public class JwtUtils {

    private static final String ACCESS_TOKEN = "access_token";

    private static final String SIGNING_KEY = "value";

    /**
     * 获取token中包含的信息
     *
     * @param token      token字符串
     * @param signingKey 秘钥
     * @return Claims jwt中包含的信息
     */
    public static Claims getClaims(String token, String signingKey) {
        if (StringUtils.isBlank(signingKey)) {
            System.out.println("tokenKey为空");
        }
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(signingKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token);
        return claimsJws.getBody();
    }

    /**
     * 将jwt规定返回的json中获取到access_token
     *
     * @param tokenResult 请求到的jwt信息
     * @return String jwt串
     */
    public static String getAccessToken(String tokenResult) {
        Map<String, Object> resultMap = new HashMap<>(6);
        try {
            resultMap = JsonUtils.json2map(tokenResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String token = (String) resultMap.get(ACCESS_TOKEN);
        if (StringUtils.isBlank(token)) {
            System.out.println("获取的token为空");
        }
        return token;
    }

    /**
     * 从获取到的秘钥json中得到signingKey
     *
     * @param signingKeyJson 秘钥json
     * @return String 秘钥
     */
    public static String getSigningKey(String signingKeyJson) {
        Map<String, Object> resultMap = new HashMap<>(6);
        try {
            resultMap = JsonUtils.json2map(signingKeyJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String) resultMap.get(SIGNING_KEY);
    }

}
