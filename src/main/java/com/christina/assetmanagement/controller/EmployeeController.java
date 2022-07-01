package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.model.AssetStatusChange;
import com.christina.assetmanagement.model.Employee;
import com.christina.assetmanagement.model.Status;
import com.christina.assetmanagement.repository.AssetRepository;
import com.christina.assetmanagement.repository.AssetStatusChangeRepository;
import com.christina.assetmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeController
 *
 * @author Christina
 * @date 2022/07/01
 */
@RestController
@RequestMapping("")
public class EmployeeController {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private AssetStatusChangeRepository assetStatusChangeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * create a record of the asset being lent, when someone lends an asset.
     *
     * @param employeeId
     * @param assetId
     * @param conditionNote
     * @return {@code AssetStatusChange}
     * @throws Exception
     */
    @PostMapping("/lendAsset/{employee_id}/{asset_id}")
    public AssetStatusChange lendAsset(@PathVariable(value = "employee_id") Long employeeId,
                                       @PathVariable(value = "asset_id") Long assetId,
                                       String conditionNote) throws Exception {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new Exception("Asset not found for this id :: " + assetId));
        AssetStatusChange assetStatusChange = new AssetStatusChange();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));

        if (asset.getStatus() == Status.Assigned) {
            return null;
        }

        assetStatusChange.setAsset(asset);
        assetStatusChange.setAssetUser(employee);
        assetStatusChange.setReportStatus(Status.Assigned);
        assetStatusChange.setConditionNote(conditionNote);
        asset.setStatus(Status.Assigned);
        assetRepository.save(asset);
        return assetStatusChangeRepository.save(assetStatusChange);
    }

    /**
     * create a return asset record, when someone returns an asset.
     *
     * @param employeeId
     * @param assetId
     * @param conditionNote
     * @return {@code AssetStatusChange}
     * @throws Exception
     */
    @PostMapping("/returnAsset/{employee_id}/{asset_id}")
    public AssetStatusChange returnAsset(@PathVariable(value = "employee_id") Long employeeId,
                                         @PathVariable(value = "asset_id") Long assetId,
                                         String conditionNote) throws Exception {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new Exception("Asset not found for this id :: " + assetId));
        AssetStatusChange assetStatusChange = new AssetStatusChange();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));

        if (asset.getStatus() != Status.Assigned) {
            return null;
        }

        assetStatusChange.setAsset(asset);
        assetStatusChange.setAssetUser(employee);
        assetStatusChange.setReportStatus(Status.Recovered);
        assetStatusChange.setConditionNote(conditionNote);
        asset.setStatus(Status.Recovered);
        assetRepository.save(asset);
        return assetStatusChangeRepository.save(assetStatusChange);
    }

    /**
     * create a record of the asset being inspected, when someone inspects the returned asset.
     *
     * @param employeeId
     * @param assetId
     * @param conditionNote
     * @return {@code AssetStatusChange}
     * @throws Exception
     */
    @PostMapping("/CheckAsset/{employee_id}/{asset_id}")
    public AssetStatusChange checkAsset(@PathVariable(value = "employee_id") Long employeeId,
                                         @PathVariable(value = "asset_id") Long assetId,
                                         String conditionNote) throws Exception {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new Exception("Asset not found for this id :: " + assetId));
        AssetStatusChange assetStatusChange = new AssetStatusChange();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));

        if (asset.getStatus() != Status.Recovered) {
            return null;
        }

        assetStatusChange.setAsset(asset);
        assetStatusChange.setAssetUser(employee);
        assetStatusChange.setReportStatus(Status.Available);
        assetStatusChange.setConditionNote(conditionNote);
        asset.setStatus(Status.Available);
        assetRepository.save(asset);
        return assetStatusChangeRepository.save(assetStatusChange);
    }

}
