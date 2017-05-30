package com.deliver.dao;

import com.deliver.model.DeliverCompany;
import com.deliver.model.ProxyChargeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface ProxyChargeRecordRepository extends JpaRepository<ProxyChargeRecord,Integer>{
    ProxyChargeRecord findByMId(int id);

    Page<ProxyChargeRecord> findByMFee(double fee, Pageable pageable);

    Page<ProxyChargeRecord> findByMSendorReceive(boolean sendorReceive, Pageable pageable);

    Page<ProxyChargeRecord> findByMTime(Timestamp time, Pageable pageable);

    @Query("select ProxyChargeRecord from ProxyChargeRecord as pc, Package  as pk where pc.mDate = ?1 and pk.mId = pc.mPackage")
    List<ProxyChargeRecord> getByDate(Date date);



}
