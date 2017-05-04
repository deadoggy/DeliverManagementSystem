package com.deliver.dao;

import com.deliver.model.DeliverCompany;
import com.deliver.model.HourForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;


/**
 * Created by deadoggy on 17-5-2.
 */

@Transactional
@Repository
public interface HourFormRepository extends JpaRepository<HourForm, Integer> {

    HourForm findBymId(int id);

    Page<HourForm> findByMYearAndMMonthAndMDayAndMHour( int year, int month, int day, int hour, Pageable pageable);

    Page<HourForm> findByMYearAndMMonthAndMDay(int year, int month, int day,  Pageable pageable);

    Page<HourForm> findByMYearAndMMonthAndMDayAndMCompany(int year, int month, int day, DeliverCompany company, Pageable pageable);

    Page<HourForm> findByMYearAndMMonth(int year, int month, Pageable pageable);

    Page<HourForm> findByMYear(int year, Pageable pageable);

//    @Query("select HourForm from HourForm as hf , DeliverCompany as c where hf.mDate >= ?1 and hf.mDate < ?2 and hf.mCompany = c")
//    Page<HourForm> findByPeriod(Date beg, Date end, DeliverCompany company);

}
