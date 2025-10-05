package com.smartcity.issue;

import com.smartcity.models.IssueStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@SpringBootApplication
@EnableR2dbcAuditing
@EnableConfigurationProperties
public class IssueApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueApplication.class, args);
	}
}
