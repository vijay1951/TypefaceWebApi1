package com.webapi.Folder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@ComponentScan(basePackageClasses=folder_repo.class)
public class FolderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FolderApplication.class, args);
	}

	
}
