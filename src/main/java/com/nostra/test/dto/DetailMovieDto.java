package com.nostra.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailMovieDto {

	private String id;
	private String title;
	private String description;
	private String author;
	private String imageUrl;
	private String videoUrl;
	private Double rating;
}
