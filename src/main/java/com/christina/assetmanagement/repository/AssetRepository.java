package com.christina.assetmanagement.repository;

import com.christina.assetmanagement.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByNameContaining(String Sting);
}
