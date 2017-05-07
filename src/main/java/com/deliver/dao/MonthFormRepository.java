package com.deliver.dao;

import com.deliver.model.MonthForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by deadoggy on 17-5-2.
 */
@Repository
@Transactional
public interface MonthFormRepository extends JpaRepository<MonthForm, Integer> {

    MonthForm findBymId(int id);

    Page<MonthForm>  findByMYearAndMMonth( int year, int month, Pageable pageable);

    Page<MonthForm> findByMYear( int year, Pageable pageable);

}
