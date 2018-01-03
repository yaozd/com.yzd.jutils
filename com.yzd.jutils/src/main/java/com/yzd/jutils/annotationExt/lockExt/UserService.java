package com.yzd.jutils.annotationExt.lockExt;


import com.yzd.jutils.annotationExt.redisCacheExt.RedisCache;
import com.yzd.jutils.print.PrintUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @BaodanLock(key = BaodanLockEnum.HK)
    public Integer get(IdForBaodanLock idForBaodanLock, Integer id) {
        PrintUtil.outLn("UserService.get()=step 01");
        return 1;
    }
}
