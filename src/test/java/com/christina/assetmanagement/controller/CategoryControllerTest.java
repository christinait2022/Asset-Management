package com.christina.assetmanagement.controller;

import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerTest {

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
    void getAllCategory() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/allCategory",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    void getCategoryById() {
        Category category = restTemplate.getForObject(getRootUrl() + "/category/1", Category.class);
        System.out.println(category.toString());
        assertNotNull(category);
    }

    @Test
    void createCategory() {
        Category category = new Category();
        category.setName("furniture");
        ResponseEntity<Category> postResponse = restTemplate.postForEntity(getRootUrl() + "/category", category, Category.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    void updateCategory() {
        int id = 1;
        Category category = restTemplate.getForObject(getRootUrl() + "/category/" + id, Category.class);
        category.setName("update");
        restTemplate.put(getRootUrl() + "/category/" + id, category);
        Category updatedCategory = restTemplate.getForObject(getRootUrl() + "/category/" + id, Category.class);
        assertNotNull(updatedCategory);
        assertNotNull(updatedCategory.getName());
        assertArrayEquals("update".toCharArray(), updatedCategory.getName().toCharArray());

    }


    @Test
    void deleteCategory() {
        int id = 2;
        Category category = restTemplate.getForObject(getRootUrl() + "/category/" + id, Category.class);
        assertNotNull(category);
        restTemplate.delete(getRootUrl() + "/category/" + id);
        try {
            category = restTemplate.getForObject(getRootUrl() + "/category/" + id, Category.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}