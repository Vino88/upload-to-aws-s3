package com.nostra.test.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListMovieDto {

	private List<MovieSectionsDto> headers;
	private List<MovieSectionsDto> myMovie;
	private List<MovieSectionsDto> newMovie;
}
