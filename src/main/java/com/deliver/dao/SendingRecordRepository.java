package com.deliver.dao;

import com.deliver.model.SendingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface SendingRecordRepository extends JpaRepository<SendingRecord,Integer>{
    SendingRecord findBymId(int id);

    Page<SendingRecord> findBymSendingRecordId(String sendingRecordId);

    Page<SendingRecord> findBymSenderName(String senderName);

    Page<SendingRecord> findBymSenderTele(String mSenderTele);
}
