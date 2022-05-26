package br.com.tqi.dio.controller;

import br.com.tqi.dio.domain.Address;
import br.com.tqi.dio.dto.PostalCodeDTO;
import br.com.tqi.dio.service.PostalCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postal-code")
@Slf4j
public class PostalCodeController {

	private final PostalCodeService postalCodeService;

	@GetMapping("/{postalCode}")
	public Address findByPostalCode(@PathVariable String postalCode) {
		log.info("find postalCode={}", postalCode);
		return postalCodeService.findByPostalCode(postalCode);
	}

	@PostMapping
	public void saveAddressData(@RequestBody PostalCodeDTO postalCodeDTO) {
		log.info("try add address={}", postalCodeDTO);
		postalCodeService.saveAddressDataFromRest(postalCodeDTO);
	}

	@GetMapping("/{postalCode}/{delayMilliseconds}")
	public Address findByPostalCodeWithDelay(@PathVariable String postalCode, @PathVariable Long delayMilliseconds) throws InterruptedException {
		log.info("find postalCode={} delayMilliseconds={}", postalCode, delayMilliseconds);
		TimeUnit.MILLISECONDS.sleep(delayMilliseconds);
		return postalCodeService.findByPostalCode(postalCode);
	}

	@GetMapping("/build-error")
	public void buildError() {
		throw new RuntimeException("Build error from api user");
	}

}
