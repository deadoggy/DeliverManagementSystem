package com.deliver.dao;

import com.deliver.model.Shelf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface ShelfRepository extends JpaRepository<Shelf,Integer>{
    Shelf findByMId(int id);

    Shelf findByMShelfId(String shelfId);

    Page<Shelf> findByMEmptySum(int emptySum, Pageable pageable);

    Page<Shelf> findByMPositionSum(int positionSum, Pageable pageable);

    Page<Shelf> findByMLayerSum(int layerSum, Pageable pageable);

    Page<Shelf> findByMColumnSum(int columnSum, Pageable pageable);


}
