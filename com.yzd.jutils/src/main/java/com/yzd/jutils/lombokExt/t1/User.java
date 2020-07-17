package com.yzd.jutils.lombokExt.t1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: yaozh
 * @Description:
 */
@Accessors(chain = true)
@Setter
@Getter
//@RequiredArgsConstructor(staticName = "newUser")
@NoArgsConstructor
public class User {
    @NotNull
    private String username;
    @NotNull
    private int age;
}
