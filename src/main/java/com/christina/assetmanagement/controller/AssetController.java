package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.model.Status;
import com.christina.assetmanagement.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private AssetRepository assetRepository;

    /**
     * list all asset
     *
     * @return {@code List<Asset>}
     */
    @GetMapping("/allAsset")
    public List<Asset> getAllAsset() {
        return assetRepository.findAll();
    }


    /**
     * list all asset whose name containing name_part
     *
     * @param NamePart
     * @return {@code List<Asset>}
     */
    @GetMapping("/Asset/{Name_part}")
    public List<Asset> getAssetByName(@PathVariable(value = "Name_part") String NamePart) {
        return assetRepository.findByNameContaining(NamePart);
    }


    /**
     * get an asset by id
     *
     * @param assetId
     * @return {@code ResponseEntity<Asset>}
     * @throws Exception
     */
    @GetMapping("/asset/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable(value = "id") Long assetId) throws Exception {
        Asset asset = assetRepository.findById(assetId).orElseThrow(() -> new Exception("Asset not found for this id :: " + assetId));
        return ResponseEntity.ok().body(asset);
    }

    /**
     * create an new asset
     *
     * @param asset
     * @return {@code Asset}
     */
    @PostMapping("/asset")
    public Asset createAsset(@Valid @RequestBody Asset asset) {
        return assetRepository.save(asset);
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
                                             @Valid @RequestBody Asset assetDetails) throws Exception {
        Asset asset = assetRepository.findById(assetId).orElseThrow(() -> new Exception("Asset not found for this id :: " + assetId));

        asset.setName(assetDetails.getName());
        asset.setCategory(assetDetails.getCategory());
        asset.setConditionNote(assetDetails.getConditionNote());
        asset.setStatus(assetDetails.getStatus());

        final Asset updatedAsset = assetRepository.save(assetDetails);

        return ResponseEntity.ok(updatedAsset);
    }

    /**
     * delete an asset by id, can not delete when status of the asset is assigned
     *
     * @param assetId
     * @return {@code Map<String, Boolean>}
     * @throws Exception
     */
    @DeleteMapping("/asset/{id}")
    public Map<String, Boolean> deleteAsset(@PathVariable(value = "id") Long assetId)
            throws Exception {
        Asset asset = assetRepository.findById(assetId).orElseThrow(() -> new Exception("Asset not found for this id :: " + assetId));
        Map<String, Boolean> response = new HashMap<>();
        if (asset.getStatus() == Status.Assigned) {
            response.put("deleted", Boolean.FALSE);
        } else {
            assetRepository.delete(asset);
            response.put("deleted", Boolean.TRUE);
        }
        return response;
    }
}