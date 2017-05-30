package com.deliver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.model.DeliverCompany;
import com.deliver.model.Package;
import com.deliver.model.Point;
import com.deliver.model.StoragePosition;
import com.deliver.service.DeliverCompanyService;
import com.deliver.service.PackageService;
import com.deliver.service.StorageService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.api.scripting.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.deliver.constant.Constant.POSITION_FULL;

/**
 * Created by 91574 on 2017/5/24.
 */
@RestController
public class PackageController {
    @Autowired
    private PackageService packageService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private DeliverCompanyService deliverCompanyService;

    @RequestMapping(value = "/package/{id}", method = RequestMethod.GET)
    public String getAPackage(@PathVariable String id) {
        Package aPackage = packageService.getPackage(id);
        JSONObject jsonObject = new JSONObject();
        if (aPackage == null) {
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "找不到对应id的包裹");
            return jsonObject.toString();
        } else {
            jsonObject.put("status", "ok");
            jsonObject.put("package", aPackage);
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/package/tele/{tele}", method = RequestMethod.GET)
    public String getAPackageByTele(@PathVariable String tele) {
        List<Package> packageList = packageService.getPackageByTele(tele);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        JSONArray jsonArray = new JSONArray();
        for (Package aPackage : packageList) {
            jsonArray.add(aPackage);
        }
        jsonObject.put("packages", jsonArray);
        return jsonObject.toJSONString();
    }



    @RequestMapping(value = "/package/all", method = RequestMethod.GET)
    public String getAllPackage() {
        List<Package> packageList = packageService.getAllPackage();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        JSONArray jsonArray = new JSONArray();
        for (Package aPackage : packageList) {
            jsonArray.add(aPackage);
        }
        jsonObject.put("packages", jsonArray);
        return JSONObject.toJSONString(jsonObject);
    }

    @RequestMapping(value = "/package", method = RequestMethod.POST)
    public String add(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        String id = httpServletRequest.getParameter("id");
        if (packageService.getPackage(id) != null) {
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "包裹id重复");
            return jsonObject.toString();
        }
        StoragePosition storagePosition=storageService.getStorageById(Integer.parseInt(httpServletRequest.getParameter("storageId")));
        if(storagePosition==null||storagePosition.ismEmpty()==POSITION_FULL){
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "存储位置已经有货物");
            return jsonObject.toString();
        }
        DeliverCompany deliverCompany=deliverCompanyService.getDeliverCompany(httpServletRequest.getParameter("companyName"));
        if(deliverCompany==null){
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "没有这家快递公司");
            return jsonObject.toString();
        }

        boolean flag = packageService.addPackage(id, deliverCompany,
                httpServletRequest.getParameter("receiverName"), httpServletRequest.getParameter("receiverTele"),
                Boolean.parseBoolean(httpServletRequest.getParameter("cupOrShelf")),
                storagePosition);

        if (flag == false) {
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "包裹信息不符合要求");
            return jsonObject.toString();
        } else {
            jsonObject.put("status", "ok");
            return jsonObject.toString();
        }
    }

    @RequestMapping(value = "/confirm/{confirmcode}", method = RequestMethod.POST)
    public String confirm(@PathVariable String confirmcode) {
        boolean flag = packageService.confirmReceive(confirmcode);
        JSONObject jsonObject = new JSONObject();
        if (flag == false) {
            jsonObject.put("status", "fail");
            jsonObject.put("reason","取货失败");
            return jsonObject.toString();
        } else {
            jsonObject.put("status", "ok");
            return jsonObject.toString();
        }
    }
}
