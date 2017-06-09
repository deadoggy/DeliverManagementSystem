package com.deliver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.PointRepository;
import com.deliver.dao.ShelfRepository;
import com.deliver.dao.StoragePositionreRepository;
import com.deliver.model.Point;
import com.deliver.model.Shelf;
import com.deliver.model.StoragePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.deliver.constant.Constant.POSITION_IN_SHELF;
import static com.deliver.constant.Constant.POSTION_EMPTY;

/**
 * Created by 91574 on 2017/5/9.
 */
@Service
public class ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private StoragePositionreRepository storagePositionreRepository;

    @Autowired
    private PointRepository pointRepository;

    @Transactional
    public List<Shelf> getAllShelf() {
        return shelfRepository.findAll();
    }


    @Transactional
    public Shelf getShelfById(String id) {
        return shelfRepository.findByMShelfId(id);
    }

    public List<Shelf> getShelfs(String id){
        return shelfRepository.getShelfBegin(id);
    }


    @Transactional
    public boolean addShelf(String id, int columnSum, int layerSum,Point point) {
        try {
            Shelf shelf = new Shelf();
            shelf.setmShelfId(id);
            shelf.setmColumnSum(columnSum);
            shelf.setmLayerSum(layerSum);
            shelf.setmPositionSum(columnSum * layerSum);
            shelf.setmEmptySum(columnSum * layerSum);
            shelf.setmPosition(null);
            shelf.setmPoint(point);
            shelfRepository.saveAndFlush(shelf);
            for (int i = 1; i <= shelf.getmPositionSum(); i++) {
                StoragePosition storagePosition = new StoragePosition();
                storagePosition.setmEmpty(POSTION_EMPTY);
                storagePosition.setmCuporShelf(POSITION_IN_SHELF);
                if(i%columnSum==0){
                    storagePosition.setmLayer(i / shelf.getmColumnSum());
                    storagePosition.setmColumn(columnSum);
                }else{
                    storagePosition.setmLayer(i / shelf.getmColumnSum()+1);
                    storagePosition.setmColumn(i % columnSum);
                }
                storagePosition.setmStorageId(shelf.getmShelfId()+"-"+
                        storagePosition.getmLayer()+"-"+storagePosition.getmColumn());
                storagePosition.setmShelf(shelf);
                storagePosition.setmIdentifyCode(null);
                storagePosition.setmPackage(null);
                storagePosition.setmCup(null);
                storagePositionreRepository.saveAndFlush(storagePosition);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    @Transactional
    public boolean deleteShelf(String id) {
        Shelf shelf = shelfRepository.findByMShelfId(id);
        if (shelf == null) {
            return false;
        } else {
            shelfRepository.delete(shelf.getmId());
            return true;
        }
    }


    //返回一个货架的空位
    @Transactional
    public StoragePosition getPosition() {
        List<Shelf> shelfList = shelfRepository.findAll();
        for (Shelf shelf : shelfList) {
            if (shelf.getmEmptySum() == 0) {
                continue;
            } else {
                List<StoragePosition> storagePositions = shelf.getmPosition();
                for (StoragePosition position : storagePositions) {
                    if (position.ismEmpty()) {
                        return position;
                    }
                }
            }
        }
        return null;
    }


}
