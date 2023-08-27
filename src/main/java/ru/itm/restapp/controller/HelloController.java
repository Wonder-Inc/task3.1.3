package ru.itm.restapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

	@GetMapping(value = "/")
	public List<String> printWelcome() {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Rest Application");
		messages.add("1.0.0 version by aug'23 ");
		return messages;
	}
}