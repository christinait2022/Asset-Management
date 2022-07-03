package com.christina.assetmanagement.service;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p>Asset Service</p>
 * @date 2022-07-02 15:15
 **/

public interface AssetService {

    ResponseEntity<List<Asset>> getAllAsset();

    ResponseEntity<List<Asset>> getByNameContaining(String keyword);

    ResponseEntity<Asset> getAssetById(Long id);

    ResponseEntity<Asset> addAsset(Asset asset);

    ResponseEntity<Asset> updateAssetById(Long id, Asset newAsset);

    ResponseEntity<ApiResponse> deleteAssetById(Long id);


}
