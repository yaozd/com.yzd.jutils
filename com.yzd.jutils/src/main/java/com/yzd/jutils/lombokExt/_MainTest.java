package com.yzd.jutils.lombokExt;

import com.yzd.jutils.bean.BeanToMapUtils;
import com.yzd.jutils.fastjson.FastJsonUtil;
import com.yzd.jutils.xml.XmlUtil2;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _MainTest {
    //使用lombok提高编码效率
    //https://blog.csdn.net/v2sking/article/details/73431364

    //toString 转为实体类
    @Test
    public void t1(){
        String str="userId=107959, prodId=511505, compName=弘康人寿保险股份有限公司, prodName=多倍保重大疾病保险, polNo=86110020180210035473, toubaoren=潘果洪, toubaorenIdType=0, toubaorenId=452127199404052416, addr=上海市市辖区长宁区广西马山县里当乡青龙村豪上屯1号, tel=17736630704, insured=潘果洪, insIdType=0, insIdNo=452127199404052416, insMobile=17736630704, relationtoAppnt=00, insuredSex=null, prem=4041.0, mainriskPrem=4020.0, faceAmount=3300000.0, premDate=2018-4-3 21:56:37, premType=1, premPeriod=30, benefitPeriodType=0, benefitPeriod=106, addTime=2018-4-3 21:56:37, valiDate=2018-04-04, status=1, updateTime=null, adviceCode=JDSXB, polUrl=http://www.hongkang-life.com/fileUpload/downLoadEPolicyPDF.do?contNo=2CAA037174D5A708ECF186496D6A4DE5CD3DE3D3ECACA5A3, cardNo=null, addriskProdId=521506, addriskProdName=弘康重大疾病医疗保险, addriskPrem=21.0, addriskFaceAmount=3000000.0, addriskPremDate=2018-4-3 21:56:37, addriskPremType=0, addriskPremPeriod=1, addriskBenefitPeriodType=Y, addriskBenefitPeriod=1, bnfss=null";
        Map<String,String> paraMap=new HashMap<String,String>();
        String[] para=str.split(",");
        for(int i=0,len=para.length;i<len;i++){
            String[] temp=para[i].split("=");
            paraMap.put(temp[0].trim(), temp[1]);
        }
        Object obj= BeanToMapUtils.convertMap(HKSysResquestData.class,paraMap);
        String itemJsonStr= FastJsonUtil.serialize(obj);
        HKSysResquestData objItem= FastJsonUtil.deserialize(itemJsonStr,HKSysResquestData.class);
    }
    //toString 转为实体类-整理版
    @Test
    public void t2(){
        String str="userId=107959, prodId=511505, compName=弘康人寿保险股份有限公司, prodName=多倍保重大疾病保险, polNo=86110020180210035473, toubaoren=潘果洪, toubaorenIdType=0, toubaorenId=452127199404052416, addr=上海市市辖区长宁区广西马山县里当乡青龙村豪上屯1号, tel=17736630704, insured=潘果洪, insIdType=0, insIdNo=452127199404052416, insMobile=17736630704, relationtoAppnt=00, insuredSex=null, prem=4041.0, mainriskPrem=4020.0, faceAmount=3300000.0, premDate=2018-4-3 21:56:37, premType=1, premPeriod=30, benefitPeriodType=0, benefitPeriod=106, addTime=2018-4-3 21:56:37, valiDate=2018-04-04, status=1, updateTime=null, adviceCode=JDSXB, polUrl=http://www.hongkang-life.com/fileUpload/downLoadEPolicyPDF.do?contNo=2CAA037174D5A708ECF186496D6A4DE5CD3DE3D3ECACA5A3, cardNo=null, addriskProdId=521506, addriskProdName=弘康重大疾病医疗保险, addriskPrem=21.0, addriskFaceAmount=3000000.0, addriskPremDate=2018-4-3 21:56:37, addriskPremType=0, addriskPremPeriod=1, addriskBenefitPeriodType=Y, addriskBenefitPeriod=1, bnfss=null";
        Map<String,String> paraMap=LombokUtil2.stringToBean(str);
        Object obj= LombokUtil2.convertMap(HKSysResquestData.class,paraMap);
        String itemJsonStr= FastJsonUtil.serialize(obj);
        HKSysResquestData objItem= FastJsonUtil.deserialize(itemJsonStr,HKSysResquestData.class);
    }
}
