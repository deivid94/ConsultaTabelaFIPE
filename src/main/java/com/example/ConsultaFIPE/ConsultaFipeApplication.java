package com.example.ConsultaFIPE;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.example.ConsultaFIPE.principal.Principal;
import com.example.ConsultaFIPE.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ConsultaFipeApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaFipeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal exibeMenu = new Principal();


		exibeMenu.exibeMenu();


	}


}
