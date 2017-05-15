package com.deliver.dao;

import com.deliver.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface PositionRepository extends JpaRepository<Position,Integer>{
    Position findByMId(int id);

    Page<Position> findByMPositionId(String positionId, Pageable pageable);

    Page<Position> findByMName(String name, Pageable pageable);
}
