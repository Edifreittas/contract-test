package br.com.spam.contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import br.com.spam.contract.utils.ContractFiedUtils;
import br.com.spam.dto.ProductDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "contract-provider")
public class ConsumerContractTest {

    private final String CONSUMER_LIMITS_INFO = "/product";

    @Pact(consumer="contract-consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder){

        var dsl = ContractFiedUtils.getFields(ProductDTO.class);

        return builder
            .given("product POST - Success")
            .uponReceiving("product POST")
            .path(CONSUMER_LIMITS_INFO)
            .method("POST")
            .willRespondWith()
            .status(200)
            .body(
                dsl.build()
            )
            .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    void testGetLimits(MockServer mockServer) throws IOException {

        HttpResponse httpResponse = Request.Post(mockServer.getUrl() + CONSUMER_LIMITS_INFO)
            .bodyString("{}", ContentType.APPLICATION_JSON).execute().returnResponse();

        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
    }
}
