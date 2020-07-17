package com.yzd.jutils.lombokExt.t1;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    public static void main(String[] args) {
        //User user=User.newUser();
        //
        UserDTO userDTO=UserDTO.newUserDTO().setUsername("new");
        User user = userDTO.convertToUser();
        UserDTO result = userDTO.convertFor(user);
    }
}
