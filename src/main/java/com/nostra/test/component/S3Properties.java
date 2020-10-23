package com.nostra.test.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class S3Properties {

	@Value("${s3.bucket}")
	private String bucket;
	@Value("${s3.folder}")
	private String folder;
	@Value("${s3.url}")
	private String url;
}
