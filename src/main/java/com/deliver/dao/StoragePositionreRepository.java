package com.deliver.dao;

import com.deliver.model.StoragePosition;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface StoragePositionreRepository extends JpaRepository<StoragePosition,Integer>{
    StoragePosition findBymId(int id);

    Page<StoragePosition> findBymCupOrShelf(boolean cupOrShelf);

    Page<StoragePosition> findBymEmptyOrFull(boolean emptyOrFull);

    Page<StoragePosition> findBymLayer(int layer);

    Page<StoragePosition> findBymColumn(int column);

    Page<StoragePosition> findBymSize(int size);
}
