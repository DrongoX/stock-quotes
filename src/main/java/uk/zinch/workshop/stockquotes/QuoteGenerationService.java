package uk.zinch.workshop.stockquotes;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuoteGenerationService {

    private final MathContext mathContext = new MathContext(2);

    private final Random random = new Random();
    private final List<Quote> prices = List.of(
    		new Quote("CTXS", 82.26),
            new Quote("DELL", 63.74),
            new Quote("GOOG", 847.24),
            new Quote("MSFT", 65.11),
            new Quote("ORCL", 45.71),
            new Quote("RHT", 84.29),
            new Quote("VMW", 92.21));
    private final Flux<Quote> quoteStream;

    /**
     * Bootstraps the generator with tickers and initial prices
     */
    public QuoteGenerationService() {
        this.quoteStream = createQuoteStream();
    }

    public Flux<Quote> fetchQuoteStream() {
        return quoteStream;
    }


    private Flux<Quote> createQuoteStream() {
        return Flux.interval(Duration.ofMillis(200))
                .onBackpressureDrop()
                .flatMapIterable(this::generateQuotes)
                .share();
    }

    private List<Quote> generateQuotes(long i) {
        Instant instant = Instant.now();
        return prices.stream()
                .map(baseQuote -> {
                    BigDecimal priceChange = baseQuote.getPrice()
                            .multiply(BigDecimal.valueOf(0.05 * this.random.nextDouble()), this.mathContext);
                    Quote result = new Quote(baseQuote.getTicker(), baseQuote.getPrice().add(priceChange));
                    result.setInstant(instant);
                    return result;
                })
                .collect(Collectors.toList());
    }
}
