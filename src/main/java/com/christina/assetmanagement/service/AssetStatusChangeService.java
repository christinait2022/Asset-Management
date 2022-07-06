package com.christina.assetmanagement.service;

import com.christina.assetmanagement.model.AssetStatusChange;
import com.christina.assetmanagement.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-03 20:39
 **/
public interface AssetStatusChangeService {

    ResponseEntity<List<AssetStatusChange>> getAllAssetStatusChange();

    ResponseEntity<ApiResponse> lendAsset(long employeeId, long assetId, String conditionNote);

    ResponseEntity<ApiResponse> returnAsset(long employeeId, long assetId, String conditionNote);

    ResponseEntity<ApiResponse> checkAsset(long employeeId, long assetId, String conditionNote);
}
