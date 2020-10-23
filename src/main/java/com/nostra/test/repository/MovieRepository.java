package com.nostra.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.nostra.test.entity.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, String>, JpaSpecificationExecutor<Movie>{

	@Query(nativeQuery = true, value = "SELECT * FROM movie WHERE category_id =:categoryId")
	List<Movie> findByCategoryId(@Param("categoryId") String categoryId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM movie ORDER BY release_date DESC LIMIT 5")
	List<Movie> findByNewRealease();
}
