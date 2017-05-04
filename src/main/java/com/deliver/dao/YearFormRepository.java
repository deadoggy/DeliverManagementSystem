package com.deliver.dao;

import com.deliver.model.YearForm;
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
public interface YearFormRepository extends JpaRepository<YearForm,Integer>{
    YearForm findByMId(int id);

    Page<YearForm> findByMYear(int year, Pageable pageable);

    Page<YearForm> findByMSum(int sum, Pageable pageable);
}
