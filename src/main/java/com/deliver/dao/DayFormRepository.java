package com.deliver.dao;

import com.deliver.model.DayForm;
import com.deliver.model.DeliverCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by deadoggy on 17-5-2.
 */
@Transactional
@Repository
public interface DayFormRepository extends JpaRepository<DayForm, Integer>{

    DayForm findByMId(int id);

    List<Integer> findMSumByMYearAndMMonthAndMDayAndMCompany(int year, int mon, int day, DeliverCompany company);

    List<Integer> findMSumByMYearAndMMonthAndMCompany(int year, int mon, DeliverCompany company);

    List<Integer> findMSumByMYearAndMCompany(int year, DeliverCompany company);
}
