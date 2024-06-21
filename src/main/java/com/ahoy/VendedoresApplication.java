package com.ahoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@Import(DotenvConfig.class)
public class VendedoresApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		// Log para verificar se as variáveis estão sendo carregadas
        System.out.println("DATABASE_URL: " + dotenv.get("DATABASE_URL"));
        System.out.println("DATABASE_USERNAME: " + dotenv.get("DATABASE_USERNAME"));
        System.out.println("DATABASE_PASSWORD: " + dotenv.get("DATABASE_PASSWORD"));
		
		SpringApplication.run(VendedoresApplication.class, args);
	}

}
