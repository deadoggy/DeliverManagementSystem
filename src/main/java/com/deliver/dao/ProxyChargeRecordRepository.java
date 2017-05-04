package com.deliver.dao;

import com.deliver.model.DeliverCompany;
import com.deliver.model.ProxyChargeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;


/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface ProxyChargeRecordRepository extends JpaRepository<ProxyChargeRecord,Integer>{
    ProxyChargeRecord findBymId(int id);

    Page<ProxyChargeRecord> findBymFee(double fee);

    Page<ProxyChargeRecord> findBymSendOrReceive(boolean sendOrReceive);

    Page<ProxyChargeRecord> findBymTime(Timestamp time);

}
