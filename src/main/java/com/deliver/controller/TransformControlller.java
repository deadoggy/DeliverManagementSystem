package com.deliver.controller;

import com.alibaba.fastjson.JSONObject;
import com.deliver.model.Shelf;
import com.deliver.service.ShelfService;
import com.deliver.service.SmartCupboardService;
import com.deliver.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.deliver.constant.Constant.POSITION_IN_SHELF;

/**
 * Created by 91574 on 2017/6/6.
 */
@RestController
public class TransformControlller {
    @Autowired
    private ShelfService shelfService;

    @Autowired
    private SmartCupboardService smartCupboardService;

    @Autowired
    private StorageService storageService;

    @RequestMapping(value ="/transform",method = RequestMethod.POST)
    public String transform(HttpServletRequest httpServletRequest){
        try{
            Boolean isCup = Boolean.valueOf(httpServletRequest.getParameter("isCup"));
            String id=httpServletRequest.getParameter("id");
            int layer=Integer.valueOf(httpServletRequest.getParameter("layer"));
            int column=Integer.valueOf(httpServletRequest.getParameter("column"));
            String tStorageId=httpServletRequest.getParameter("tStorageId");
            boolean flag=false;
            if(isCup==POSITION_IN_SHELF){
                int shelfId=shelfService.getShelfById(id).getmId();
                String storageId=storageService.getStorageIdByLayerAndColumnInShelf(shelfId,layer,column);
                flag=storageService.transform(storageId,tStorageId);
            }else{
                int cupId=smartCupboardService.getSmartCupboardById(id).getmId();
                String storageId=storageService.getStorageIdByLayerAndColumnInCup(cupId,layer,column);
                flag=storageService.transform(storageId,tStorageId);
            }
            if(flag==true){
                return "{\"status\": \"ok\"}";
            }else{
                return "{\"status\": \"fail\", \"reason\" : \"转移失败\"}";
            }
        }catch (Exception e){
            return "{\"status\": \"fail\", \"reason\" : \"请求失败\"}";
        }
    }

    @RequestMapping(value ="/transform/id",method = RequestMethod.POST)
    public String transformById(HttpServletRequest httpServletRequest){
        try{
            String fStorageId=httpServletRequest.getParameter("fStorageId");
            String tStorageId=httpServletRequest.getParameter("tStorageId");
            boolean flag=storageService.transform(fStorageId,tStorageId);
            if(flag==true){
                return "{\"status\": \"ok\"}";
            }else{
                return "{\"status\": \"fail\", \"reason\" : \"转移失败\"}";
            }
        }catch (Exception e){
            return "{\"status\": \"fail\", \"reason\" : \"请求失败\"}";
        }
    }


}
