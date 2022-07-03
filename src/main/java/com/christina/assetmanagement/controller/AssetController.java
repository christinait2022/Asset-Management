package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.payload.ApiResponse;
import com.christina.assetmanagement.service.AssetService;
import com.christina.assetmanagement.service.AssetStatusChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * AssetController
 *
 * @author Christina
 * @date 2022/07/01
 */
@RestController
@RequestMapping("")
public class AssetController {

    @Autowired
    AssetService assetService;

    @Autowired
    AssetStatusChange assetStatusChange;

    /**
     * [GET] /allAsset
     * list all asset.
     * <p>
     * [GET] /allAsset?name=[keyword]
     * if there is a parameter name, then list all asset whose name containing keyword.
     *
     * @param keyword
     * @return {@code List<Asset>}
     */
    @GetMapping("/allAsset")
    public ResponseEntity<List<Asset>> getAllAsset(@RequestParam(required = false, value = "name") String keyword) {

        if (keyword == null) {
            return assetService.getAllAsset();
        } else {
            return assetService.getByNameContaining(keyword);
        }
    }

    /**
     * get an asset by id
     *
     * @param assetId
     * @return {@code ResponseEntity<Asset>}
     * @throws Exception
     */
    @GetMapping("/asset/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable(value = "id") Long assetId) {

        return assetService.getAssetById(assetId);
    }

    /**
     * create an new asset
     *
     * @param asset
     * @return {@code Asset}
     */
    @PostMapping("/asset")
    public ResponseEntity<Asset> createAsset(@Valid @RequestBody Asset asset) {

        return assetService.addAsset(asset);
    }

    /**
     * update an asset by id
     *
     * @param assetId
     * @param assetDetails
     * @return {@code ResponseEntity<Asset>}
     * @throws Exception
     */
    @PutMapping("/asset/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable(value = "id") Long assetId,
                                             @Valid @RequestBody Asset assetDetails) {

        return assetService.updateAssetById(assetId, assetDetails);

    }

    /**
     * delete an asset by id, can not delete when status of the asset is assigned
     *
     * @param assetId
     * @return {@code Map<String, Boolean>}
     * @throws Exception
     */
    @DeleteMapping("/asset/{id}")
    public ResponseEntity deleteAsset(@PathVariable(value = "id") Long assetId) {

        return assetService.deleteAssetById(assetId);
    }

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
    public ResponseEntity<ApiResponse> lendAsset(@PathVariable(value = "employee_id") Long employeeId,
                                                 @PathVariable(value = "asset_id") Long assetId,
                                                 String conditionNote) throws Exception {
        return assetStatusChange.lendAsset(employeeId, assetId, conditionNote);

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
    public ResponseEntity<ApiResponse> returnAsset(@PathVariable(value = "employee_id") Long employeeId,
                                                   @PathVariable(value = "asset_id") Long assetId,
                                                   String conditionNote) throws Exception {
        return assetStatusChange.returnAsset(employeeId, assetId, conditionNote);
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
    public ResponseEntity<ApiResponse> checkAsset(@PathVariable(value = "employee_id") Long employeeId,
                                                  @PathVariable(value = "asset_id") Long assetId,
                                                  String conditionNote) throws Exception {
        return assetStatusChange.checkAsset(employeeId, assetId, conditionNote);
    }
}