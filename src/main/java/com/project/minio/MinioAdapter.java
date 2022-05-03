package com.project.minio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import com.project.minio.exceptions.NotFoundException;
import com.project.minio.exceptions.ServiceUnavailableException;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.NoResponseException;
import io.minio.messages.Bucket;

@Service
public class MinioAdapter {

    @Autowired
    private MinioClient minioClient;
    
    
    @Value("${minio.default.folder}")
    private String defaultBaseFolder;
    
    @Autowired
    private MessageSource messageSource;
    
    private static final String MINIO = "minio";
    private static final String DATA = "data";

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioAdapter.class);

   
    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            LOGGER.info("Exception in getAllBuckets() ");
            LOGGER.info(e.getMessage());
            throw new ServiceUnavailableException(MINIO, messageSource);
        }
    }
    
    @SuppressWarnings("deprecation")
   	public void objectUpload(String bucketName,Integer invTypeId,String objectName, byte[] imageBuffer) {
    		
    	if(null==objectName || null==imageBuffer) {
    		throw new NotFoundException(DATA, messageSource);
    	}
    	
    	try {
    		LOGGER.info("bucketName : [{}]", bucketName);
    		LOGGER.info("defaultBaseFolder : [{}]", defaultBaseFolder);

    		if(!minioClient.bucketExists(bucketName)) {
    			minioClient.makeBucket(bucketName);
            }
    		
            minioClient.putObject(bucketName, invTypeId+"/"+objectName, new ByteArrayInputStream(imageBuffer), 
              		imageBuffer.length, "application/octet-stream");
           	} catch(Exception e) {
               LOGGER.info("Exception in objectUpload() ");
               LOGGER.info(e.getMessage());
               throw new ServiceUnavailableException(MINIO, messageSource);
        }
     }

    public byte[] objectDownload(String bucketName,Integer invTypeId,String fileName) {
    	

    	
        try {
        	
            InputStream obj = minioClient.getObject(bucketName, "/"+invTypeId+"/"+fileName);
        	
            byte[] content = IOUtils.toByteArray(obj);
        	LOGGER.info("content : [{}]", content);
            obj.close();
            return content;
        } catch (Exception e) {
            LOGGER.info("Exception in objectDownload() ");
            LOGGER.info(e.getMessage());
            throw new ServiceUnavailableException(MINIO, messageSource);
        }
    }
    
    public void delete(String bucketName,Integer invTypeId,String fileName) {
            
				try {
					minioClient.removeObject(bucketName,invTypeId+"/"+fileName);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			

    }

   
}