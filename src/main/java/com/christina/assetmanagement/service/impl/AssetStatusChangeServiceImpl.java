package com.christina.assetmanagement.service.impl;

import com.christina.assetmanagement.exception.ResourceNotFoundException;
import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.model.AssetStatusChange;
import com.christina.assetmanagement.model.Employee;
import com.christina.assetmanagement.model.Status;
import com.christina.assetmanagement.payload.ApiResponse;
import com.christina.assetmanagement.repository.AssetRepository;
import com.christina.assetmanagement.repository.AssetStatusChangeRepository;
import com.christina.assetmanagement.repository.EmployeeRepository;
import com.christina.assetmanagement.service.AssetStatusChangeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-03 20:40
 **/
@Service
public class AssetStatusChangeServiceImpl implements AssetStatusChangeService {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private AssetStatusChangeRepository assetStatusChangeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public AssetStatusChangeServiceImpl(AssetRepository assetRepository, AssetStatusChangeRepository assetStatusChangeRepository, EmployeeRepository employeeRepository) {
        this.assetRepository = assetRepository;
        this.assetStatusChangeRepository = assetStatusChangeRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<List<AssetStatusChange>> getAllAssetStatusChange() {
        List<AssetStatusChange> assetStatusChangeList = assetStatusChangeRepository.findAll();
        return new ResponseEntity<>(assetStatusChangeList, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<ApiResponse> lendAsset(long employeeId, long assetId, String conditionNote) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", assetId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        if (asset.getStatus() == Status.Assigned) {
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE,
                    "Can not lend the asset when it is assigned. "), HttpStatus.FORBIDDEN);
        }

        AssetStatusChange assetStatusChange = new AssetStatusChange();
        assetStatusChange.setAsset(asset);
        assetStatusChange.setAssetUser(employee);
        assetStatusChange.setReportStatus(Status.Assigned);
        assetStatusChange.setConditionNote(conditionNote);
        asset.setStatus(Status.Assigned);
        assetRepository.save(asset);
        assetStatusChangeRepository.save(assetStatusChange);
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE,
                "You have lend asset" + assetId + "."), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse> returnAsset(long employeeId, long assetId, String conditionNote) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", assetId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        if (asset.getStatus() != Status.Assigned) {
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE,
                    "Can not return the asset when it is not assigned. "), HttpStatus.FORBIDDEN);
        }

        AssetStatusChange assetStatusChange = new AssetStatusChange();
        assetStatusChange.setAsset(asset);
        assetStatusChange.setAssetUser(employee);
        assetStatusChange.setReportStatus(Status.Recovered);
        assetStatusChange.setConditionNote(conditionNote);
        asset.setStatus(Status.Recovered);
        assetRepository.save(asset);
        assetStatusChangeRepository.save(assetStatusChange);
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE,
                "You have returned asset" + assetId + "."), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse> checkAsset(long employeeId, long assetId, String conditionNote) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", assetId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        if (asset.getStatus() != Status.Recovered) {
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE,
                    "Can not inspect the asset when it is recovered. "), HttpStatus.FORBIDDEN);
        }
        AssetStatusChange assetStatusChange = new AssetStatusChange();
        assetStatusChange.setAsset(asset);
        assetStatusChange.setAssetUser(employee);
        assetStatusChange.setReportStatus(Status.Available);
        assetStatusChange.setConditionNote(conditionNote);
        asset.setStatus(Status.Available);
        assetRepository.save(asset);
        assetStatusChangeRepository.save(assetStatusChange);
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE,
                "You have inspected asset" + assetId + "."), HttpStatus.OK);
    }
}
