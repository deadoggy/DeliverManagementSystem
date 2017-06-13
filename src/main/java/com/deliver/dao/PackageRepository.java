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

    @Query("select p from Package as p where p.mPackageId like ?1%")
    List<Package> findByMPackageIdBegin(String id);

    @Query("select p from Package as p where p.mReceiverTele like ?1%")
    List<Package> findByMPackageTeleBegin(String tele);

    @Query("select p from Package  as p where p.mCompany = ?1 and p.mReceiveTime between ?2 and ?3")
    List<Package> getByCompanyAndReceTime(DeliverCompany company, Timestamp beg, Timestamp end);

    @Query("select p from Package  as p where p.mReceiveTime between ?2 and ?3")
    List<Package> getByReceTime(Timestamp beg, Timestamp end);

    @Query("select p from Package as p where p.mTaken=0")
    List<Package> getAllNoTaken();

    @Query("select p from Package as p where p.mTaken=1")
    List<Package> getAllTaken();

    @Query("select p from Package as p where p.mTaken=0 and p.mPosition= " +
            "(select s from StoragePosition as s where s.mId=?1)")
    Package getByStoragePosition(int id);

    @Query("select p from Package as p where p.mTaken=0 and p.mReceiveTime<?1")
    List<Package> getOvertime(Timestamp t);

    @Query("select p.mProxyChargeFee from Package as p where p.mTaken=0 and p.mPosition= " +
            "(select s from StoragePosition as s where s.mId=?1)")
    double getFee(int id);

    @Query("select p from Package as p where p.mTaken=0 and p.mPosition= " +
            "(select s from StoragePosition as s where s.mId=?1)")
    Package getByStorageMId(int mId);

    @Query("select p from Package as p where p.mTaken=0 and p.mPackageId like ?1%")
    List<Package> findNoTakenMPackageIdBegin(String id);

    @Query("select p from Package as p where p.mTaken=1 and p.mPackageId like ?1%")
    List<Package> findTakenMPackageIdBegin(String id);

}
