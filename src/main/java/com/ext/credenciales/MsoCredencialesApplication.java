package com.ext.credenciales;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.efectivale.*")
public class MsoCredencialesApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MsoCredencialesApplication.class);
		Map<String,Object> map = new HashMap<>();
		map.put("server.port", "8085");
		map.put("server.servlet.context-path", "/efvServ");
		map.put("server.error.whitelabel.enabled", false);
		app.setDefaultProperties(map);
		app.run(args);
	}

}
