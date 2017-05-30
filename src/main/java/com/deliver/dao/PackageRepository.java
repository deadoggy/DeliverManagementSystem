package com.deliver.dao;
import com.deliver.model.DeliverCompany;
import com.deliver.model.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by deadoggy on 17-4-26.
 */

@Transactional
@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{



    Package findByMId(int id);

    Package findByMPackageId(String packageId);

    Page<Package> findByMReceiverName(String receiverName, Pageable pageable);

    Page<Package> findByMReceiverTele(String receiverTele, Pageable pageable);

    Page<Package> findByMReceiveTime(Timestamp time, Pageable pageable);

    Page<Package> findByMCompany(DeliverCompany company, Pageable pageable);

    List<Package> findByMReceiverTele(String tele);
}
