package com.nostra.test.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MOVIE")
public class Movie {

	@Id
	@ApiModelProperty(notes = "Id of the movie",name="id",required=true,value="test id")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name="id")
	private String id;
	
	@ApiModelProperty(notes = "Title of the movie",name="title",required=true,value="test title")
	private String title;
	
	@ApiModelProperty(notes = "Description of the Movie",name="description",required=true,value="test description")
	private String description;
	
	@ApiModelProperty(notes = "Author of the Movie",name="author",required=true,value="test author")
	private String author;
	
	@ApiModelProperty(notes = "Image url of the Movie",name="imageUrl",required=true,value="test imageUrl")
	@Column(name = "imageUrl")
	private String imageUrl;
	
	@ApiModelProperty(notes = "video url of the Movie",name="videoUrl",required=true,value="test videoUrl")
	@Column(name = "video_url")
	private String videoUrl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@ApiModelProperty(notes = "release date of the Movie",name="releaseDate",required=true,value="test releaseDate")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "release_date")
	private Date releaseDate;
	
	@ApiModelProperty(notes = "Rating of the Movie",name="rating",required=true,value="test rating")
	private Double rating;
	
	@ApiModelProperty(notes = "Category of the Movie",name="category",required=true,value="test category")
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
