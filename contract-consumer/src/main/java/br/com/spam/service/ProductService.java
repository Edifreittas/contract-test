package br.com.spam.service;

import br.com.spam.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private WebClient webClient;

    public ProductDTO getProduct() {

        var product = webClient.post()
            .uri("/product")
            .retrieve()
            .bodyToMono(ProductDTO.class)
            .block();

        return product;
    }

}
