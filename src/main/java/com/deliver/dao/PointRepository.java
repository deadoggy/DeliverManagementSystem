package com.deliver.dao;
import com.deliver.model.Point;
import org.springframework.data.domain.Page;
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
    Point findBymId(int id);

    Page<Point> findBymPositionId(String positionId);

    Page<Point> findBymName(String name);

    Page<Point> findBymProvince(String province);

    Page<Point> findBymCity(String city);

    Page<Point> findBymCounty(String country);

    Page<Point> findBymRoad(String road);

    Page<Point> findBymNumber(String number);

}
