package br.com.spam.controller;


import br.com.spam.dto.ProductDTO;
import br.com.spam.service.ProductService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @Autowired
    private ProductService service;

    @GetMapping("/product")
    @SneakyThrows
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProduct() {

        log.trace("-------> TRACER");
        log.info( "-------> INFO");
        log.debug("-------> DEBUG");

        return service.getProduct();
    }

}
