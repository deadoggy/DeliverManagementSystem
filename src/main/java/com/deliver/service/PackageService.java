package com.deliver.service;

import com.deliver.dao.*;
import com.deliver.model.*;
import com.deliver.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ProxyChargeRecordRepository proxyChargeRecordRepository;

    @Transactional
    public Package getPackage(String id) {
        return packageRepository.findByMPackageId(id);
    }

    @Transactional
    public List<Package> getPackageTakenStartingWith(String id) {
        List<Package> packageList=packageRepository.findTakenMPackageIdBegin(id);
        return packageList;
    }

    @Transactional
    public List<Package> getPackageNoTakenStartingWith(String id) {
        List<Package> packageList=packageRepository.findNoTakenMPackageIdBegin(id);
        return packageList;
    }


    //将快件加入到一个智能柜or货架的位置上
    @Transactional
    public boolean addPackage(String id, DeliverCompany deliverCompany, String receiverName, String receiverTele, double fee, StoragePosition storagePosition) {
        try {
            Package aPackage = new Package();
            aPackage.setmCupOrShelf(storagePosition.ismCuporShelf());
            aPackage.setmPackageId(id);
            aPackage.setmProxyChargeFee(fee);
            aPackage.setmReceiveTime(new Timestamp(System.currentTimeMillis()));
            aPackage.setmReceiverName(receiverName);
            aPackage.setmReceiverTele(receiverTele);
            aPackage.setmTaken(false);
            aPackage.setmTakenTime(null);
            aPackage.setmCompany(deliverCompany);
            aPackage.setmPosition(storagePosition);
            packageRepository.saveAndFlush(aPackage);

            SelfBcryptEncoder selfBcryptEncoder = new SelfBcryptEncoder();
            String identifyCode = selfBcryptEncoder.encipher(aPackage.getmPackageId());
            storagePosition.setmIdentifyCode(identifyCode.substring(identifyCode.length() - 4, identifyCode.length()));
            storagePosition.setmEmpty(POSITION_FULL);
            storagePositionreRepository.saveAndFlush(storagePosition);
            if (storagePosition.ismCuporShelf() == POSITION_IN_SHELF) {
                Shelf shelf = storagePosition.getmShelf();
                shelf.setmEmptySum(shelf.getmEmptySum() - 1);
                shelfRepository.saveAndFlush(shelf);
            } else {
                SmartCupboard smartCupboard = storagePosition.getmCup();
                smartCupboard.setmEmptySum(smartCupboard.getmEmptySum() - 1);
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
    public boolean confirmReceive(String identifyCode, double fee) {
        try {
            StoragePosition storagePosition = storagePositionreRepository.findByMIdentifyCode(identifyCode);
            if (storagePosition == null) {
                return false;
            } else {
                Package aPackage = packageRepository.getByStorageMId(storagePosition.getmId());
                if (Math.abs(aPackage.getmProxyChargeFee() - fee) > 0.00001) {
                    return false;
                }
                //代收费记录
                ProxyChargeRecord proxyChargeRecord = new ProxyChargeRecord(aPackage, fee, PROXY_CHARGE_RECE, new Date());
                proxyChargeRecordRepository.saveAndFlush(proxyChargeRecord);

                aPackage.setmTaken(true);
                aPackage.setmTakenTime(new Timestamp(System.currentTimeMillis()));
                packageRepository.saveAndFlush(aPackage);

                storagePosition.setmEmpty(POSTION_EMPTY);
                storagePositionreRepository.saveAndFlush(storagePosition);
                if (storagePosition.ismCuporShelf() == POSITION_IN_SHELF) {
                    Shelf shelf = storagePosition.getmShelf();
                    shelf.setmEmptySum(shelf.getmEmptySum() + 1);
                    shelfRepository.saveAndFlush(shelf);
                } else {
                    SmartCupboard smartCupboard = storagePosition.getmCup();
                    smartCupboard.setmEmptySum(smartCupboard.getmEmptySum() + 1);
                    smartCupboardRepository.saveAndFlush(smartCupboard);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //根据取件码获得费用
    public double getFee(String identifyCode) {
        try {
            StoragePosition storagePosition = storagePositionreRepository.findByMIdentifyCode(identifyCode);
            if (storagePosition == null) {
                return -1;
            } else {
                Package aPackage = packageRepository.getByStorageMId(storagePosition.getmId());
                return aPackage.getmProxyChargeFee();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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
    public List<Package> getPackageByTele(String tele) {
        return packageRepository.findByMReceiverTele(tele);
    }

    @Transactional
    public List<Package> getPackageNoTakenByTele(String tele) {
        List<Package> packageList = packageRepository.findByMPackageTeleBegin(tele);
        Iterator<Package> it = packageList.iterator();
        while (it.hasNext()) {
            Package aPackage = it.next();
            if (aPackage.ismTaken() == true) {
                it.remove();
            }
        }
        return packageList;
    }

    @Transactional
    public List<Package> getOvertime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -2);
        Timestamp t = new Timestamp(cal.getTimeInMillis());
        return packageRepository.getOvertime(t);
    }


}
