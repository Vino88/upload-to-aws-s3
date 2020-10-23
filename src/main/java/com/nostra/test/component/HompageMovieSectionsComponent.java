package com.nostra.test.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nostra.test.dto.ListMovieDto;
import com.nostra.test.dto.MovieSectionsDto;
import com.nostra.test.dto.Response;
import com.nostra.test.entity.Movie;
import com.nostra.test.repository.MovieRepository;

@Component
public class HompageMovieSectionsComponent {

	@Autowired
	private MovieRepository movieRepository;
	
	private List<MovieSectionsDto> getHeader(String categoryId){
		MovieSectionsDto dto = null;
		List<MovieSectionsDto> listDto = new ArrayList<MovieSectionsDto>();
		List<Movie> listMovie = movieRepository.findByCategoryId(categoryId);
		for(Movie m : listMovie) {
			dto = new MovieSectionsDto();
			dto.setId(m.getId());
			dto.setTitle(m.getTitle());
			dto.setAuthor(m.getAuthor());
			dto.setImageUrl(m.getImageUrl());
			listDto.add(dto);
		}
		
		return listDto;
	}
	
	private List<MovieSectionsDto> getMyMovie(String categoryId){
		MovieSectionsDto dto = null;
		List<MovieSectionsDto> listDto = new ArrayList<MovieSectionsDto>();
		List<Movie> listMovie = movieRepository.findByCategoryId(categoryId);
		for(Movie m : listMovie) {
			dto = new MovieSectionsDto();
			dto.setId(m.getId());
			dto.setTitle(m.getTitle());
			dto.setAuthor(m.getAuthor());
			dto.setImageUrl(m.getImageUrl());
			listDto.add(dto);
		}
		
		return listDto;
	}
	
	private List<MovieSectionsDto> getNewMovie(){
		MovieSectionsDto dto = null;
		List<MovieSectionsDto> listDto = new ArrayList<MovieSectionsDto>();
		List<Movie> listMovie = movieRepository.findByNewRealease();
		for(Movie m : listMovie) {
			dto = new MovieSectionsDto();
			dto.setId(m.getId());
			dto.setTitle(m.getTitle());
			dto.setAuthor(m.getAuthor());
			dto.setImageUrl(m.getImageUrl());
			listDto.add(dto);
		}
		
		return listDto;
	}
	
	public Response<ListMovieDto> getSectionsHompage(){
		String headerCategory = "H001";
		String myMovieCategory = "M001";
		
		ListMovieDto dto = new ListMovieDto();
		dto.setHeaders(getHeader(headerCategory));
		dto.setMyMovie(getMyMovie(myMovieCategory));
		dto.setNewMovie(getNewMovie());
		
		return new Response<ListMovieDto>(dto);		
	}
}
