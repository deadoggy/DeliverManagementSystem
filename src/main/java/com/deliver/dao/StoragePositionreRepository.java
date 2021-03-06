package com.deliver.dao;

import com.deliver.model.StoragePosition;
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

@Repository
public interface StoragePositionreRepository extends JpaRepository<StoragePosition,Integer>{
    StoragePosition findByMId(int id);

    StoragePosition findByMStorageId(String storageId);

    Page<StoragePosition> findByMCuporShelf(boolean cuporShelf, Pageable pageable);

    Page<StoragePosition> findByMEmpty(boolean empty, Pageable pageable);

    Page<StoragePosition> findByMLayer(int layer, Pageable pageable);

    Page<StoragePosition> findByMColumn(int column, Pageable pageable);

    StoragePosition findByMIdentifyCode(String identifyCode);

    @Query("select s.mStorageId from StoragePosition as s where s.mLayer=?2 and s.mColumn=?3 " +
            "and s.mShelf = (select she from Shelf as she where she.mId = ?1))")
    List<String> findMShelfAndMLayerAndMColumn(int id, int layer, int column);

    @Query("select s.mStorageId from StoragePosition as s where s.mLayer=?2 and s.mColumn=?3 " +
            "and s.mCup=(select cup from SmartCupboard as cup where cup.mId=?1)")
    List<String> findMCupAndMLayerAndMColumn(int id,int layer,int column);
}
