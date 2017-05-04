package com.deliver.dao;

import com.deliver.model.YearForm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface YearFormRepository extends JpaRepository<YearForm,Integer>{
    YearForm findBymId(int id);

    Page<YearForm> findBymYearFormId(String yearFormId);

    Page<YearForm> findBymYear(int year);

    Page<YearForm> findBymSum(int sum);
}
