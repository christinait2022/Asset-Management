package com.christina.assetmanagement.service.impl;

import com.christina.assetmanagement.exception.ResourceNotFoundException;
import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.model.Status;
import com.christina.assetmanagement.payload.ApiResponse;
import com.christina.assetmanagement.repository.AssetRepository;
import com.christina.assetmanagement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-02 15:52
 **/
@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public ResponseEntity<List<Asset>> getAllAsset() {
        List<Asset> assetList = assetRepository.findAll();
        return new ResponseEntity<>(assetList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Asset>> getByNameContaining(String keyword) {
        if (keyword == null) {
            throw new NullPointerException("keyword is null");
        }
        List<Asset> assetList = assetRepository.findByNameContaining(keyword);
        return new ResponseEntity<>(assetList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Asset> getAssetById(Long assetId) {
        if (assetId == null) {
            throw new NullPointerException("assetId is null");
        }
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", assetId));
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Asset> addAsset(Asset asset) {
        if (asset == null) {
            throw new NullPointerException("asset is null");
        }
        Asset newAsset = assetRepository.save(asset);
        return new ResponseEntity<>(newAsset, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Asset> updateAssetById(Long assetId, Asset assetDetails) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", assetId));
        asset.setName(assetDetails.getName());
        asset.setName(assetDetails.getName());
        asset.setCategory(assetDetails.getCategory());
        asset.setConditionNote(assetDetails.getConditionNote());
        asset.setStatus(assetDetails.getStatus());
        Asset updatedAsset = assetRepository.save(asset);
        return new ResponseEntity<>(updatedAsset, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse> deleteAssetById(Long assetId) {
        if (assetId == null) {
            throw new NullPointerException("assetId is null");
        }
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("asset", "id", assetId));
        if (asset.getStatus() == Status.Assigned) {
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "Can not delete the asset when it is assigned. "), HttpStatus.FORBIDDEN);
        }
        assetRepository.deleteById(assetId);
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "You successfully deleted asset"), HttpStatus.OK);
    }


}
