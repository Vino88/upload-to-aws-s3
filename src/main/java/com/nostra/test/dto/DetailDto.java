package com.nostra.test.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailDto {

	private DetailMovieDto detail;
	private List<MovieSectionsDto> recomended;
}
