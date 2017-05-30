package com.deliver.dao;

import com.deliver.model.Refuse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Created by deadoggy on 17-5-17.
 */

@Repository
@Transactional
public interface RefuseRepository extends JpaRepository<Refuse, Integer>{

    List<Refuse> findByMPackageId(String packageid);

    List<Refuse> findByMDate(Date date);

}
