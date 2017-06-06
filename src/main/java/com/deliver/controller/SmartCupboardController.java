package com.deliver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.model.SmartCupboard;
import com.deliver.model.StoragePosition;
import com.deliver.service.SmartCupboardService;
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
public class SmartCupboardController {
    @Autowired
    private SmartCupboardService smartCupboardService;

    @RequestMapping(value = "/smartcupboard/{id}", method = RequestMethod.GET)
    public String getSmartCupboard(@PathVariable String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            SmartCupboard smartCupboard = smartCupboardService.getSmartCupboardById(id);
            if (smartCupboard == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "找不到对应id的的智能柜");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                jsonObject.put("smartcupboard", smartCupboard);
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/smartcupboard/all", method = RequestMethod.GET)
    public String getAllPackage() {
        JSONObject jsonObject = new JSONObject();
        try {
            List<SmartCupboard> smartCupboardList = smartCupboardService.getAllSmartCupboard();
            jsonObject.put("status", "ok");
            JSONArray jsonArray = new JSONArray();
            for (SmartCupboard smartCupboard : smartCupboardList) {
                jsonArray.add(smartCupboard);
            }
            jsonObject.put("smartcupboards", jsonArray);
            return JSONObject.toJSONString(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/smartcupboard", method = RequestMethod.POST)
    public String add(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        try {
            String id = httpServletRequest.getParameter("id");
            if (smartCupboardService.getSmartCupboardById(id) != null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "智能柜id重复");
                return jsonObject.toString();
            }
            boolean flag = smartCupboardService.addSmartCupboard(id, Integer.parseInt(httpServletRequest.getParameter("columnSum")),
                    Integer.parseInt(httpServletRequest.getParameter("layerSum")));

            if (flag == false) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "智能柜信息不符合要求");
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

    //从智能柜上获取一个空位置
    @RequestMapping(value = "/smartcupboard/position", method = RequestMethod.GET)
    public String getPosition() {
        JSONObject jsonObject = new JSONObject();
        try {
            StoragePosition storagePosition = smartCupboardService.getPosition();
            if (storagePosition == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "智能柜没有空位置了");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                jsonObject.put("column", storagePosition.getmColumn());
                jsonObject.put("layer", storagePosition.getmLayer());
                jsonObject.put("id", storagePosition.getmId());
                jsonObject.put("smartCupboardId", storagePosition.getmCup().getmCupboardId());
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
