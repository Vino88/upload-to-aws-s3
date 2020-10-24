package com.nostra.test.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostMovieDto {

	private String id;
	private String title;
	private String description;
	private String author;
	private FileBase64Dto imageFile;
	private FileBase64Dto videoFile;
	private KeyValueDto category;
	private Date releaseDate;
}
