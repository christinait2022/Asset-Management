package com.christina.assetmanagement.repository;

import com.christina.assetmanagement.model.AssetStatusChange;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AssetStatusChangeRepository
 *
 * @author Christina
 * @date 2022/07/01
 */
public interface AssetStatusChangeRepository extends JpaRepository<AssetStatusChange, Long> {
}
