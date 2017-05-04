package com.deliver.dao;

import com.deliver.model.Shelf;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface ShelfRepository extends JpaRepository<Shelf,Integer>{
    Shelf findBymId(int id);

    Page<Shelf> findBymShelfId(String shelfId);

    Page<Shelf> findBymEmptySum(int emptySum);

    Page<Shelf> findBymPositionSum(int positionSum);

    Page<Shelf> findBymLayerSum(int layerSum);

    Page<Shelf> findBymColumnSum(int columnSum);
}
