package com.yzd.jutils.guava.listExt;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zd.yao on 2017/6/16.
 */
@Data
@NoArgsConstructor
public class ShortcutForm {
    private Integer id;
    private String name;
    private String filePath;
    private String color;
    public static ShortcutForm toForm(Shortcut entity){
        ShortcutForm form=new ShortcutForm();
        form.setId(entity.getId());
        form.setName(entity.getName());
        return form;
    }
}
