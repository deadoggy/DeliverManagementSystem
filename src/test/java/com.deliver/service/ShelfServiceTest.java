package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import com.deliver.model.Shelf;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by 91574 on 2017/5/13.
 */
public class ShelfServiceTest extends BaseServiceTest {

    @Autowired
    private ShelfService shelfService;

    @Test
    public void addShelf(){
/*        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mShelfId","1");
        jsonObject.put("mEmptySum",3);
        jsonObject.put("mPositionSum",1);
        jsonObject.put("mLayerSum",1);
        jsonObject.put("mColumnSum",2);
//        jsonObject.put()
        boolean flag=shelfService.addShelf("1",1,2);
        assertTrue(flag);*/
    }


    @Test
    public void get(){
        Shelf s=shelfService.getShelfById("1");
        System.out.println(s);
    }

    @Test
    public void getAll(){
        List<Shelf> shelf=shelfService.getAllShelf();
        for (Shelf s: shelf
             ) {
            System.out.println(s.getmShelfId()+" "+s.getmPosition()+" ");
        }
    }
}
