package ru.itm.restapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApp implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(RestApp.class, args);
	}
	
	@Override
	public void run(String... args) {
		System.out.println("Ура! Заработало!");
	}
}
