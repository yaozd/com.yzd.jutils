package com.yzd.jutils.JWTExt.t1;

import com.yzd.jutils.fastjson.FastJsonUtil;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

/***
 *
 *
 * Created by yzd on 2018/9/10 18:07.
 */

public class JWTUtil3Test {
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    //@PerfTest(threads = 100,invocations=100000)
    public void createToken() {
        String token=JWTUtil3.createToken(null);
        System.out.println(token);
        //String token1=JWTUtil3.createToken(1);
        //System.out.println(token1);
    }

    @Test
    @PerfTest(threads = 100,duration=150000)
    //@PerfTest(threads = 100,invocations=100000)
    public void verifyToken() {
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5emQiLCJleHAiOjE1MzY2MzcxNTEsImlhdCI6MTUzNjYzNjg1MX0.qaEb6g5El1rDEaAuBK0qMkepNptyrErexW8t2PIB3as";
        VerifyResultJWT verifyResultJWT=JWTUtil3.verifyToken(token);
        //System.out.println(FastJsonUtil.serialize(verifyResultJWT));
    }

    @Test
    public void refreshToken() {
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5emQiLCJleHAiOjE1MzY2MzU2OTksImlhdCI6MTUzNjYzNTM5OX0.mzUSqRYOjkvl0L77BcBIdgX0K8cdNmHgH2PScY7y7zA";
        RefreshResultJWT refreshResultJWT= JWTUtil3.refreshToken(token,Integer.class);
        System.out.println(FastJsonUtil.serialize(refreshResultJWT));
    }
}