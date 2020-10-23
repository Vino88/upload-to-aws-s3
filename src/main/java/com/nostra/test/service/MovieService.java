package com.nostra.test.service;

import java.util.List;

import com.nostra.test.dto.DetailMovieDto;
import com.nostra.test.dto.MovieSectionsDto;
import com.nostra.test.dto.PostMovieDto;

public interface MovieService {
	
	public void createOrUpdateMovie(PostMovieDto dto);
	public DetailMovieDto getDetailMovie(String id);
	public List<MovieSectionsDto> getRecomendation(); 
	public void delete(String id);
}
