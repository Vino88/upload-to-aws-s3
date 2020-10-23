package com.nostra.test.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nostra.test.component.HompageMovieSectionsComponent;
import com.nostra.test.dto.DetailDto;
import com.nostra.test.dto.ListMovieDto;
import com.nostra.test.dto.PostMovieDto;
import com.nostra.test.dto.Response;
import com.nostra.test.service.MovieService;
import com.nostra.test.util.MessageUtil;

import io.swagger.annotations.Api;

@Api(value = "API Nostra Movie", description = "REST Api Nostra Movie")
@RestController
@RequestMapping("/api/movie")
public class MovieApiController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private HompageMovieSectionsComponent hompageComponent;
	
	@GetMapping("/hompage")
	public Response<ListMovieDto> getListHompage(){
		return hompageComponent.getSectionsHompage();		
	}
	
	@GetMapping("/detail")
	public Response<DetailDto> getDetailMovie(@PathVariable("id") String id){
		DetailDto dto = new DetailDto();
		dto.setDetail(movieService.getDetailMovie(id));
		dto.setRecomended(movieService.getRecomendation());
		return new Response<DetailDto>(dto);
	}
	
	@PostMapping("/create")
	public ResponseEntity<MessageUtil> createOrUpdateMovie(PostMovieDto dto) throws IOException{
		MessageUtil message = null;
		if(dto != null) {
			movieService.createOrUpdateMovie(dto);
			message = new MessageUtil(200, "SUCCESS", "Movie Has been created");
			return new ResponseEntity<MessageUtil>(message, HttpStatus.OK);
		}else {
			message = new MessageUtil(400, "FAILED", "Bad Request!");
			return new ResponseEntity<MessageUtil>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MessageUtil> deleteMovieByid(@PathVariable("id") String id){
		MessageUtil message = null;
		if(id != null && id.trim().length() > 0) {
			movieService.delete(id);
			message = new MessageUtil(200, "SUCCESS", "Movie Has been deleted");
			return new ResponseEntity<MessageUtil>(message, HttpStatus.OK);
		}else {
			message = new MessageUtil(400, "FAILED", "Bad Request!");
			return new ResponseEntity<MessageUtil>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
