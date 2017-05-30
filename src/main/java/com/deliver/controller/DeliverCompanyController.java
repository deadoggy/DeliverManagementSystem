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

    //存在一个问题当company的packageList为空时
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public String getCompany(@PathVariable String id){
        JSONObject jsonObject=new JSONObject();
        DeliverCompany deliverCompany=deliverCompanyService.findDeliverCompanyById(id);
        if(deliverCompany==null){
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "找不到对应id的快递公司");
            return jsonObject.toString();
        } else {
            jsonObject.put("status", "ok");
            jsonObject.put("company", deliverCompany);
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/company/all", method = RequestMethod.GET)
    public String getAllCompany() {
        List<DeliverCompany> deliverCompanyList = deliverCompanyService.findAllDeliverCompany();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        JSONArray jsonArray = new JSONArray();
        for (DeliverCompany deliverCompany : deliverCompanyList) {
            jsonArray.add(deliverCompany);
        }
        jsonObject.put("companys", jsonArray);
        return JSONObject.toJSONString(jsonObject);
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public String addCompany(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
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
    }
}
