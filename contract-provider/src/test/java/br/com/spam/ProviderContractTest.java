package br.com.spam;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.VerificationReports;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import br.com.spam.dto.ProductDTO;
import br.com.spam.service.ProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@PactFolder("pacts")
@Provider("contract-provider")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProviderContractTest {

    @MockBean
    private ProviderService service;

    @LocalServerPort
    int localServerPort;

    @BeforeEach
    void setUp(PactVerificationContext context) {
        if (context != null) {
            context.setTarget(new HttpTestTarget("localhost", localServerPort, "/"));
        }
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    public void pactVerificationTest(PactVerificationContext context) {
        if (context != null) {
            context.verifyInteraction();
        }
    }

    @State("product POST - Success")
    public void test() {

        var date = LocalDate.of(2023, 3, 14);
        when(service.getProduct(any())).thenReturn(ProductDTO
            .builder()
                .id("String")
                .price(BigDecimal.ONE)
                .valid(Boolean.TRUE)
                .date(date)
            .build());

    }

}
