package com.yzd.jutils.fastjsonFilter.model;

import com.yzd.jutils.fastjsonFilter.SensitiveLogInfo;
import com.yzd.jutils.fastjsonFilter.SensitiveLogType;

public class CardInfo {
    private String userId;
    private String name;
    @SensitiveLogInfo(type = SensitiveLogType.ID_CARD)
    private String certId;
    @SensitiveLogInfo(type = SensitiveLogType.BANK_CARD)
    private String cardId;
    private String bank;
    @SensitiveLogInfo(type = SensitiveLogType.PHONE)
    private String phone;
    @SensitiveLogInfo(type = SensitiveLogType.PASSWORD)
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
