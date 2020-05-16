package com.rafael.projetomvc.services;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	public URI uploadFile(MultipartFile multipartfile) {
		try {
			String filename = multipartfile.getOriginalFilename();
			InputStream is = multipartfile.getInputStream();
			String contentType = multipartfile.getContentType();
			
			return uploadFile(is,filename,contentType);
			
		} catch (Exception e) {
			throw new RuntimeException("Erro de IO :" + e.getMessage());
		}
		
	}	
		
	
	
	public URI uploadFile(InputStream is , String fileMane, String contebtType){
		try {
			ObjectMetadata meta = new ObjectMetadata();
			LOG.info("Iniciando upload");
			s3Client.putObject(bucketName, fileMane, is, meta);
			LOG.info("Upload Finalizado");
		
			return s3Client.getUrl(bucketName, fileMane).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}

	
	}

}
