package com.deliver.dao;

import com.deliver.model.DeliverCompany;
import com.deliver.model.DeliverCompanyBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by deadoggy on 17-5-2.
 */

@Repository
@Transactional
public interface DeliverCompanyBillRepository extends JpaRepository<DeliverCompanyBill, Integer>{

    DeliverCompanyBill findBymId(int id);

    Page<DeliverCompanyBill> findByMCompany(DeliverCompany company, Pageable pageable);

    Page<DeliverCompanyBill> findBymCompanyAndMYear(DeliverCompany company, int year, Pageable pageable);

    List<DeliverCompanyBill> findBymCompanyAndMYearAndMMonth(DeliverCompany company, int year, int month);



}
