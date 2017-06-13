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
import com.deliver.service.ShortMesgService;
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
import java.util.DoubleSummaryStatistics;
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

    //根据id查找未取走的包裹
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
                JSONArray jsonArray = new JSONArray();
                for (Package aPackage : packageList) {
                    JSONObject packageObject = new JSONObject();
                    packageObject.put("packageId", aPackage.getmPackageId());
                    packageObject.put("fee", aPackage.getmProxyChargeFee());
                    packageObject.put("rName", aPackage.getmReceiverName());
                    packageObject.put("rTele", aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime", sdf.format(aPackage.getmReceiveTime()));
                    StoragePosition storagePosition = aPackage.getmPosition();
                    if (storagePosition.ismCuporShelf() == POSITION_IN_SHELF) {
                        packageObject.put("position", "shelf:" + storagePosition.getmStorageId());
                    } else {
                        packageObject.put("position", "smartCupboard:" + storagePosition.getmStorageId());
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

    //根据电话查找未取走的包裹
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
                JSONArray jsonArray = new JSONArray();
                for (Package aPackage : packageList) {
                    JSONObject packageObject = new JSONObject();
                    packageObject.put("packageId", aPackage.getmPackageId());
                    packageObject.put("fee", aPackage.getmProxyChargeFee());
                    packageObject.put("rName", aPackage.getmReceiverName());
                    packageObject.put("rTele", aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime", sdf.format(aPackage.getmReceiveTime()));
                    StoragePosition storagePosition = aPackage.getmPosition();
                    if (storagePosition.ismCuporShelf() == POSITION_IN_SHELF) {
                        packageObject.put("position", "shelf:" + storagePosition.getmStorageId());
                    } else {
                        packageObject.put("position", "smartCupboard:" + storagePosition.getmStorageId());
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

    //所有未取走包裹
    @RequestMapping(value = "/package/allnotaken", method = RequestMethod.GET)
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
                JSONArray jsonArray = new JSONArray();
                for (Package aPackage : packageList) {
                    JSONObject packageObject = new JSONObject();
                    packageObject.put("packageId", aPackage.getmPackageId());
                    packageObject.put("fee", aPackage.getmProxyChargeFee());
                    packageObject.put("rName", aPackage.getmReceiverName());
                    packageObject.put("rTele", aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime", sdf.format(aPackage.getmReceiveTime()));
                    StoragePosition storagePosition = aPackage.getmPosition();
                    if (storagePosition.ismCuporShelf() == POSITION_IN_SHELF) {
                        packageObject.put("position", "shelf:" + storagePosition.getmStorageId());
                    } else {
                        packageObject.put("position", "smartCupboard:" + storagePosition.getmStorageId());
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

    //所有取走包裹
    @RequestMapping(value = "/package/alltaken", method = RequestMethod.GET)
    public String getAllPackageTaken() {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Package> packageList = packageService.getAllTakenPackage();
            if (packageList == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "获取对象失败");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                JSONArray jsonArray = new JSONArray();
                for (Package aPackage : packageList) {
                    JSONObject packageObject = new JSONObject();
                    packageObject.put("packageId", aPackage.getmPackageId());
                    packageObject.put("fee", aPackage.getmProxyChargeFee());
                    packageObject.put("rName", aPackage.getmReceiverName());
                    packageObject.put("rTele", aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime", sdf.format(aPackage.getmReceiveTime()));
                    packageObject.put("takenTime", sdf.format(aPackage.getmTakenTime()));
                    StoragePosition storagePosition = aPackage.getmPosition();
                    if (storagePosition.ismCuporShelf() == POSITION_IN_SHELF) {
                        packageObject.put("position", "shelf:" + storagePosition.getmStorageId());
                    } else {
                        packageObject.put("position", "smartCupboard:" + storagePosition.getmStorageId());
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

    //根据id查找取走的包裹
    @RequestMapping(value = "/package/taken/{id}", method = RequestMethod.GET)
    public String getPackageTaken(@PathVariable String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Package> packageList = packageService.getPackageTakenStartingWith(id);
            if (packageList == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "获取对象失败");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                JSONArray jsonArray = new JSONArray();
                for (Package aPackage : packageList) {
                    JSONObject packageObject = new JSONObject();
                    packageObject.put("packageId", aPackage.getmPackageId());
                    packageObject.put("fee", aPackage.getmProxyChargeFee());
                    packageObject.put("rName", aPackage.getmReceiverName());
                    packageObject.put("rTele", aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime", sdf.format(aPackage.getmReceiveTime()));
                    packageObject.put("takenTime", sdf.format(aPackage.getmTakenTime()));
                    StoragePosition storagePosition = aPackage.getmPosition();
                    if (storagePosition.ismCuporShelf() == POSITION_IN_SHELF) {
                        packageObject.put("position", "shelf:" + storagePosition.getmStorageId());
                    } else {
                        packageObject.put("position", "smartCupboard:" + storagePosition.getmStorageId());
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


    //包裹入库
    @RequestMapping(value = "/package", method = RequestMethod.POST)
    public String add(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        try {
            String id = httpServletRequest.getParameter("id");
            if (packageService.getPackage(id) != null) {
                return "{\"status\": \"fail\", \"reason\" : \"包裹id重复\"}";
            }
            StoragePosition storagePosition = storageService.getByStorageId(httpServletRequest.getParameter("storageId"));
            if (storagePosition == null) {
                return "{\"status\": \"fail\", \"reason\" : \"没有此位置\"}";
            } else if (storagePosition.ismEmpty() == POSITION_FULL) {
                return "{\"status\": \"fail\", \"reason\" : \"此位置已经有货物\"}";
            }
            DeliverCompany deliverCompany = deliverCompanyService.getDeliverCompany(httpServletRequest.getParameter("companyName"));
            if (deliverCompany == null) {
                return "{\"status\": \"fail\", \"reason\" : \"没有这家快递公司\"}";
            }

            double fee=Double.valueOf(httpServletRequest.getParameter("fee"));
            if(fee<0){
                return "{\"status\": \"fail\", \"reason\" : \"快递费用不能为负\"}";
           }

            boolean flag = packageService.addPackage(id, deliverCompany,
                    httpServletRequest.getParameter("receiverName"), httpServletRequest.getParameter("receiverTele"),
                    fee,storagePosition);

            if (flag == false) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "包裹信息不符合要求");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                ShortMesgService shortMesgService = ShortMesgService.getInstance();
                Package aPackage=packageService.getPackage(id);
                shortMesgService.SendMessage(aPackage,false);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    //根据取件码获得包裹的费用
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String getByConfirmCode(HttpServletRequest httpServletRequest) {
        try {
            double fee = packageService.getFee(httpServletRequest.getParameter("confirmcode"));
            if (fee < 0) {
                return "{\"status\": \"fail\", \"reason\" : \"没有对应的包裹\"}";
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", "ok");
                jsonObject.put("fee", fee);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\": \"fail\", \"reason\" : \"转移失败\"}";
        }
    }

    @RequestMapping(value = "/confirm/fee", method = RequestMethod.POST)
    public String confirm(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        try {
            String confirmcode=httpServletRequest.getParameter("confirmcode");
            double fee= Double.valueOf(httpServletRequest.getParameter("fee"));
            boolean flag = packageService.confirmReceive(confirmcode, fee);
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

    //获取超过48h的包裹
    @RequestMapping(value = "/package/overtime", method = RequestMethod.GET)
    public String getPackageOvertime() {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Package> packageList = packageService.getOvertime();
            if (packageList == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "获取对象失败");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                JSONArray jsonArray = new JSONArray();
                for (Package aPackage : packageList) {
                    JSONObject packageObject = new JSONObject();
                    packageObject.put("packageId", aPackage.getmPackageId());
                    packageObject.put("fee", aPackage.getmProxyChargeFee());
                    packageObject.put("rName", aPackage.getmReceiverName());
                    packageObject.put("rTele", aPackage.getmReceiverTele());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    packageObject.put("rTime", sdf.format(aPackage.getmReceiveTime()));
                    StoragePosition storagePosition = aPackage.getmPosition();
                    if (storagePosition.ismCuporShelf() == POSITION_IN_SHELF) {
                        packageObject.put("position", "shelf:" + storagePosition.getmStorageId());
                    } else {
                        packageObject.put("position", "smartCupboard:" + storagePosition.getmStorageId());
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

    //强制开柜
    @RequestMapping(value = "/package/force/{storageId}", method = RequestMethod.GET)
    public String forceOpen(@PathVariable String storageId) {
        try {
            boolean flag = storageService.forceOpen(storageId);
            if (flag == true) {
                return "{\"status\": \"ok\"}";
            } else {
                return "{\"status\": \"fail\", \"reason\" : \"强制开柜失败\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\": \"fail\", \"reason\" : \"请求失败\"}";
        }
    }
}
