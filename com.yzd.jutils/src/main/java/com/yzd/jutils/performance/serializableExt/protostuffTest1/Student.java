package com.yzd.jutils.performance.serializableExt.protostuffTest1;

import io.protostuff.Tag;

import java.io.Serializable;

public class Student  implements Serializable {
    private static final long serialVersionUID = -3646646866678777010L;
    @Tag(1)
    private String name;  
    @Tag(2)  
    private String studentNo;  
    @Tag(3)  
    private int age;  
    @Tag(4)  
    private String schoolName;  
    // @Tag标识方法存在一个问题就是数据版本问题-如果数据放在缓存中，如果数据结构发生更改，则会造成无法序列化
    // 可通过private static final long serialVersionUID = -36435658415657806L;但要每次手动生成
    // 因此在缓存中要将数据结构版本号做为一个参数
    // 关于@Tag,要么所有属性都有@Tag注解,要么都没有,不能一个类中只有部分属性有@Tag注解
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getStudentNo() {  
        return studentNo;  
    }  
  
    public void setStudentNo(String studentNo) {  
        this.studentNo = studentNo;  
    }  
  
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
  
    public String getSchoolName() {  
        return schoolName;  
    }  
  
    public void setSchoolName(String schoolName) {  
        this.schoolName = schoolName;  
    }  
  
    @Override  
    public String toString() {  
        return "Student{" +  
                "name='" + name + '\'' +  
                ", studentNo='" + studentNo + '\'' +  
                ", age=" + age +  
                ", schoolName='" + schoolName + '\'' +  
                '}';  
    }
}
