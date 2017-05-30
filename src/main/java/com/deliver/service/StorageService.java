package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.StoragePositionreRepository;
import com.deliver.model.StoragePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/15.
 */
@Service
public class StorageService {
    @Autowired
    private StoragePositionreRepository storagePositionreRepository;

    @Transactional
    public StoragePosition getStorageById(int id) {
        return storagePositionreRepository.findByMId(id);
    }



}
