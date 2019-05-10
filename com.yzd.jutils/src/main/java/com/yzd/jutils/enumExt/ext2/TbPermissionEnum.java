package com.yzd.jutils.enumExt.ext2;

import java.util.Set;

public interface TbPermissionEnum {
    /**
     * 可见状态:VISIBLE(1=可见)、HIDDEN(2=隐藏)
     */
    enum visibleStatus {
        VISIBLE(1, "可见"),
        HIDDEN(2, "隐藏");

        public final Integer CODE;

        visibleStatus(int code, String desc) {
            this.CODE = code;
        }
        public static final Set<Integer> CODES=TbEnumExtend.getCodeList(visibleStatus.class);

    }
}
