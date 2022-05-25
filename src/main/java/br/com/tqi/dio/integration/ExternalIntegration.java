package br.com.tqi.dio.integration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExternalIntegration {

    @Value("${url.external}")
    private String rootUri;

    private final WebClient webClient;

    public String find() {
        return webClient
                .get()
                .uri(rootUri)
                .header("X-CSRF-TOKEN", "wcxk8gNQc9LMeK5RsN3zl16hTGerNp0yXLVqiy7h")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
