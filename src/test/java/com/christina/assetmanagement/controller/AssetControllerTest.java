package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.model.Status;
import com.christina.assetmanagement.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssetControllerTest {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void getAllAsset() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/allAsset" ,
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    void getAssetByName() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/Asset/desk" ,
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());

    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8})
    void getAssetById(int i) {
        Asset asset = restTemplate.getForObject(getRootUrl() + "/asset/" + i, Asset.class);
        System.out.println(asset.toString());
        assertNotNull(asset);
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"desk001","desk002","desk003","desk004","desk005"})
    void createAsset(String name) {
        Category category = new Category();
        category.setId(1);
        Asset asset = new Asset();
        asset.setName(name);
        asset.setCategory(category);
        asset.setStatus(Status.Available);
        ResponseEntity<Asset> postResponse = restTemplate.postForEntity(getRootUrl() + "/asset", asset, Asset.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    @ParameterizedTest
    @EnumSource(Status.class)
    void updateAsset(Status status ) {
        int id = 1;
        Asset asset = restTemplate.getForObject(getRootUrl() + "/asset/" + id, Asset.class);
        asset.setStatus(status);
        restTemplate.put(getRootUrl() + "/asset/" + id, asset);
        Asset updatedAsset = restTemplate.getForObject(getRootUrl() + "/asset/" + id, Asset.class);
        assertNotNull(updatedAsset);
    }


    @Test
    void deleteAsset() {
        int id = 1;
        Asset asset = restTemplate.getForObject(getRootUrl() + "/asset/" + id, Asset.class);
        assertNotNull(asset);
        restTemplate.delete(getRootUrl() + "/asset/" + id);
        try {
            asset = restTemplate.getForObject(getRootUrl() + "/asset/" + id, Asset.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }


}