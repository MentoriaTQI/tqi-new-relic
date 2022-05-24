package br.com.tqi.dio.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postal-code")
@Slf4j
public class PostalCodeController {

	@GetMapping
	public String welcome() {
		return "WELCOME";
	}

	@PostMapping("/send/{email}")
	public void sendMail(@PathVariable String email) {
		log.info("Sending email to={}", email);
	}

}
