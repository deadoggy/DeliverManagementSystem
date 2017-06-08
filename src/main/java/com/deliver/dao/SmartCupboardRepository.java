package com.deliver.dao;

import com.deliver.model.SmartCupboard;
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
public interface SmartCupboardRepository extends JpaRepository<SmartCupboard,Integer>{
    SmartCupboard findByMId(int id);

    SmartCupboard findByMCupboardId(String cupboardId);

    Page<SmartCupboard> findByMEmptySum(int emptySum, Pageable pageable);

    Page<SmartCupboard> findByMPositionSum(int positionSum, Pageable pageable);

    Page<SmartCupboard> findByMLayerSum(int layerSum, Pageable pageable);

    Page<SmartCupboard> findByMColumnSum(int columnSum, Pageable pageable);

    @Query("select s from SmartCupboard as s where s.mCupboardId like ?1%")
    List<SmartCupboard> getSmartCupboardBegin(String id);
}
