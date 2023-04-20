package com.standard.untils;

import com.standard.Common.Constant.JwtConstant;
import com.standard.entity.checkResult;
import io.jsonwebtoken.*;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtils {


    public static String createJwt(String id,String subject,long ttlMillis){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);

        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("尘落") //签发者
                .setIssuedAt(now)
                .signWith(signatureAlgorithm,secretKey);

        System.out.println("下发的签名: " + builder);
        if(ttlMillis >=0 ){
            System.out.println("jwt使用时长: "+ttlMillis);
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    public static SecretKey generalKey(){
        byte[] encodeKey = Base64.getDecoder().decode(JwtConstant.JWT_SECRET);
        return new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
    }


    public static String genJwtToken(String username){
        return createJwt(username,username,60*60*1000);
    }


    public static checkResult validateJWT(String jwtStr){
        checkResult checkResult = new checkResult();
        Claims claims = null;
        try{
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        }catch (ExpiredJwtException e){
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        }catch (SignatureException e){
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }catch (Exception e){
            e.printStackTrace();
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_NULL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    public static Claims parseJWT(String jwt){
        SecretKey secretKey = generalKey();
        System.out.println(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody());
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

}
