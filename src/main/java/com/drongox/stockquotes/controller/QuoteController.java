package com.drongox.stockquotes.controller;

import com.drongox.stockquotes.Quote;
import com.drongox.stockquotes.QuoteGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteGenerationService quoteGenerationService;

    public Mono<ServerResponse> getQuotesStream(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(quoteGenerationService.fetchQuoteStream(), Quote.class);
    }
}
