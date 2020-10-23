package com.nostra.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nostra.test.component.SearchMovieComponent;
import com.nostra.test.dto.MovieSectionsDto;
import com.nostra.test.dto.Response;

@RestController
@RequestMapping("/api")
public class SearchControllerApi {

	@Autowired
	private SearchMovieComponent search;
	
	@GetMapping("/search")
	public Response<Page<MovieSectionsDto>> searchMovie(@RequestParam(name = "start", required = true) Integer start,
														@RequestParam(name = "length", required = true) Integer length,
														@RequestParam(name = "keyword", required = false) String keyword){
		
		return search.searchMovie(start, length, keyword);
	}
}
