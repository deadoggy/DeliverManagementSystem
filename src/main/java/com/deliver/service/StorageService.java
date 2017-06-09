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



    public StoragePosition getByStorageMId(int id) {
        return storagePositionreRepository.findByMId(id);
    }

    public StoragePosition getByStorageId(String storageId){
        return storagePositionreRepository.findByMStorageId(storageId);
    }

    public String getStorageIdByLayerAndColumnInShelf(int id,int layer,int column){
        return storagePositionreRepository.findMShelfAndMLayerAndMColumn(id,layer,column).get(0);
    }
    public String getStorageIdByLayerAndColumnInCup(int id,int layer,int column){
        return storagePositionreRepository.findMCupAndMLayerAndMColumn(id,layer,column).get(0);
    }

    @Transactional
    public boolean transform(String fStorageId,String tStorageId){
        try {
            StoragePosition fStoragePosition = storagePositionreRepository.findByMStorageId(fStorageId);
            StoragePosition tStoragePosition = storagePositionreRepository.findByMStorageId(tStorageId);
            if (fStoragePosition == null||tStoragePosition==null
                    ||fStoragePosition.ismEmpty()==POSTION_EMPTY||tStoragePosition.ismEmpty()==POSITION_FULL) {
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
                Package aPackage = packageRepository.getByStoragePosition(fStoragePosition.getmId());
                aPackage.setmPosition(tStoragePosition);
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

    @Transactional
    public boolean forceOpen(String storageId){
        try{
            StoragePosition storagePosition=storagePositionreRepository.findByMStorageId(storageId);
            if(storagePosition==null||storagePosition.ismEmpty()==POSTION_EMPTY){
                return false;
            }

            Package aPackage=packageRepository.getByStorageMId (storagePosition.getmId());
            if(aPackage.ismTaken()==true){
                return false;
            }


            storagePosition.setmEmpty(POSTION_EMPTY);
            storagePositionreRepository.saveAndFlush(storagePosition);
            if(storagePosition.ismCuporShelf()==POSITION_IN_SHELF){
                Shelf shelf=storagePosition.getmShelf();
                shelf.setmEmptySum(shelf.getmEmptySum()+1);
                shelfRepository.saveAndFlush(shelf);
            }else {
                SmartCupboard smartCupboard=storagePosition.getmCup();
                smartCupboard.setmEmptySum(smartCupboard.getmEmptySum()+1);
                smartCupboardRepository.saveAndFlush(smartCupboard);
            }

            aPackage.setmTaken(true);
            aPackage.setmTakenTime(new Timestamp(System.currentTimeMillis()));
            packageRepository.saveAndFlush(aPackage);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
