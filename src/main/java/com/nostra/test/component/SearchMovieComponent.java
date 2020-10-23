package com.nostra.test.component;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.nostra.test.dto.MovieSectionsDto;
import com.nostra.test.dto.Response;
import com.nostra.test.entity.Movie;
import com.nostra.test.repository.MovieRepository;

@Component
public class SearchMovieComponent {

	@Autowired
	private MovieRepository movieRepository;
	
	public Response<Page<MovieSectionsDto>> searchMovie(Integer start, Integer length, String keyword){
		Specification<Movie> spec = new Specification<Movie>() {
			public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate search = null;

				if (keyword != null && keyword.trim().length() > 0) {
					Predicate search1 = builder.like(builder.lower(root.get("title")),
							"%" + keyword.toLowerCase() + "%");
					Predicate search2 = builder.like(builder.lower(root.get("author")),
							"%" + keyword.toLowerCase() + "%");
					
					search = builder.or(search1, search2);
				}

				return search;
			}
		};

		PageRequest pageable = new PageRequest(start, length, new Sort(Direction.ASC,"title"));
		Page<Movie> pageMovie = movieRepository.findAll(spec, pageable);
		
		MovieSectionsDto dto = null;
		List<MovieSectionsDto> content = new ArrayList<MovieSectionsDto>();
		
		for(Movie m : pageMovie.getContent()) {
			dto = new MovieSectionsDto();
			dto.setId(m.getId());
			dto.setTitle(m.getTitle());
			dto.setAuthor(m.getTitle());
			dto.setImageUrl(m.getImageUrl());
			content.add(dto);
		}
		
		Page<MovieSectionsDto> pageDto = new PageImpl<MovieSectionsDto>(content, pageable, pageMovie.getTotalElements());
		
		return new Response<Page<MovieSectionsDto>>(pageDto);
	}
}
