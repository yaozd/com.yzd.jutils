package com.yzd.jutils.guava.optionalExt;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


/**
 * Created by zd.yao on 2018/9/9.
 */
@Slf4j
public class OptionalExample {

    //[Google Guava] 1.1-使用和避免null
    //http://ifeve.com/google-guava-using-and-avoiding-null/
    public static void main(String[] args) {
        Optional<UserInfo> userInfo = Optional.ofNullable(getUserInfo());
        if (!userInfo.isPresent()){
            log.info("userInfo is null");
        }
        //为null给默认值--推荐MoreObjects方法-byArvin-2018-09-09
        String ret=null;
        String val= MoreObjects.firstNonNull(ret, "默认值");//使用MoreObjects.firstNonNull
        //两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
        Optional<Long> projectIdOptional = Optional.ofNullable(getProjectId());
        //Long projectId = projectIdOptional.orElse(0L); // 如果projectId为null时，这值为0
        Long projectId = projectIdOptional.orElseGet(()->0L); // 如果projectId为null时，这值为0
    }

    public static UserInfo getUserInfo() {
        return null;
    }

    public static Long getProjectId() {
        return null;
    }

    @Getter
    @Setter
    class UserInfo{
        private String userName;
    }
}
