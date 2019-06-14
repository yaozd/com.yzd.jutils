package com.yzd.jutils.JWTExt2.t1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yzd.jutils.fastjson.FastJsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * JSON Web Token (JWT)
 * https://www.cnblogs.com/cjsblog/p/9277677.html
 */
public class JwtUtil {
    private JwtUtil() {
        throw new IllegalStateException("Utility class");
    }

    //
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNfkjsdrow32234545fdf>?N<:{LWPW";
    //签发者
    private static final String ISSUER = "yzd";
    //用户信息JSON格式
    private static final String USER_JSON = "USER";
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5L * 60L * 1000L;
    //可以多次使用的校验实例
    private static final JWTVerifier jwtVerifier = getJWTVerifier();

    private static JWTVerifier getJWTVerifier() {
        return JWT.require(getAlgorithm())
                .withIssuer(ISSUER)
                .build();
    }

    /**
     * 创建 token,当user为null时是未登录的访问token
     *
     * @param user 用户信息
     * @return
     */
    public static <T> String createToken(T user) {
        Long nowLong = System.currentTimeMillis();
        Date issuedDate = new Date(nowLong);
        //JWT=>不做时间过期验证，只做数据合法性验证
        //expireDate设置为永久：2999-01-01=32472115200000
        Date expireDate = new Date(32472115200000L);
        JWTCreator.Builder builder = JWT.create()
                //签发者
                .withIssuer(ISSUER)
                //签发时间
                .withIssuedAt(issuedDate)
                //token 过期时间
                .withExpiresAt(expireDate);
        //自定义数据
        if (user != null) {
            String objJson = FastJsonUtil.serialize(user);
            builder = builder.withClaim(USER_JSON, objJson);
        }
        return builder.sign(getAlgorithm());
    }

    /***
     * 验证token,如果是已登录的授权token，则同时设置CurrentUserContextHolder的当前用户信息
     * @param token
     * @return
     */
    public static String verifyToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new CustomJwtException("验证token失败：token is null");
        }
        try {
            DecodedJWT jwt = jwtVerifier.verify(token);
            Claim userJsonClaim = jwt.getClaim(USER_JSON);
            return userJsonClaim.asString();
        } catch (JWTVerificationException ex) {
            throw new CustomJwtException("验证token失败："+ex.getMessage());
        }
    }
    public static boolean isExpireTime(long timestamp){
        return System.currentTimeMillis()>timestamp;
    }
    public static <T> T jsonToUser(String json,Class<T> clz){
        return FastJsonUtil.deserialize(json, clz);
    }

    /***
     * 刷新token
     * @param oldToken
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> String refreshToken(String oldToken, Class<T> clz) {
        if (StringUtils.isBlank(oldToken)) {
            throw  new CustomJwtException("刷新token失败：oldToken is Null");
        }
        try {
            DecodedJWT jwt = jwtVerifier.verify(oldToken);
            Claim userJsonClaim = jwt.getClaim(USER_JSON);
            String userJson = userJsonClaim.asString();
            T user = FastJsonUtil.deserialize(userJson, clz);
            return createToken(user);
        }catch (JWTVerificationException ex) {
            throw new CustomJwtException("刷新token失败："+ex.getMessage());
        }
    }

    private static Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(SECRET);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     *  过期时间-毫秒
     * @return
     */
    private static long getExpireTime(){
        return EXPIRE_TIME+System.currentTimeMillis();
    }

    // region 创建TOKEN

    /**
     * 创建Token
     * @param id 用户ID
     * @param name  用户名
     * @param expireTime    过期时间
     * @param dataType T:数据类型（0=非登录情况下TOKEN、1=登录情况下TOKEN）
     * @return
     */
    private static CurrentToken build(Long id,String name,Long expireTime,int dataType){
        CurrentToken token=new CurrentToken();
        token.setId(id);
        token.setName(name);
        token.setUuid(UUID.randomUUID().toString().replace("-",""));
        token.setT(dataType);
        token.setE(expireTime);
        //数据结构版本
        token.setV(CurrentTokenEnum.DataVersion.v1.CODE);
        return token;
    }

    /**
     * 非登录情况下用户TOKEN
     * @return
     */
    public static CurrentToken build4NoLogin(){
        return build(null,null,getExpireTime(),CurrentTokenEnum.DataType.noLogin.CODE);
    }

    /**
     * 登录情况下用户TOKEN
     * @param id
     * @param name
     * @return
     */
    public static CurrentToken build4Login(Long id,String name){
        return build(id,name,getExpireTime(),CurrentTokenEnum.DataType.login.CODE);
    }
    // end
}
