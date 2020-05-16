package com.rafael.projetomvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoMvcApplication implements CommandLineRunner {
	
	//@Autowired
	//private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			//s3Service.uploadFile("C:\\temp\\fotos\\download.jpg");
	}

}
