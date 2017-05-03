package com.deliver.dao;

import com.deliver.model.HourForm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by deadoggy on 17-5-2.
 */

@Transactional
@Repository
public interface HourFormRepository extends JpaRepository<HourForm, Integer> {

    HourForm findBymId(int id);

    Page<HourForm> findBymYearAndmMonthAndmDayAndmHour(int year, int month, int day, int hour);

    Page<HourForm> findBymYearAndmMonthAndmDay(int year, int month, int day);

    Page<HourForm> findBymYearAndmMonth(int year, int month);

    Page<HourForm> findBymYear(int year);

}
