package br.com.tqi.dio.queue;

import br.com.tqi.dio.dto.ImportPostalCodeDTO;
import br.com.tqi.dio.service.PostalCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PostalCodeConsumer {

    @Autowired
    private PostalCodeService postalCodeService;

    @SqsListener("${aws.import.cep.queue}")
    public void receiveMessage(ImportPostalCodeDTO message) {
        log.info("Receive message={}", message);
    }

}
