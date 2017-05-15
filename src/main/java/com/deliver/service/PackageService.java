package com.deliver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.DeliverCompanyRepository;
import com.deliver.dao.PackageRepository;
import com.deliver.dao.StoragePositionreRepository;
import com.deliver.model.Package;
import com.deliver.model.StoragePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

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

    @Transactional
    public Package getPackage(String id) {
        return packageRepository.findByMPackageId(id);
    }

    @Transactional
    public boolean addPackage(JSONObject jsonObject) {
        try {
            String packageId = jsonObject.getString("mPackageId");
            if (packageRepository.findByMPackageId(packageId) != null) {
                throw new Exception("已经存在此记录");
            } else {
                Package aPackage = new Package();
                aPackage.setmPackageId(jsonObject.getString("mPackageId"));
                aPackage.setmCompany(deliverCompanyRepository.findByMName(jsonObject.getString("mCompany")));
                aPackage.setmReceiveTime(new Timestamp(System.currentTimeMillis()));
                aPackage.setmTaken(false);
                aPackage.setmReceiverName(jsonObject.getString("mReceiverName"));
                aPackage.setmReceiverTele(jsonObject.getString("mReceiverTele"));
                aPackage.setmCupOrShelf(jsonObject.getBoolean("mCupOrShelf"));
                aPackage.setmPosition(storagePositionreRepository.findByMId(jsonObject.getInteger("storageId")));
                packageRepository.saveAndFlush(aPackage);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
