package com.christina.assetmanagement.repository;

import com.christina.assetmanagement.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * AssetRepository
 *
 * @author Christina
 * @date 2022/07/01
 */
public interface AssetRepository extends JpaRepository<Asset, Long> {

    /**
     * list all asset whose name containing string
     *
     * @param string
     * @return {@code List<Asset>}
     */
    List<Asset> findByNameContaining(String string);
}
