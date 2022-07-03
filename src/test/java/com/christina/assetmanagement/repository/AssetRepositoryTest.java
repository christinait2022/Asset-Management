package com.christina.assetmanagement.repository;

import com.christina.assetmanagement.model.Asset;
import com.christina.assetmanagement.model.Category;
import com.christina.assetmanagement.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <h3>AssetManagement</h3>
 *
 * @author Christina
 * @description <p></p>
 * @date 2022-07-02 00:23
 **/

@SpringBootTest
class AssetRepositoryTest {


    @Autowired
    AssetRepository assetRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void should_find_no_assets_if_repository_is_empty() {
        Iterable assets = assetRepository.findAll();
        assertThat(assets).isEmpty();
    }

    @Test
    public void should_store_a_asset() {
        Category category = new Category(null, null, false, null);
        Asset asset1 = new Asset("desk001", null, false, null, category, Status.Available);
        categoryRepository.save(category);
        Asset asset = assetRepository.save(asset1);
        assertThat(asset).hasFieldOrPropertyWithValue("name", "desk001");
        assertThat(asset).hasFieldOrPropertyWithValue("conditionNote", null);
        assertThat(asset).hasFieldOrPropertyWithValue("isDeleted", false);
        //    assertThat(asset).hasFieldOrPropertyWithValue("category", category);
        Iterable assets = assetRepository.findAll();
        System.out.println(assets.toString());
    }
}

//        @Test
//        public void should_find_all_assets() {
//            Category category = new Category(1,null,null,false,null);
//            categoryRepository.save(category);
//            Asset asset1 = new  Asset(1,"desk001", null, false, null,category, Status.Available);
//            entityManager.persist(asset1);
//            Asset asset2 = new  Asset(2,"desk001", null, false, null,category, Status.Available);
//            entityManager.persist(asset2);
//            Asset asset3 = new Asset(3,"desk001", null, false, null,category, Status.Available);
//            entityManager.persist(asset3);
//            Iterable assets = assetRepository.findAll();
//            assets.toString();
//            assertThat(assets).hasSize(3).contains(asset1, asset2, asset3);
//        }
//        @Test
//        public void should_find_asset_by_id() {
//            Category category = new Category(1,null,null,false,null);
//            entityManager.persist(category);
//            Asset asset1 = new Asset(1,"desk001", null, false, null,category, Status.Available);
//            entityManager.persist(asset1);
//            Asset asset2 = new Asset(2,"desk001", null, false, null,category, Status.Available);
//            entityManager.persist(asset2);
//            Asset foundAsset = assetRepository.findById(asset2.getId()).get();
//            assertThat(foundAsset).isEqualTo(asset2);
//        }
//
//        @Test
//        public void should_find_assets_by_title_containing_string() {
//            Category category = new Category(1,null,null,false,null);
//            entityManager.persist(category);
//            Asset asset1 = new  Asset(1,"a", null, false, null,category, Status.Available);
//            entityManager.persist(asset1);
//            Asset asset2 = new  Asset(2,"b", null, false, null,category, Status.Available);
//            entityManager.persist(asset2);
//            Asset asset3 = new Asset(3,"ab", null, false, null,category, Status.Available);
//            entityManager.persist(asset3);
//            Iterable assets = assetRepository.findByNameContaining("a");
//            assertThat(assets).hasSize(2).contains(asset1, asset3);
//        }
//
////        @Test
////        public void should_update_asset_by_id() {
////            Category category = new Category(1,null,null,false,null);
////            Asset asset1 = new Asset(1,"desk001", null, false, null,category, Status.Available);
////            entityManager.persist(asset1);
////            Asset asset2 = new Asset(2,"desk001", null, false, null,category, Status.Available);
////            entityManager.persist(asset2);
////            Asset updatedTut = new Asset(1,"updated", null, false, null,category, Status.Available);
////            Asset asset = repository.findById(asset2.getId()).get();
////            asset.setTitle(updatedTut.getTitle());
////            asset.setDescription(updatedTut.getDescription());
////            asset.setPublished(updatedTut.isPublished());
////            repository.save(asset);
////            Asset checkTut = repository.findById(asset2.getId()).get();
////
////            assertThat(checkTut.getId()).isEqualTo(asset2.getId());
////            assertThat(checkTut.getTitle()).isEqualTo(updatedTut.getTitle());
////            assertThat(checkTut.getDescription()).isEqualTo(updatedTut.getDescription());
////            assertThat(checkTut.isPublished()).isEqualTo(updatedTut.isPublished());
////        }
//        @Test
//        public void should_delete_asset_by_id() {
//            Category category = new Category(1,null,null,false,null);
//            entityManager.persist(category);
//            Asset asset1 = new  Asset(1,"a", null, false, null,category, Status.Available);
//            entityManager.persist(asset1);
//            Asset asset2 = new  Asset(2,"b", null, false, null,category, Status.Available);
//            entityManager.persist(asset2);
//            Asset asset3 = new Asset(3,"ab", null, false, null,category, Status.Available);
//            entityManager.persist(asset3);
//            assetRepository.deleteById(asset2.getId());
//            Iterable assets = assetRepository.findAll();
//            assertThat(assets).hasSize(2).contains(asset1, asset3);
//        }
//        @Test
//        public void should_delete_all_assets() {
//            Category category = new Category(1,null,null,false,null);
//            entityManager.persist(category);
//            Asset asset1 = new  Asset(1,"a", null, false, null,category, Status.Available);
//            entityManager.persist(asset1);
//            Asset asset2 = new  Asset(2,"b", null, false, null,category, Status.Available);
//            entityManager.persist(asset2);
//
//
//            assetRepository.deleteAll();
//            assertThat(assetRepository.findAll()).isEmpty();
//        }
//

