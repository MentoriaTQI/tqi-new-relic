package br.com.tqi.dio.queue;

import br.com.tqi.dio.dto.PostalCodeDTO;
import br.com.tqi.dio.service.PostalCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostalCodeConsumer {

    private final PostalCodeService postalCodeService;

    @SqsListener("${aws.import.cep.queue}")
    public void receiveMessage(PostalCodeDTO message) {
        log.info("Receive message={}", message);
        postalCodeService.saveAddressDataFromQueue(message);
    }

}
