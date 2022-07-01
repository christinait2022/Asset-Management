package com.christina.assetmanagement.repository;

import com.christina.assetmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * EmployeeRepository
 *
 * @author Christina
 * @date 2022/07/01
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
