package com.rutikpatel.microservice.cureecncyexchangeservice;

import com.google.common.annotations.GwtCompatible;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name="default",fallbackMethod = "hardCodedResponse")
//    @CircuitBreaker(name="default",fallbackMethod = "hardCodedResponse")
    @Bulkhead(name = "default")
//    @RateLimiter(name="default",fallbackMethod = "hardCodedResponse")
    public String sampleApi(){
        logger.info("sample-api-call-received");
//        ResponseEntity<String> forEntity=  new RestTemplate().getForEntity("localhost/some-dummy",String.class);
//        return forEntity.getBody();
        return "sample-api";
    }


    public String hardCodedResponse(Exception ex){
        return "Fall back response";
    }
}
