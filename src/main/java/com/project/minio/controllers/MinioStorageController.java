package com.project.minio.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.minio.MinioAdapter;
import com.project.minio.pojo.UploadImagePojo;

import io.minio.messages.Bucket;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class MinioStorageController {
	@Autowired
	private MinioAdapter minioAdapter;
	
	@Value("${minio.access.name}")
    private String accessKey;
    @Value("${minio.access.secret}")
    private String accessSecret;
    @Value("${minio.url}")
    private String minioUrl;

	@GetMapping(path = "/buckets")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "API to list all the buckets", description = "API will return the list of all buckets")
	public List<Bucket> listBuckets() {
		System.out.println("GET LIST OF BUCKETS");
		return minioAdapter.getAllBuckets();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "API to upload file", description = "API will upload the file")
	@PostMapping(path = "/upload/{bucketName}/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public UploadImagePojo uploadFile(@RequestPart(value = "file", required = false) MultipartFile files,
			@PathVariable String id, @PathVariable String bucketName) throws IOException {
		
		System.out.println("Start of Minio");
		minioAdapter.objectUpload(bucketName,id, id+files.getOriginalFilename(), files.getBytes());	
		UploadImagePojo uip = new UploadImagePojo();
		uip.setFileName(id+files.getOriginalFilename());
		uip.setId(id);
		uip.setPath(minioUrl+"/"+bucketName+"/"+id+"/"+files.getOriginalFilename());
		
		return uip;
	}


	@GetMapping(path = "/download/{bucketName}/{invTypeId}/{fileName}")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "API to download file", description = "API will download the file")
	public byte[] downloadFile(
			@PathVariable String bucketName, @PathVariable Integer invTypeId,@PathVariable String fileName)
			throws IOException {

		byte[] data = minioAdapter.objectDownload(bucketName,invTypeId,fileName);
		return data;

	}
	
	@DeleteMapping(path="/delete/{bucketName}/{invTypeId}/{fileName}")
	public void deleteFile(
			@PathVariable String bucketName, @PathVariable Integer invTypeId,@PathVariable String fileName)
			throws IOException {

		 minioAdapter.delete(bucketName,invTypeId,fileName);

	}



}
