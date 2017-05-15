package com.deliver.dao;
import com.deliver.model.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.List;
/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface PointRepository extends JpaRepository<Point, Integer>{
    Point findByMId(int id);

    Point findByMBusinessPointId(String id);

    Page<Point> findByMName(String name, Pageable pageable);

    Page<Point> findByMProvince(String province, Pageable pageable);

    Page<Point> findByMCity(String city, Pageable pageable);

    Page<Point> findByMCounty(String country, Pageable pageable);

    Page<Point> findByMRoad(String road, Pageable pageable);

    Page<Point> findByMNumber(String number, Pageable pageable);

}
