package com.yzd.jutils.autowiredExtJson;

import com.yzd.jutils.autowiredExtJson.model.CatModel;
import com.yzd.jutils.autowiredExtJson.model.JsonModel;
import com.yzd.jutils.autowiredExtJson.model.ModelEnum;
import com.yzd.jutils.fastjson.FastJsonUtil;
import org.junit.Test;

public class _MainTest {
    /**
     * 通过JSON的结构的方式实现无结构数据存储
     */
    @Test
    public void t() {
        CatModel catModel = new CatModel();
        catModel.setName("cat");
        //通过modelEnum查找数据的类型与版本号
        ModelEnum modelEnum = ModelEnum.CatModelE;
        //
        String catModelJson = FastJsonUtil.serialize(catModel);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setId(0L);
        jsonModel.setModelType(modelEnum.getModelType());
        jsonModel.setModelVersion(modelEnum.getModelVersion());
        jsonModel.setJsonData(catModelJson);
        //
        //通过modelEnum.CatModel获得数据库的查询条件，然后再反序列化
        CatModel catModel4Json = FastJsonUtil.deserialize(jsonModel.getJsonData(), CatModel.class);
    }
}
