package com.deliver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.model.Shelf;
import com.deliver.model.StoragePosition;
import com.deliver.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 91574 on 2017/5/29.
 */
@RestController
public class ShelfController {
    @Autowired
    private ShelfService shelfService;

    @RequestMapping(value = "/shelf/{id}", method = RequestMethod.GET)
    public String getShelf(@PathVariable String id) {
        JSONObject jsonObject = new JSONObject();
        Shelf shelf = shelfService.getShelfById(id);
        if (shelf == null) {
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "找不到对应id的的货架");
            return jsonObject.toString();
        } else {
            jsonObject.put("status", "ok");
            jsonObject.put("shelf", shelf);
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/shelf/all", method = RequestMethod.GET)
    public String getAllPackage() {
        List<Shelf> shelfList = shelfService.getAllShelf();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        JSONArray jsonArray = new JSONArray();
        for (Shelf shelf : shelfList) {
            jsonArray.add(shelf);
        }
        jsonObject.put("shelfs", jsonArray);
        return JSONObject.toJSONString(jsonObject);
    }

    @RequestMapping(value = "/shelf", method = RequestMethod.POST)
    public String add(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        String id = httpServletRequest.getParameter("id");
        if (shelfService.getShelfById(id) != null) {
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "货架id重复");
            return jsonObject.toString();
        }
        boolean flag = shelfService.addShelf(id, Integer.parseInt(httpServletRequest.getParameter("columnSum")),
                Integer.parseInt(httpServletRequest.getParameter("layerSum")));

        if (flag == false) {
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "货架信息不符合要求");
            return jsonObject.toString();
        } else {
            jsonObject.put("status", "ok");
            return jsonObject.toString();
        }
    }

    //从货架上获取一个空位置
    @RequestMapping(value = "/shelf/position", method = RequestMethod.GET)
    public String getPosition() {
        JSONObject jsonObject = new JSONObject();
        StoragePosition storagePosition = shelfService.getPosition();
        if (storagePosition == null) {
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "货架没有空位置了");
            return jsonObject.toString();
        } else {
            jsonObject.put("status", "ok");
            jsonObject.put("storagePosition", storagePosition);
            return jsonObject.toJSONString();
        }
    }

}
