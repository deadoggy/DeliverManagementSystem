package com.deliver.dao;

import com.deliver.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.deliver.model.Attendance;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by deadoggy on 17-4-26.
 */

@Transactional
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{

    Attendance findByMId(int id);

    Page<Attendance> findByMEmployee(Employee employee, Pageable pageable);

    Page<Attendance> findByMBeginTime( Timestamp begTime, Pageable pageable);

    Page<Attendance> findByMEndTime(Timestamp endTime, Pageable pageable);




}
