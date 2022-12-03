package org.polytech.covidapi.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class RateLimitService {
    private final Bucket bucket = Bucket.builder().addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)))).build();

    public<T> Optional<ResponseEntity<T>> tryConsume() {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (!probe.isConsumed()) {
            return Optional.of(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .header("X-Rate-Limit-Retry-After-Seconds", String.valueOf(probe.getNanosToWaitForRefill() / 1_000_000))
                    .build());
        }
        return Optional.empty();
    }
}
