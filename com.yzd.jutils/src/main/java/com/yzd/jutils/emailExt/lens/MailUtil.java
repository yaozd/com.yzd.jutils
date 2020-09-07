package com.yzd.jutils.emailExt.lens;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author yawei
 * @date 2020/2/17 上午9:04
 */
public class MailUtil {

    /**
     * Send email.
     *
     * @param mailConfig 邮箱配置
     * @param content    发送内容
     * @param subject    主题
     * @param to         收件人
     * @throws EmailException
     */
    public static String sendHTMLMail(MailConfig mailConfig, String content, String subject, String... to) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(mailConfig.getMail_smtp_host());
        email.setSmtpPort(mailConfig.getMail_smtp_port());
        if (mailConfig.getMail_smtp_username() != null && mailConfig.getMail_smtp_password() != null) {
            email.setAuthentication(mailConfig.getMail_smtp_username(), mailConfig.getMail_smtp_password());
        }
        email.setCharset("UTF-8");
        email.setFrom(mailConfig.getMail_smtp_from());
        email.setMsg(content);
        email.setSubject(subject);
        email.addTo(to);
        return email.send();
    }
}
