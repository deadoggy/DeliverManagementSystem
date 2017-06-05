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
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.deliver.constant.Constant.POSITION_FULL;
import static com.deliver.constant.Constant.POSITION_IN_SHELF;

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
        JSONObject jsonObject = new JSONObject();
        try {
            List<Package> packageList = packageService.getPackageNoTakenStartingWith(id);
            if (packageList == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "获取对象失败");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                JSONArray jsonArray=new JSONArray();
                for (Package aPackage:packageList) {
                    JSONObject packageObject=new JSONObject();
                    packageObject.put("packageId",aPackage.getmPackageId());
                    packageObject.put("fee",aPackage.getmProxyChargeFee());
                    packageObject.put("rName",aPackage.getmReceiverName());
                    packageObject.put("rTele",aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime",sdf.format(aPackage.getmReceiveTime()));
                    StoragePosition storagePosition=aPackage.getmPosition();
                    if(storagePosition.ismCuporShelf()==POSITION_IN_SHELF){
                        packageObject.put("position","shelf "+storagePosition.getmShelf().getmShelfId()+" "+
                                storagePosition.getmLayer()+" "+storagePosition.getmColumn());
                    }else{
                        packageObject.put("position","smartCupboard "+storagePosition.getmCup().getmCupboardId()+" "+
                                storagePosition.getmLayer()+" "+storagePosition.getmColumn());
                    }
                    jsonArray.add(packageObject);
                }
                jsonObject.put("packageList", jsonArray);
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/package/tele/{tele}", method = RequestMethod.GET)
    public String getAPackageByTele(@PathVariable String tele) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Package> packageList = packageService.getPackageNoTakenByTele(tele);
            if (packageList == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "获取对象失败");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                JSONArray jsonArray=new JSONArray();
                for (Package aPackage:packageList) {
                    JSONObject packageObject=new JSONObject();
                    packageObject.put("packageId",aPackage.getmPackageId());
                    packageObject.put("fee",aPackage.getmProxyChargeFee());
                    packageObject.put("rName",aPackage.getmReceiverName());
                    packageObject.put("rTele",aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime",sdf.format(aPackage.getmReceiveTime()));
                    StoragePosition storagePosition=aPackage.getmPosition();
                    if(storagePosition.ismCuporShelf()==POSITION_IN_SHELF){
                        packageObject.put("position","shelf "+storagePosition.getmShelf().getmShelfId()+" "+
                                storagePosition.getmLayer()+" "+storagePosition.getmColumn());
                    }else{
                        packageObject.put("position","smartCupboard "+storagePosition.getmCup().getmCupboardId()+" "+
                                storagePosition.getmLayer()+" "+storagePosition.getmColumn());
                    }
                    jsonArray.add(packageObject);
                }
                jsonObject.put("packageList", jsonArray);
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }

    }


    @RequestMapping(value = "/package/all", method = RequestMethod.GET)
    public String getAllPackage() {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Package> packageList = packageService.getAllNoTakenPackage();
            if (packageList == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "获取对象失败");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                JSONArray jsonArray=new JSONArray();
                for (Package aPackage:packageList) {
                    JSONObject packageObject=new JSONObject();
                    packageObject.put("packageId",aPackage.getmPackageId());
                    packageObject.put("fee",aPackage.getmProxyChargeFee());
                    packageObject.put("rName",aPackage.getmReceiverName());
                    packageObject.put("rTele",aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime",sdf.format(aPackage.getmReceiveTime()));
                    StoragePosition storagePosition=aPackage.getmPosition();
                    if(storagePosition.ismCuporShelf()==POSITION_IN_SHELF){
                        packageObject.put("position","shelf "+storagePosition.getmShelf().getmShelfId()+" "+
                                storagePosition.getmLayer()+" "+storagePosition.getmColumn());
                    }else{
                        packageObject.put("position","smartCupboard "+storagePosition.getmCup().getmCupboardId()+" "+
                                storagePosition.getmLayer()+" "+storagePosition.getmColumn());
                    }
                    jsonArray.add(packageObject);
                }
                jsonObject.put("packageList", jsonArray);
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }

    }

    @RequestMapping(value = "/package", method = RequestMethod.POST)
    public String add(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        try {
            String id = httpServletRequest.getParameter("id");
            if (packageService.getPackage(id) != null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "包裹id重复");
                return jsonObject.toString();
            }
            StoragePosition storagePosition = storageService.getStorageById(Integer.parseInt(httpServletRequest.getParameter("storageId")));
            if (storagePosition == null || storagePosition.ismEmpty() == POSITION_FULL) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "存储位置已经有货物");
                return jsonObject.toString();
            }
            DeliverCompany deliverCompany = deliverCompanyService.getDeliverCompany(httpServletRequest.getParameter("companyName"));
            if (deliverCompany == null) {
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
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/confirm/{confirmcode}", method = RequestMethod.POST)
    public String confirm(@PathVariable String confirmcode) {
        JSONObject jsonObject = new JSONObject();
        try {
            boolean flag = packageService.confirmReceive(confirmcode);
            if (flag == false) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "取货失败");
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

    @RequestMapping(value = "/package/taken/{id}", method = RequestMethod.GET)
    public String getPackageTaken(@PathVariable String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Package> packageList = packageService.getPackageNoTakenStartingWith(id);
            jsonObject.put("status", "ok");
            JSONArray jsonArray = new JSONArray();
            for (Package aPackage : packageList) {
                jsonArray.add(aPackage);
            }
            jsonObject.put("packages", jsonArray);
            return JSONObject.toJSONString(jsonObject);
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }

    }
}
