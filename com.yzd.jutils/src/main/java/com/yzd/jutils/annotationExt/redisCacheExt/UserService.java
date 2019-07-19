package com.yzd.jutils.annotationExt.redisCacheExt;

import com.google.common.base.Preconditions;
import com.yzd.jutils.print.PrintUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UserService {
    @RedisCache(key = CacheKeyConstant.UserService_getUser)
    public Integer get(Integer id) {
        PrintUtil.outLn("UserService.get()=step 01");
        return 1;
    }
    @RedisCache(key = CacheKeyConstant.UserService_getEmpty)
    public void empty(){

    }
    @RedisCache(key = "whereIsMap")
    public String whereIsMap(Map<String,Object>where){
        Preconditions.checkNotNull(where,"where must not be null");
        Integer k1= (Integer) where.get("k1");
        String k2=(String)where.get("k2");
        return "whereIsMap";
    }
}
