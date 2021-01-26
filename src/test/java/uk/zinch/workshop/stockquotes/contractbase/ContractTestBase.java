package uk.zinch.workshop.stockquotes.contractbase;

import uk.zinch.workshop.stockquotes.Quote;
import uk.zinch.workshop.stockquotes.QuoteGenerationService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractTestBase {
  @LocalServerPort
  private int port;

  @MockBean
  QuoteGenerationService service;


  @BeforeEach
  void setup() {
    RestAssured.baseURI = "http://localhost:" + port;

    when(service.fetchQuoteStream()).thenReturn(Flux.just(new Quote("TEST", 11.1), new Quote("TEST2", 0.1)));
  }
}
