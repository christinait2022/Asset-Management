package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.AssetStatusChange;
import com.christina.assetmanagement.repository.AssetRepository;
import com.christina.assetmanagement.repository.AssetStatusChangeRepository;
import com.christina.assetmanagement.repository.CategoryRepository;
import com.christina.assetmanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private AssetStatusChangeRepository assetStatusChangeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void lendAsset() {
        int asset_id = 5;
        int employee_id = 1;
        ResponseEntity<AssetStatusChange> postResponse = restTemplate.postForEntity(
                getRootUrl() + "/lendAsset/" + employee_id + "/" + asset_id, null, AssetStatusChange.class);
        System.out.println(postResponse.toString());
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    void returnAsset() {
        int asset_id = 5;
        int employee_id = 1;
        ResponseEntity<AssetStatusChange> postResponse = restTemplate.postForEntity(
                getRootUrl() + "/returnAsset/" + employee_id + "/" + asset_id, null, AssetStatusChange.class);

        System.out.println(postResponse.toString());
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    void checkAsset() {
        int asset_id = 5;
        int employee_id = 1;
        ResponseEntity<AssetStatusChange> postResponse = restTemplate.postForEntity(
                getRootUrl() + "/checkAsset/" + employee_id + "/" + asset_id, null, AssetStatusChange.class);
        System.out.println(postResponse.toString());
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
}