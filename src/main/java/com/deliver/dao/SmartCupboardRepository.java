package com.deliver.dao;

import com.deliver.model.SmartCupboard;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/3.
 */
@Transactional
@Repository
public interface SmartCupboardRepository extends JpaRepository<SmartCupboard,Integer>{
    SmartCupboard findBymId(int id);

    Page<SmartCupboard> findBymCupboardId(String cupboardId);

    Page<SmartCupboard> findBymEmptySum(int emptySum);

    Page<SmartCupboard> findBymPositionSum(int positionSum);

    Page<SmartCupboard> findBymLayerSum(int layerSum);

    Page<SmartCupboard> findBymColumnSum(int columnSum);


}
