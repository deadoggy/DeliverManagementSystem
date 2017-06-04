package com.deliver.dao;

import com.deliver.model.DayForm;
import com.deliver.model.DeliverCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by deadoggy on 17-5-2.
 */
@Transactional
@Repository
public interface DayFormRepository extends JpaRepository<DayForm, Integer>{

    DayForm findByMId(int id);

    List<DayForm> findMSumByMDateAndMCompany(Date date, DeliverCompany company);

    @Query("select df.mSum from DayForm  as df where df.mDate between  ?1 and ?2")
    List<Integer> findInPeriod(Date beg, Date end);

    @Query("select df.mSum from DayForm  as df where df.mCompany=?1 and df.mDate between  ?2 and ?3")
    List<Integer> findInPeriodOfCompany(DeliverCompany company, Date beg, Date end);

}
