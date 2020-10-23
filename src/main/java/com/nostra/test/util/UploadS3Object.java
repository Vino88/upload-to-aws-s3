package com.nostra.test.util;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

public class UploadS3Object {

	public void upload(AmazonS3 s3Client, String bucketName, String fileObjKeyName, String fileName, InputStream is) throws IOException {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.addUserMetadata("x-amz-meta-title", fileName);

			// Upload a file as a new object with ContentType and title specified.
			PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, is, metadata);

			PutObjectResult result = s3Client.putObject(request);
			System.out.println("bucketName " + bucketName);
			System.out.println("upload " + fileObjKeyName);
			System.out.println("result " + result);

		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
	}
	
	public void upload(AmazonS3 s3Client, String bucketName, String fileObjKeyName, String fileName, InputStream is, long contentLength) throws IOException {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.addUserMetadata("x-amz-meta-title", fileName);
			metadata.setContentLength(contentLength);

			// Upload a file as a new object with ContentType and title specified.
			PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, is, metadata);

			PutObjectResult result = s3Client.putObject(request);
			System.out.println("bucketName " + bucketName);
			System.out.println("upload " + fileObjKeyName);
			System.out.println("result " + result);

		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
	}
}
