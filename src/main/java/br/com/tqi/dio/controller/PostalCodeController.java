package br.com.tqi.dio.controller;

import br.com.tqi.dio.domain.Address;
import br.com.tqi.dio.integration.ExternalIntegration;
import br.com.tqi.dio.service.PostalCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postal-code")
@Slf4j
public class PostalCodeController {

	private final ExternalIntegration externalIntegration;
	private final PostalCodeService postalCodeService;

	@GetMapping("/local/{postalCode}")
	public Address find(@PathVariable String postalCode) {
		log.info("find postalCode={}", postalCode);
		return postalCodeService.find(postalCode);
	}

	@GetMapping("/cache/{postalCode}")
	public String findInCache(@PathVariable String postalCode) {
		log.info("findInCache postalCode={}", postalCode);
		return "WELCOME";
	}

}
