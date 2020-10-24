package com.nostra.test.component;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.nostra.test.util.UploadS3Object;


@Component
public class UploadS3Util {
	@Autowired
	private S3Properties s3Properties;
	private AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
	
	public String upload(String fileObjKeyName, String fileName, InputStream is) throws IOException {
		UploadS3Object o = new UploadS3Object();
		o.upload(s3Client, s3Properties.getBucket(),s3Properties.getFolder()+"/"+ fileObjKeyName, fileName, is);
		return s3Properties.getFolder()+"/"+ fileObjKeyName;
	}
	
	public String getUrl() {
		return s3Properties.getUrl();
	}
	
	public S3Object download(String key) {
		S3Object s3Object = s3Client.getObject(s3Properties.getBucket(), key);
		return s3Object;
	}
	
	
	public void delete(String fileObjKeyName) {
        try {
           
            s3Client.deleteObject(new DeleteObjectRequest(s3Properties.getBucket(), fileObjKeyName));
        }
        catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process 
            // it, so it returned an error response.
            e.printStackTrace();
        }
        catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
	}
}
