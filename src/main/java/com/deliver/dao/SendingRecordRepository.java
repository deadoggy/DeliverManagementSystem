package com.deliver.dao;

import com.deliver.model.SendingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface SendingRecordRepository extends JpaRepository<SendingRecord,Integer>{
    SendingRecord findByMId(int id);

    SendingRecord findByMSendingRecordId(String sendingRecordId);

    Page<SendingRecord> findByMSenderName(String senderName, Pageable pageable);

    Page<SendingRecord> findByMSenderTele(String mSenderTele, Pageable pageable);

    @Query("select sr from SendingRecord  as sr where sr.mSendTime between ?1 and ?2")
    List<SendingRecord> getSendingRecordInPeriod(Timestamp beg, Timestamp end);

    @Query("select send from SendingRecord as send where send.mSendingRecordId like ?1%")
    List<SendingRecord> getSendingRecordBegin(String id);

}
