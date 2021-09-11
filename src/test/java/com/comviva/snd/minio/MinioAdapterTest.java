package com.comviva.snd.minio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import com.project.minio.MinioAdapter;
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

class MinioAdapterTest {
	
	@InjectMocks
	private MinioAdapter minioAdapter;
	
	@Mock
	private MinioClient minioClient;
	
	@Mock
	private MessageSource messageSource;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("test getAllBuckets without minIO availablity")
	void test1() throws IOException {
		try {
			Mockito.when(minioClient.listBuckets()).thenThrow(IOException.class);
		} catch (InvalidKeyException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException
				| NoResponseException | ErrorResponseException | InternalException | IOException
				| XmlPullParserException e) {
		}

		Assertions.assertThrows(ServiceUnavailableException.class, 
				() -> this.minioAdapter.getAllBuckets());
	}
	
	@Test
	@DisplayName("test getAllBuckets")
	void test2() {
		try {
			Mockito.when(minioClient.listBuckets()).thenReturn(new ArrayList<>());
		} catch (InvalidKeyException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException
				| NoResponseException | ErrorResponseException | InternalException | IOException
				| XmlPullParserException e) {
		}
		List<Bucket> list = this.minioAdapter.getAllBuckets();
		Assertions.assertNotNull(list);
	}
	
//	@Test
//	@DisplayName("test objectDownload without minIO availablity")
//	void test3() {
//		String key = "key";
//		Assertions.assertThrows(ServiceUnavailableException.class, 
//				() -> this.minioAdapter.objectDownload(key));
//	}
//	
//	@Test
//	@DisplayName("test objectDownload")
//	void test4() throws InvalidArgumentException, FileNotFoundException, IOException {
//		
//		String key = "key";
//		InputStream is = IOUtils.toInputStream("any String for Testing");
//		
//		try {
//			Mockito.when(minioClient.getObject(null, null + key)).thenReturn(is);
//		} catch (InvalidKeyException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException
//				| NoResponseException | ErrorResponseException | InternalException | IOException
//				| XmlPullParserException e) {
//		}
//
//		Assertions.assertDoesNotThrow(() -> this.minioAdapter.objectDownload(key));
//	}

//	@Test
//	@DisplayName("test objectUpload without minIO availablity")
//	void test5() throws InvalidArgumentException, IOException {
//		String fileDirectory = "C:\\Users\\mohit.tanwar.COMVIVA\\Desktop\\MohitTanwar\\MicroServices\\"
//				+ "PdfGenerator\\created\\GRNReceipt.pdf";
//		File file1 = new File(fileDirectory);
//		FileInputStream input = new FileInputStream(file1);
//	
//		
//		MultipartFile multipartFile = new MockMultipartFile("file",
//	            file1.getName(), "multipart/form-data", IOUtils.toByteArray(input));
//
//		Assertions.assertDoesNotThrow(() -> this.minioAdapter.objectUpload(
//				multipartFile.getOriginalFilename(), multipartFile.getBytes()));
//	}
//	
//	@SuppressWarnings("deprecation")
//	@Test
//	@DisplayName("test objectUpload")
//	void test6() throws InvalidArgumentException, IOException {
//		String fileDirectory = "C:\\Users\\mohit.tanwar.COMVIVA\\Desktop\\MohitTanwar\\MicroServices\\"
//				+ "PdfGenerator\\created\\testFile.txt";
//		File file1 = new File(fileDirectory);
//		FileInputStream input = new FileInputStream(file1);
//	
//		
//		MultipartFile multipartFile = new MockMultipartFile("file",
//	            file1.getName(), "multipart/form-data", IOUtils.toByteArray(input));
//		
//		String objectName = multipartFile.getOriginalFilename();
//		byte[] imageBuffer = multipartFile.getBytes();
//
//		try {
//			Mockito.doThrow(new IOException()).when(minioClient).putObject(Mockito.any(), 
//					Mockito.anyString(), Mockito.anyObject(), Mockito.anyLong(), Mockito.anyString());
//		} catch (InvalidKeyException | InvalidBucketNameException | NoSuchAlgorithmException | NoResponseException
//				| ErrorResponseException | InternalException | InvalidArgumentException | InsufficientDataException
//				| IOException | XmlPullParserException e) {
//		}
//		
//		Assertions.assertThrows(ServiceUnavailableException.class, 
//				() -> this.minioAdapter.objectUpload(objectName, imageBuffer));
//	}
//	
//	@Test
//	@DisplayName("test objectDownload With Null Key")
//	void test7() {
//		Assertions.assertThrows(NotFoundException.class, 
//				() -> this.minioAdapter.objectDownload(null));
//	}
//	
//	@Test
//	@DisplayName("test objectUpload With Null Payload")
//	void test8() {
//		Assertions.assertThrows(NotFoundException.class, () -> this.minioAdapter.objectUpload(
//				null, null));
//	}
//	
//	@Test
//	@DisplayName("test objectUpload With Null Payload")
//	void test9() {
//		Assertions.assertThrows(NotFoundException.class, () -> this.minioAdapter.objectUpload(
//				"", null));
//	}
//
//	@Test
//	@DisplayName("test objectUpload without minIO availablity")
//	void test10() throws InvalidArgumentException, IOException {
//		String fileDirectory = "C:\\Users\\mohit.tanwar.COMVIVA\\Desktop\\MohitTanwar\\MicroServices\\"
//				+ "PdfGenerator\\created\\GRNReceipt.pdf";
//		File file1 = new File(fileDirectory);
//		FileInputStream input = new FileInputStream(file1);
//			
//		MultipartFile multipartFile = new MockMultipartFile("file",
//	            file1.getName(), "multipart/form-data", IOUtils.toByteArray(input));
//
//		try {
//			Mockito.when(minioClient.bucketExists(null)).thenReturn(true);
//		} catch (InvalidKeyException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException
//				| NoResponseException | ErrorResponseException | InternalException | IOException
//				| XmlPullParserException e) {
//		}
//
//		Assertions.assertDoesNotThrow(() -> this.minioAdapter.objectUpload(
//				multipartFile.getOriginalFilename(), multipartFile.getBytes()));
//	}
	
}
