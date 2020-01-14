package com.drongox.stockquotes.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuotesRouter {
    @Bean
    RouterFunction<ServerResponse> quotesRoutes(QuoteController quoteController) {
        return route(GET("/quotes").and(accept(MediaType.APPLICATION_STREAM_JSON)),
                quoteController::getQuotesStream);
    }
}
