package com.yzd.jutils.cloneExt;

import com.yzd.jutils.fastjson.FastJsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: yaozhendong
 * @create: 2019-11-05 16:05
 **/
@Slf4j
public class ListUtil {
    private ListUtil() {

    }

    /**
     * 深拷贝
     *
     * @param src
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<T> deepCopy(List<T> src, Class<T> clz) {
        try {
            String json = FastJsonUtil.serialize(src);
            return FastJsonUtil.deserializeList(json, clz);
        } catch (Exception ex) {
            log.error("deepCopy method:", ex);
            throw new IllegalStateException(ex);
        }

    }

}
