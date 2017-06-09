package com.deliver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.SmartCupboardRepository;
import com.deliver.dao.StoragePositionreRepository;
import com.deliver.model.Point;
import com.deliver.model.SmartCupboard;
import com.deliver.model.StoragePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.deliver.constant.Constant.POSITION_IN_CUPBOARD;
import static com.deliver.constant.Constant.POSITION_IN_SHELF;
import static com.deliver.constant.Constant.POSTION_EMPTY;

/**
 * Created by 91574 on 2017/5/9.
 */
@Service
public class SmartCupboardService {
    @Autowired
    private SmartCupboardRepository smartCupboardRepository;

    @Autowired
    private StoragePositionreRepository storagePositionreRepository;

    //获取所有智能柜
    public List<SmartCupboard> getAllSmartCupboard() {
        return smartCupboardRepository.findAll();
    }

    //根据id找到对应智能柜
    public SmartCupboard getSmartCupboardById(String id) {
        return smartCupboardRepository.findByMCupboardId(id);
    }

    public List<SmartCupboard> getCupBegin(String id){
        return smartCupboardRepository.getSmartCupboardBegin(id);
    }

    @Transactional
    public boolean addSmartCupboard(String id, int layerSum, int columnSum,Point point) {
        try {
            SmartCupboard smartCupboard = new SmartCupboard();
            smartCupboard.setmCupboardId(id);
            smartCupboard.setmLayerSum(layerSum);
            smartCupboard.setmColumnSum(columnSum);
            smartCupboard.setmPositionSum(smartCupboard.getmColumnSum() * smartCupboard.getmLayerSum());
            smartCupboard.setmEmptySum(smartCupboard.getmPositionSum());
            smartCupboard.setmPosition(null);
            smartCupboard.setmPoint(point);
            smartCupboardRepository.saveAndFlush(smartCupboard);
            List<StoragePosition> storagePositionList = new ArrayList<StoragePosition>(smartCupboard.getmPositionSum());
            for (int i = 1; i <= smartCupboard.getmPositionSum(); i++) {
                StoragePosition storagePosition = new StoragePosition();
                storagePosition.setmEmpty(POSTION_EMPTY);
                storagePosition.setmCuporShelf(POSITION_IN_CUPBOARD);
                if(i%columnSum==0){
                    storagePosition.setmLayer(i / smartCupboard.getmColumnSum());
                    storagePosition.setmColumn(columnSum);
                }else{
                    storagePosition.setmLayer(i / smartCupboard.getmColumnSum()+1);
                    storagePosition.setmColumn(i%columnSum);
                }

                storagePosition.setmStorageId(smartCupboard.getmCupboardId()+"-"+
                        storagePosition.getmLayer()+"-"+storagePosition.getmColumn());
                storagePosition.setmShelf(null);
                storagePosition.setmIdentifyCode(null);
                storagePosition.setmPackage(null);
                storagePosition.setmCup(smartCupboard);
                storagePositionreRepository.saveAndFlush(storagePosition);
            }
            smartCupboard.setmPosition(storagePositionList);
            smartCupboardRepository.saveAndFlush(smartCupboard);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //返回一个智能柜的空位
    @Transactional
    public StoragePosition getPosition() {
        List<SmartCupboard> smartCupboardList = smartCupboardRepository.findAll();
        for (SmartCupboard smartCupboard : smartCupboardList) {
            if (smartCupboard.getmEmptySum() == 0) {
                continue;
            } else {
                List<StoragePosition> storagePositionList = smartCupboard.getmPosition();
                for (StoragePosition storage : storagePositionList) {
                    if (storage.ismEmpty() == POSTION_EMPTY) {
                        return storage;
                    }
                }
            }
        }
        return null;
    }
}
