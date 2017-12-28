package com.kunlun.cloudadminserver;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class CloudAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudAdminServerApplication.class, args);
	}
}
