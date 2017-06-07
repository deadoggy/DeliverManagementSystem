package com.deliver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.*;
import com.deliver.model.*;
import com.deliver.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.deliver.constant.Constant.*;

/**
 * Created by 91574 on 2017/5/15.
 */
@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private DeliverCompanyRepository deliverCompanyRepository;

    @Autowired
    private StoragePositionreRepository storagePositionreRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private SmartCupboardRepository smartCupboardRepository;

    @Transactional
    public Package getPackage(String id){
        return packageRepository.findByMPackageId(id);
    }

    @Transactional
    public List<Package> getPackageTakenStartingWith(String id) {
        List<Package> packageList=packageRepository.findByMPackageIdBegin(id);
        Iterator<Package> it=packageList.iterator();
        while (it.hasNext()){
            Package aPackage=it.next();
            if(aPackage.ismTaken()==false){
                it.remove();
            }
        }
        return packageList;
    }

    @Transactional
    public List<Package> getPackageNoTakenStartingWith(String id) {
        List<Package> packageList=packageRepository.findByMPackageIdBegin(id);
        Iterator<Package> it=packageList.iterator();
        while (it.hasNext()){
            Package aPackage=it.next();
            if(aPackage.ismTaken()==true){
                it.remove();
            }
        }
        return packageList;
    }


    //将快件加入到一个智能柜or货架的位置上
    //目前没考虑代收邮费的问题
    //未测试
    @Transactional
    public boolean addPackage(String id, DeliverCompany deliverCompany, String receiverName, String receiverTele, boolean cupOrShelf, StoragePosition storagePosition) {
        try {
            Package aPackage = new Package();
            aPackage.setmCupOrShelf(cupOrShelf);
            aPackage.setmPackageId(id);
            aPackage.setmProxyChargeFee(0);
            aPackage.setmReceiveTime(new Timestamp(System.currentTimeMillis()));
            aPackage.setmReceiverName(receiverName);
            aPackage.setmReceiverTele(receiverTele);
            aPackage.setmTaken(false);
            aPackage.setmTakenTime(null);
            aPackage.setmCompany(deliverCompany);
            aPackage.setmPosition(storagePosition);
            packageRepository.saveAndFlush(aPackage);

            SelfBcryptEncoder selfBcryptEncoder=new SelfBcryptEncoder();
            String identifyCode=selfBcryptEncoder.encipher(aPackage.getmPackageId());
            storagePosition.setmIdentifyCode(identifyCode.substring(identifyCode.length()-4,identifyCode.length()));
            storagePosition.setmEmpty(POSITION_FULL);
            storagePositionreRepository.saveAndFlush(storagePosition);
            if(storagePosition.ismCuporShelf()==POSITION_IN_SHELF){
                Shelf shelf=storagePosition.getmShelf();
                shelf.setmEmptySum(shelf.getmEmptySum()-1);
                shelfRepository.saveAndFlush(shelf);
            }else{
                SmartCupboard smartCupboard=storagePosition.getmCup();
                smartCupboard.setmEmptySum(smartCupboard.getmEmptySum()-1);
                smartCupboardRepository.saveAndFlush(smartCupboard);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //确认收货
    @Transactional
    public boolean confirmReceive(String identifyCode) {
        try {
            StoragePosition storagePosition = storagePositionreRepository.findByMIdentifyCode(identifyCode);
            if (storagePosition == null) {
                return false;
            } else {
                storagePosition.setmEmpty(POSTION_EMPTY);
                storagePositionreRepository.saveAndFlush(storagePosition);
                if(storagePosition.ismCuporShelf()==POSITION_IN_SHELF){
                    Shelf shelf=storagePosition.getmShelf();
                    shelf.setmEmptySum(shelf.getmEmptySum()+1);
                    shelfRepository.saveAndFlush(shelf);
                }else{
                    SmartCupboard smartCupboard=storagePosition.getmCup();
                    smartCupboard.setmEmptySum(smartCupboard.getmEmptySum()+1);
                    smartCupboardRepository.saveAndFlush(smartCupboard);
                }
                List<Package> packageList = storagePosition.getmPackage();
                Package aPackage = packageList.get(packageList.size() - 1);
                aPackage.setmTaken(true);
                aPackage.setmTakenTime(new Timestamp(System.currentTimeMillis()));
                packageRepository.saveAndFlush(aPackage);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //获取包裹所在的网点
    public Point getPackagePoint(Package aPackage) {
        try {
            StoragePosition storagePosition = aPackage.getmPosition();
            if (storagePosition.ismCuporShelf() == POSITION_IN_CUPBOARD) {
                SmartCupboard smartCupboard = storagePosition.getmCup();
                return smartCupboard.getmPoint();
            } else {
                Shelf shelf = storagePosition.getmShelf();
                return shelf.getmPoint();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取所有包裹
    public List<Package> getAllPackage() {
        return packageRepository.findAll();
    }

    public List<Package> getAllNoTakenPackage() {
        return packageRepository.getAllNoTaken();
    }

    public List<Package> getAllTakenPackage() {
        return packageRepository.getAllTaken();
    }

    //通过手机获得所有包裹
    public List<Package> getPackageByTele(String tele){
        return packageRepository.findByMReceiverTele(tele);
    }

    @Transactional
    public List<Package> getPackageNoTakenByTele(String tele) {
        List<Package> packageList=packageRepository.findByMPackageTeleBegin(tele);
        Iterator<Package> it=packageList.iterator();
        while (it.hasNext()){
            Package aPackage=it.next();
            if(aPackage.ismTaken()==true){
                it.remove();
            }
        }
        return packageList;
    }

    @Transactional
    public List<Package> getOvertime(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,-2);
        Timestamp t=new Timestamp(cal.getTimeInMillis());
        return packageRepository.getOvertime(t);
    }

    @Transactional
    public boolean forceOpen(String id){
        try{
            Package aPackage=packageRepository.findByMPackageId(id);
            if(aPackage.ismTaken()==true){
                return false;
            }
            aPackage.setmTaken(true);
            aPackage.setmTakenTime(new Timestamp(System.currentTimeMillis()));
            packageRepository.saveAndFlush(aPackage);
            StoragePosition storagePosition=aPackage.getmPosition();
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
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
