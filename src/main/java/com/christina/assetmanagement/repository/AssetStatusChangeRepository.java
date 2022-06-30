package com.christina.assetmanagement.repository;

import com.christina.assetmanagement.model.AssetStatusChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetStatusChangeRepository extends JpaRepository<AssetStatusChange, Long> {
}
