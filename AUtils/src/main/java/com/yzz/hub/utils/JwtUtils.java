package com.yzz.hub.utils;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;

import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.util.Date;
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
    
    private static final String salt = "aabbcc";

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
    
    
    /**
     * 生成token
     * @param subject （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims 自定义身份信息
     * @return
     */
    public static String generateToken(String subject, int expirationSeconds, Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥
                .compact();
    }
    
    
    // 是否已过期
    public static boolean isExpiration(Date expirationTime){
        /*return getTokenBody(token).getExpiration().before(new Date());*/
        
        //通过redis中的失效时间进行判断
        Date currentTime = DateUtil.date();
        if(DateUtil.compare(currentTime,expirationTime) == 1
                || DateUtil.compare(currentTime,expirationTime) == 0){
            //当前时间比过期时间小，失效
            return true;
        }else{
            return false;
        }
    }
    
    public static void main(String[] args) {
        Date d1 = DateUtil.parse("2021-3-21", "yyyy-MM-dd");
        Date d2 = DateUtil.date();
        System.out.println(JwtUtils.isExpiration(d1));
    }


}
