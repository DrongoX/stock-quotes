package uk.zinch.workshop.stockquotes;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class QuoteGenerationServiceTest {

    @Test
    void should_generate_flux_of_quotes() {
        //given
        QuoteGenerationService quoteGenerationService = new QuoteGenerationService();
        //when
        Flux<Quote> quoteStream = quoteGenerationService.fetchQuoteStream();
        //then
        StepVerifier.create(quoteStream)
                .recordWith(ArrayList::new)
                .expectNextCount(20)
                .expectRecordedMatches(list -> list.stream().allMatch(quote -> quote.getPrice().doubleValue() > 0))
                .consumeRecordedWith(list -> assertThat(list).allMatch(quote -> quote.getPrice().doubleValue() > 0))
                .thenCancel()
                .verify();
    }
}
