package com.deliver.dao;

import com.deliver.model.DeliverCompany;
import com.deliver.model.ProxyChargeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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


    @Query("select pc from ProxyChargeRecord as pc where pc.mDate between ?1 and ?2")
    List<ProxyChargeRecord> getByDate(Date beg, Date end);

    @Query("select pc from ProxyChargeRecord as pc where pc.mDate between ?1 and ?2 and pc.mPackage.mCompany = ?3")
    List<ProxyChargeRecord> getByDateAndCompany(Date beg, Date end, DeliverCompany company);



}
