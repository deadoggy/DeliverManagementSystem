package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.PackageRepository;
import com.deliver.dao.ShelfRepository;
import com.deliver.dao.SmartCupboardRepository;
import com.deliver.dao.StoragePositionreRepository;
import com.deliver.model.Package;
import com.deliver.model.Shelf;
import com.deliver.model.SmartCupboard;
import com.deliver.model.StoragePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static com.deliver.constant.Constant.POSITION_FULL;
import static com.deliver.constant.Constant.POSITION_IN_SHELF;
import static com.deliver.constant.Constant.POSTION_EMPTY;

/**
 * Created by 91574 on 2017/5/15.
 */
@Service
public class StorageService {
    @Autowired
    private StoragePositionreRepository storagePositionreRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private SmartCupboardRepository smartCupboardRepository;

    @Autowired
    private PackageRepository packageRepository;



    public StoragePosition getStorageById(int id) {
        return storagePositionreRepository.findByMId(id);
    }

    public int getStorageIdByLayerAndColumnInShelf(int id,int layer,int column){
        return storagePositionreRepository.findMShelfAndMLayerAndMColumn(id,layer,column).get(0);
    }
    public int getStorageIdByLayerAndColumnInCup(int id,int layer,int column){
        return storagePositionreRepository.findByMCupAndMLayerAndMColumn(id,layer,column);
    }

    @Transactional
    public boolean transform(int fStorageId,int tStorageId){
        try {
            StoragePosition fStoragePosition = storagePositionreRepository.findByMId(fStorageId);
            StoragePosition tStoragePosition = storagePositionreRepository.findByMId(tStorageId);
            if (fStoragePosition == null||tStoragePosition==null) {
                return false;
            } else {
                fStoragePosition.setmEmpty(POSTION_EMPTY);
                fStoragePosition.setmIdentifyCode(null);
                storagePositionreRepository.saveAndFlush(fStoragePosition);
                if(fStoragePosition.ismCuporShelf()==POSITION_IN_SHELF){
                    Shelf shelf=fStoragePosition.getmShelf();
                    shelf.setmEmptySum(shelf.getmEmptySum()+1);
                    shelfRepository.saveAndFlush(shelf);
                }else {
                    SmartCupboard smartCupboard = fStoragePosition.getmCup();
                    smartCupboard.setmEmptySum(smartCupboard.getmEmptySum() + 1);
                    smartCupboardRepository.saveAndFlush(smartCupboard);
                }
                Package aPackage = packageRepository.getByStoragePosition(fStorageId);
                aPackage.setmPosition(storagePositionreRepository.findByMId(tStorageId));
                packageRepository.saveAndFlush(aPackage);

                //包裹转入到的位置
                tStoragePosition.setmEmpty(POSITION_FULL);
                SelfBcryptEncoder selfBcryptEncoder=new SelfBcryptEncoder();
                String identifyCode=selfBcryptEncoder.encipher(aPackage.getmPackageId());
                tStoragePosition.setmIdentifyCode(identifyCode.substring(identifyCode.length()-4,identifyCode.length()));
                storagePositionreRepository.saveAndFlush(fStoragePosition);
                if(fStoragePosition.ismCuporShelf()==POSITION_IN_SHELF){
                    Shelf shelf=fStoragePosition.getmShelf();
                    shelf.setmEmptySum(shelf.getmEmptySum()-1);
                    shelfRepository.saveAndFlush(shelf);
                }else{
                    SmartCupboard smartCupboard=fStoragePosition.getmCup();
                    smartCupboard.setmEmptySum(smartCupboard.getmEmptySum()-1);
                    smartCupboardRepository.saveAndFlush(smartCupboard);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
