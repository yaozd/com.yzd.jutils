package com.yzd.jutils.emailExt.lens;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yawei
 * @date 2020/2/12 下午4:11
 */
@Getter
@Setter
public class MailConfig {
    private String mail_smtp_host;
    private Integer mail_smtp_port;
    private String mail_smtp_from;
    private String mail_smtp_username;
    private String mail_smtp_password;
}
