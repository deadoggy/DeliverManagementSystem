package com.deliver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.model.DeliverCompany;
import com.deliver.service.DeliverCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static javax.swing.text.StyleConstants.ModelAttribute;

/**
 * Created by 91574 on 2017/5/28.
 */
@RestController
public class DeliverCompanyController {
    @Autowired
    private DeliverCompanyService deliverCompanyService;


    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public String getCompany(@PathVariable String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            DeliverCompany deliverCompany = deliverCompanyService.findDeliverCompanyById(id);
            if (deliverCompany == null) {
                return "{\"status\": \"fail\", \"reason\" : \"找不到对应id的快递公司\"}";
            } else {
                jsonObject.put("status", "ok");
                jsonObject.put("mCompanyId", deliverCompany.getmCompanyId());
                jsonObject.put("mName",deliverCompany.getmName());
                jsonObject.put("packageNum",deliverCompany.getmPackage().size());
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }

    }

    @RequestMapping(value = "/company/all", method = RequestMethod.GET)
    public String getAllCompany() {
        JSONObject jsonObject = new JSONObject();
        try {
            List<DeliverCompany> deliverCompanyList = deliverCompanyService.findAllDeliverCompany();
            jsonObject.put("status", "ok");
            JSONArray jsonArray = new JSONArray();
            for (DeliverCompany deliverCompany : deliverCompanyList) {
                JSONObject deliverJSON=new JSONObject();
                deliverJSON.put("mCompanyId", deliverCompany.getmCompanyId());
                deliverJSON.put("mName",deliverCompany.getmName());
                deliverJSON.put("packageNum",deliverCompany.getmPackage().size());
                jsonArray.add(deliverJSON);
            }
            jsonObject.put("companys", jsonArray);
            return JSONObject.toJSONString(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }

    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public String addCompany(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (deliverCompanyService.findDeliverCompanyById(httpServletRequest.getParameter("id")) != null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "快递公司id重复");
                return jsonObject.toString();
            }
            boolean flag = deliverCompanyService.addDeliverCompany(httpServletRequest.getParameter("id"),
                    httpServletRequest.getParameter("name"));
            if (flag == false) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "快递公司信息不符合要求");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                return jsonObject.toString();
            }
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }
}
