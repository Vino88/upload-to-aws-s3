package com.nostra.test.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostMovieDto {

	private String id;
	private String title;
	private String description;
	private String author;
	private MultipartFile imageFile;
	private MultipartFile videoFile;
	private KeyValueDto category;
	private Date releaseDate;
}
