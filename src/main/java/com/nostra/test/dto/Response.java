package com.nostra.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
	public static final int SUCCESS = 200;
	public static final int BAD_REQUEST = 400;
	
	public Response(){}
	public Response(T object)
	{
		this.status = Response.SUCCESS;
		this.result = object;
	}
	private int status;
	private T result;
	private String message;
	
	public void setBadRequest(){
		this.status = Response.BAD_REQUEST;
	}
	public void setBadRequest(String message){
		this.status = Response.BAD_REQUEST;
		this.message = message;
	}
}
