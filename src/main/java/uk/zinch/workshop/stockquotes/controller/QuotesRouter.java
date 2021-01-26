package uk.zinch.workshop.stockquotes.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuotesRouter {
    @Bean
    RouterFunction<ServerResponse> quotesRoutes(QuoteController quoteController) {
        return route()
            .GET(path("/quotes").and(accept(APPLICATION_NDJSON)), quoteController::getQuotesStream)
            .build();
    }
}
