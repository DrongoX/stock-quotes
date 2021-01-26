package uk.zinch.workshop.stockquotes.controller;

import uk.zinch.workshop.stockquotes.Quote;
import uk.zinch.workshop.stockquotes.QuoteGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Controller
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteGenerationService quoteGenerationService;

    public Mono<ServerResponse> getQuotesStream(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_NDJSON)
                .body(quoteGenerationService.fetchQuoteStream(), Quote.class);
    }
}
