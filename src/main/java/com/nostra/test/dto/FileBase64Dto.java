package com.nostra.test.dto;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileBase64Dto {

	private String base64;
	private String originalFilename;
	
	public InputStream getInputStream() {
		if(this.base64 == null) {
			return null;
		}
		return new ByteArrayInputStream(Base64.getDecoder().decode(this.base64));
	}
}
