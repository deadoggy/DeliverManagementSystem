package com.deliver.dao;

import com.deliver.model.SendingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface SendingRecordRepository extends JpaRepository<SendingRecord,Integer>{
    SendingRecord findByMId(int id);

    Page<SendingRecord> findByMSendingRecordId(String sendingRecordId, Pageable pageable);

    Page<SendingRecord> findByMSenderName(String senderName, Pageable pageable);

    Page<SendingRecord> findByMSenderTele(String mSenderTele, Pageable pageable);
}
