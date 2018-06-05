package com.yzd.jutils.enumExt_Permission;

public enum TaskEnum {
    个人(1,"个人任务"),
    团长(2,"团长任务"),
    企业(4,"企业任务"),
    全部(0,"执行所有任务");
    // 成员变量
    private String name;
    private int id;

    // 构造方法
    private TaskEnum(int id, String name) {
        this.setName(name);
        this.setId(id);
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
    public int getValue() {
        //全部=执行所有任务
        if(this.id==0){
            int all=0;
            for (TaskEnum c : TaskEnum.values()) {
                all=c.getId()|all;
            }
            return all;
        }
        return id;
    }
}
