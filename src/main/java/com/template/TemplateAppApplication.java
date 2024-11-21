package com.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import com.template.twilioClass.TwilioProperties;

//whatsup config property enable
//@EnableConfigurationProperties(TwilioProperties.class)
@SpringBootApplication(scanBasePackages = "com.template")
@EnableJpaRepositories(basePackages = "com.template.repository")
public class TemplateAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateAppApplication.class, args);
	}
	

}
