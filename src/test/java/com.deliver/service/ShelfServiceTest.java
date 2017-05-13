package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import com.deliver.model.Shelf;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 91574 on 2017/5/13.
 */
public class ShelfServiceTest extends BaseServiceTest {

    @Autowired
    private ShelfService shelfService;

    @Test
    public void addShelf(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mShelfId","1");
        jsonObject.put("mEmptySum",3);
        jsonObject.put("mPositionSum",1);
        jsonObject.put("mLayerSum",1);
        jsonObject.put("mColumnSum",2);
        boolean flag=shelfService.addShelf(jsonObject);
        System.out.println(flag);
    }
}
