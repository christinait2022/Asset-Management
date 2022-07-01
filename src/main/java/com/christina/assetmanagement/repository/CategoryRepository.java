package com.christina.assetmanagement.repository;

import com.christina.assetmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository
 *
 * @author Christina
 * @date 2022/07/01
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
