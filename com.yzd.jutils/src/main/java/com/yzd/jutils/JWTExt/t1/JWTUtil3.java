package com.yzd.jutils.JWTExt.t1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yzd.jutils.fastjson.FastJsonUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/***
 *
 *
 * Created by yzd on 2018/9/10 17:31.
 */

public class JWTUtil3 {
    //
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNfkjsdrow32234545fdf>?N<:{LWPW";
    //签发者
    private static final String ISSUER = "yzd";
    //用户信息JSON格式
    private static final String USER_JSON = "userJson";
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5*60*1000;
    //可以多次使用的校验实例
    private static final JWTVerifier jwtVerifier=getJWTVerifier();

    private static JWTVerifier getJWTVerifier() {
        return JWT.require(getAlgorithm())
                .withIssuer(ISSUER)
                .build();
    }

    /**
     * 创建 token,当user为null时是未登录的访问token
     *
     * @param user   用户信息
     * @return
     */
    public static <T> String createToken(T user){
        Long nowLong=System.currentTimeMillis();
        Date issuedDate=new Date(nowLong);
        Date expireDate=new Date(nowLong+EXPIRE_TIME);
        JWTCreator.Builder builder = JWT.create()
                //签发者
                .withIssuer(ISSUER)
                //签发时间
                .withIssuedAt(issuedDate)
                //token 过期时间
                .withExpiresAt(expireDate);
        //自定义数据
        if (user != null) {
            String objJson=FastJsonUtil.serialize(user);
            builder = builder.withClaim(USER_JSON, objJson);
        }
        String token = builder.sign(getAlgorithm());
        return token;
    }

    /***
     * 验证token,如果是已登录的授权token，则同时设置CurrentUserContextHolder的当前用户信息
     * @param token
     * @return
     */
    public static VerifyResultJWT verifyToken(String token) {
        if(StringUtils.isBlank(token)){return VerifyResultJWT.fail("token is null");}
        try{
            DecodedJWT jwt = jwtVerifier.verify(token);
            Claim userJsonClaim = jwt.getClaim(USER_JSON);
            String userJson=userJsonClaim.asString();
            //System.out.println(userJsonClaim);
            //System.out.println(userJsonClaim.asString());
            return VerifyResultJWT.success(userJson);
        }
        catch (JWTVerificationException ex){
            //ex.printStackTrace();
            return VerifyResultJWT.fail(ex.getMessage());
        }
    }

    /***
     * 刷新token
     * @param oldToken
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> RefreshResultJWT refreshToken(String oldToken, Class<T> clz){
        if (StringUtils.isBlank(oldToken)) {
            return RefreshResultJWT.fail("oldToken is Null");
        }
        VerifyResultJWT verifyResultJWT=verifyToken(oldToken);
        if(!verifyResultJWT.getIsOk()){
            return RefreshResultJWT.fail(verifyResultJWT.getErrorMsg());
        }
        T user=FastJsonUtil.deserialize(verifyResultJWT.getUserJson(),clz);
        String token=createToken(user);
        return RefreshResultJWT.success(token);
    }
    private static Algorithm getAlgorithm(){
        try {
            return Algorithm.HMAC256(SECRET);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
