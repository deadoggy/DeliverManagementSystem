package com.deliver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.model.Point;
import com.deliver.model.SendingRecord;
import com.deliver.model.Shelf;
import com.deliver.model.StoragePosition;
import com.deliver.service.BusinessPointService;
import com.deliver.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.deliver.constant.Constant.POSITION_IN_SHELF;

/**
 * Created by 91574 on 2017/5/29.
 */
@RestController
public class ShelfController {
    @Autowired
    private ShelfService shelfService;

    @Autowired
    private BusinessPointService businessPointService;

    @RequestMapping(value = "/shelf/{id}", method = RequestMethod.GET)
    public String getShelf(@PathVariable String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Shelf> shelfList = shelfService.getShelfs(id);
            if (shelfList == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "找不到对应id的的货架");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                JSONArray jsonArray = new JSONArray();
                for (Shelf shelf:shelfList) {
                    JSONObject shelfObject = new JSONObject();
                    shelfObject.put("shelfId",shelf.getmShelfId());
                    shelfObject.put("layer",shelf.getmLayerSum());
                    shelfObject.put("column",shelf.getmColumnSum());
                    shelfObject.put("sum",shelf.getmPositionSum());
                    shelfObject.put("empty",shelf.getmEmptySum());
                    jsonArray.add(shelfObject);
                }
                jsonObject.put("shelfList",jsonArray);
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/shelf/all", method = RequestMethod.GET)
    public String getAllPackage() {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Shelf> shelfList = shelfService.getAllShelf();
            jsonObject.put("status", "ok");
            JSONArray jsonArray = new JSONArray();
            for (Shelf shelf:shelfList) {
                JSONObject shelfObject = new JSONObject();
                shelfObject.put("shelfId",shelf.getmShelfId());
                shelfObject.put("layer",shelf.getmLayerSum());
                shelfObject.put("column",shelf.getmColumnSum());
                shelfObject.put("sum",shelf.getmPositionSum());
                shelfObject.put("empty",shelf.getmEmptySum());
                jsonArray.add(shelfObject);
            }
            jsonObject.put("shelfList",jsonArray);
            return jsonObject.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/shelf", method = RequestMethod.POST)
    public String add(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        try {
            String id = httpServletRequest.getParameter("id");
            if (shelfService.getShelfById(id) != null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "货架id重复");
                return jsonObject.toString();
            }
            Point point=businessPointService.getPoint(httpServletRequest.getParameter("businessId"));
            if(point==null){
                return "{\"status\": \"fail\", \"reason\" : \"没有此网点\"}";
            }
            boolean flag = shelfService.addShelf(id, Integer.parseInt(httpServletRequest.getParameter("columnSum")),
                    Integer.parseInt(httpServletRequest.getParameter("layerSum")),point);

            if (flag == false) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "货架信息不符合要求");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    //从货架上获取一个空位置
    @RequestMapping(value = "/shelf/position", method = RequestMethod.GET)
    public String getPosition() {
        JSONObject jsonObject = new JSONObject();
        try {
            StoragePosition storagePosition = shelfService.getPosition();
            if (storagePosition == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "货架没有空位置了");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                jsonObject.put("storageId",storagePosition.getmStorageId());
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

}
