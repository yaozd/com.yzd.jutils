package com.yzd.jutils.guava.listExt;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by zd.yao on 2017/6/16.
 */
@Data
@NoArgsConstructor
public class ShortcutMapForm {
    private Integer colorId;
    List<ShortcutForm> shortcutFormList;
}

