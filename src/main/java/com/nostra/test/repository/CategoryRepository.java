package com.nostra.test.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nostra.test.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String>, JpaSpecificationExecutor<Category> {

}
