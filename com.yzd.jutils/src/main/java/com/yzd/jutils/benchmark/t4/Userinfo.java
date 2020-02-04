package com.yzd.jutils.benchmark.t4;

import java.util.Date;
import java.util.List;

/**
 * @ Description：用户信息
 * @ Author     ：xiaojiang
 * @ Date       ：Created in 2019-06-19
 * @ Version    ：0.0.1
 */
public class Userinfo {
    private String username;    //用户名
    private String gender;      //用户性别
    private Integer age;    //用户年龄
    private Date birthday;      //用户生日
    private List<String> address;   //  用户地址
    private Long createTime;    //用户创建时间

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", address=" + address +
                ", createTime=" + createTime +
                '}';
    }
}
