package br.com.tqi.dio.controller;

import br.com.tqi.dio.integration.ExternalIntegration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/integration")
@Slf4j
public class IntegrationController {

	private final ExternalIntegration externalIntegration;

	@GetMapping
	public Map find() throws JsonProcessingException {
		log.info("find integration");
		return new ObjectMapper().readValue(externalIntegration.find(), Map.class);
	}

}
