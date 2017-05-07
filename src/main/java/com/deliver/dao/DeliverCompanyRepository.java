package com.deliver.dao;

import com.deliver.model.DeliverCompany;
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
public interface DeliverCompanyRepository extends JpaRepository<DeliverCompany, Integer> {

    DeliverCompany findBymId(int id);

    Page<DeliverCompany> findByMCompanyId( String companyId, Pageable pageable);

    Page<DeliverCompany> findByMName(String name, Pageable pageable);

}
