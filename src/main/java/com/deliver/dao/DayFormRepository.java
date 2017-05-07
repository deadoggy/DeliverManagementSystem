package com.deliver.dao;

import com.deliver.model.DayForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by deadoggy on 17-5-2.
 */
@Transactional
@Repository
public interface DayFormRepository extends JpaRepository<DayForm, Integer>{

    DayForm findByMId(int id);

    Page<DayForm> findByMYearAndMMonthAndMDay(int year, int mon, int day, Pageable pageable);

    Page<DayForm> findByMYearAndMMonth(int year, int mon, Pageable pageable);

    Page<DayForm> findByMYear(int year, Pageable pageable);
}
