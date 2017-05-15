package com.deliver.dao;

import com.deliver.model.StoragePosition;
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
public interface StoragePositionreRepository extends JpaRepository<StoragePosition,Integer>{
    StoragePosition findByMId(int id);

    Page<StoragePosition> findByMCuporShelf(boolean cuporShelf, Pageable pageable);

    Page<StoragePosition> findByMEmpty(boolean empty, Pageable pageable);

    Page<StoragePosition> findByMLayer(int layer, Pageable pageable);

    Page<StoragePosition> findByMColumn(int column, Pageable pageable);
}
