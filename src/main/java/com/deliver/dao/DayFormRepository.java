package com.deliver.dao;

import com.deliver.model.DayForm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by deadoggy on 17-5-2.
 */
@Transactional
@Repository
public interface DayFormRepository extends JpaRepository<DayForm, Integer>{

    DayForm findBymId(int id);

    Page<DayForm> findBymYearAndmMonthAndmDay(int year, int mon, int day);

    Page<DayForm> findBymYearAndmMonth(int year, int mon);

    Page<DayForm> findBymYear(int year);
}
