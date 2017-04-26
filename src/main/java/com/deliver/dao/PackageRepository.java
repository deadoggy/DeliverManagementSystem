package com.deliver.dao;
import com.deliver.model.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by deadoggy on 17-4-26.
 */

@Transactional
@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{



    Package findBymId(int id);

    Page<Package> findBymPackageId(String packageId);

    Page<Package> findBymReceiverName(String receiverName);

    Page<Package> findBymReceiverTele(String receiverTele);

    Page<Package> findBymReceiveTime(Timestamp time);

}
