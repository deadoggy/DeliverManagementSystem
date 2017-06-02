package com.deliver.dao;

/**
 * Created by deadoggy on 17-5-2.
 */

import com.deliver.model.Employee;
import com.deliver.model.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findBymId(int id);

    Employee findByMEmployeeId(String employeeId);

    List<Employee> findByMName(String name);



}
