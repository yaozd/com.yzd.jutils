package com.yzd.jutils.lombokExt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by zjx on 2017/12/26.
 */
@NoArgsConstructor
@Data
public class HKSysResquestData {
    /**
     *第三方用户id
     */
    public String userId;
    /**
     *产品编码（弘康人寿保险公司定义的核心险种编码）
     */
    public String prodId;
    /**
     *公司名称（弘康人寿保险股份有限公司）
     */
    public String compName;
    /**
     * 产品名称（弘康人寿保险股份有限公司）
     */
    public String prodName;
    /**
     *保单号
     */
    public String polNo;
    /**
     *投保人姓名
     */
    public String toubaoren;
    /**
     *投保人证件类型
     */
    public String toubaorenIdType;
    /**
     *投保人证件号码
     */
    public String toubaorenId;
    /**
     *投保人地址
     */
    public String addr;
    /**
     *投保人手机号
     */
    public String tel;
    /**
     *被保人姓名
     */
    public String insured;
    //-----被保人信息------1.5 add start
    /**
     *被保人证件类型
     */
    public String insIdType;
    /**
     *被保人证件号码
     */
    public String insIdNo;
    /**
     *被保人手机（如果有）
     */
    public String insMobile;
    /**
     *投保人关系
     */
    public String relationtoAppnt;
    /**
     * 被保人性别
     */
    public String insuredSex;
    //-----------1.5 add  end
    /**
     *总保费
     */
    public String prem;
    //-----保费------1.5 add start
    /**
     *保费（主险）
     */
    public String mainriskPrem;
    //-----------1.5  add  end
    /**
     *保额
     */
    public String faceAmount;
    /**
     *缴费日期（yyyy-MM-dd HH:mm:ss）
     */
    public String premDate;
    /**
     *0=趸交或者一次交清；1=期缴；2=趸交但可续保
     */
    public String premType;
    /**
     *缴费年期，xx年
     */
    public String premPeriod;
    /**
     *保障期间类型，0表示到xx岁，1表示xx年
     */
    public String benefitPeriodType;
    /**
     *保障期间，和上面的类型一起用
     */
    public String benefitPeriod;
    /**
     *订单生成时间(yyyy-MM-dd HH:mm:ss)
     */
    public String addTime;
    /**
     *保单生效日（yyyy-MM-dd）
     */
    public String valiDate;
    /**
     *0，承保中，1承保有效，2缴费逾期，3.失效，4，退保
     */
    public String status;
    /**
     *更新时间（第三方入库更新时间，弘康人寿不予回传）
     */
    public String updateTime;
    /**
     *渠道码
     */
    public String adviceCode;
    /**
     *电子保单下载地址
     */
    public String polUrl;
    /**
     *激活卡卡号
     */
    public String cardNo;

    //-------附加险信息----1.5 add start
    /**
     *产品编码
     */
    public String addriskProdId;
    /**
     *产品名称
     */
    public String addriskProdName;
    /**
     *保费
     */
    public String addriskPrem;
    /**
     *保额
     */
    public String addriskFaceAmount;
    /**
     *缴费日期
     */
    public String addriskPremDate;
    /**
     *0=趸交或者一次性交清；1=期缴；2=趸交但可续保
     */
    public String addriskPremType;
    /**
     *缴费年期
     */
    public String addriskPremPeriod;
    /**
     *保障期间类型，0表示到xx岁，1表示xx年
     */
    public String addriskBenefitPeriodType;
    /**
     *保障期间
     */
    public String addriskBenefitPeriod;
    /**
     *受益人
     */
    //-------附加险信息----1.5 add end


}

