package com.nostra.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.nostra.test.component.UploadS3Util;
import com.nostra.test.dto.DetailMovieDto;
import com.nostra.test.dto.MovieSectionsDto;
import com.nostra.test.dto.PostMovieDto;
import com.nostra.test.entity.Category;
import com.nostra.test.entity.Movie;
import com.nostra.test.repository.CategoryRepository;
import com.nostra.test.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UploadS3Util uploadS3Util;
	
	public void createOrUpdateMovie(PostMovieDto dto) {
		String fileKeyResponse = null;
		Movie m = null;
		if(dto.getId() != null) {
			m =movieRepository.findOne(dto.getId());
			m.setTitle(dto.getTitle());
			m.setDescription(dto.getDescription());
			m.setAuthor(dto.getAuthor());
			m.setRating(0.0);
			m.setUpdatedAt(new Date());
			
			if(dto.getCategory() != null && dto.getCategory().getId() != null) {
				Category category = categoryRepository.findOne(dto.getCategory().getId());
				if(category != null && category.getCategoryId() != null) {
					m.setCategory(category);
				}
			}
			
			if(dto.getImageFile() != null) {
				String fileObjKeyName = UUID.randomUUID().toString() + dto.getImageFile().getOriginalFilename( );
				fileObjKeyName = fileObjKeyName.replaceAll(" ", "");
				try {
					fileKeyResponse = uploadS3Util.upload(fileObjKeyName, dto.getImageFile().getOriginalFilename(), dto.getImageFile().getInputStream());
					m.setImageUrl(uploadS3Util.getUrl()+fileKeyResponse);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(dto.getVideoFile() != null) {
				String fileObjKeyName = UUID.randomUUID().toString() + dto.getVideoFile().getOriginalFilename();
				fileObjKeyName = fileObjKeyName.replaceAll(" ", "");
				try {
					fileKeyResponse = uploadS3Util.upload(fileObjKeyName, dto.getVideoFile().getOriginalFilename(), dto.getVideoFile().getInputStream());
					m.setVideoUrl(uploadS3Util.getUrl()+fileKeyResponse);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			movieRepository.save(m);
			
		}else {
			m = new Movie();
			m.setTitle(dto.getTitle());
			m.setDescription(dto.getDescription());
			m.setAuthor(dto.getAuthor());
			m.setCreatedAt(new Date());
			m.setReleaseDate(dto.getReleaseDate());
			
			if(dto.getCategory() != null && dto.getCategory().getId() != null) {
				Category category = categoryRepository.findOne(dto.getCategory().getId());
				if(category != null) {
					m.setCategory(category);
				}
			}
			
			if(dto.getImageFile() != null) {
				String fileObjKeyName = UUID.randomUUID().toString() + dto.getImageFile().getOriginalFilename();
				fileObjKeyName = fileObjKeyName.replaceAll(" ", "");
				try {
					fileKeyResponse = uploadS3Util.upload(fileObjKeyName, dto.getImageFile().getOriginalFilename(), dto.getImageFile().getInputStream());
					m.setImageUrl(uploadS3Util.getUrl()+fileKeyResponse);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(dto.getVideoFile() != null) {
				String fileObjKeyName = UUID.randomUUID().toString() + dto.getVideoFile().getOriginalFilename();
				fileObjKeyName = fileObjKeyName.replaceAll(" ", "");
				try {
					fileKeyResponse = uploadS3Util.upload(fileObjKeyName, dto.getVideoFile().getOriginalFilename(), dto.getVideoFile().getInputStream());
					m.setVideoUrl(uploadS3Util.getUrl()+fileKeyResponse);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			movieRepository.save(m);
		}
	}

	@Override
	public DetailMovieDto getDetailMovie(String id) {
		Specification<Movie> spec= new Specification<Movie>() {
			public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
 
            	return builder.and(builder.equal(root.get("id"), id));
              }
         };
		
        Movie m = movieRepository.findOne(spec);
        DetailMovieDto dto = new DetailMovieDto();
        dto.setId(m.getId());
        dto.setTitle(m.getTitle());
        dto.setDescription(m.getDescription());
        dto.setAuthor(m.getAuthor());
        dto.setImageUrl(m.getImageUrl());
        dto.setVideoUrl(m.getVideoUrl());
        dto.setRating(m.getRating());
		return dto;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		movieRepository.delete(id);
	}

	@Override
	public List<MovieSectionsDto> getRecomendation() {
		Double rate = 4.0;
		Specification<Movie> spec= new Specification<Movie>() {
			public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
 
            	return builder.and(builder.greaterThanOrEqualTo(root.get("rating"), rate));
              }
         };
		
         List<Movie> listMovie = movieRepository.findAll(spec);
         MovieSectionsDto dto = null;
         List<MovieSectionsDto> listing = new ArrayList<MovieSectionsDto>();

         for(Movie m : listMovie) {
        	 dto = new MovieSectionsDto();
        	 dto.setId(m.getId());
        	 dto.setTitle(m.getTitle());
        	 dto.setAuthor(m.getAuthor());
        	 dto.setImageUrl(m.getImageUrl());
        	 listing.add(dto);
         }
         
		return listing;
	}
}
