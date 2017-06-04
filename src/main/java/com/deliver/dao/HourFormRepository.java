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
import java.util.List;


/**
 * Created by deadoggy on 17-5-2.
 */

@Transactional
@Repository
public interface HourFormRepository extends JpaRepository<HourForm, Integer> {

    HourForm findBymId(int id);
}
