package com.madskchristensen.springexerciseconsumingrest.service;

import com.madskchristensen.springexerciseconsumingrest.model.catfact.CatFact;
import com.madskchristensen.springexerciseconsumingrest.model.quote.Quote;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestConsumerService {
    final RestTemplate restTemplate;
    private final String catUrl;
    private final String quoteUrl;

    public RestConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.catUrl = "http://cat-fact.herokuapp.com/facts/random";
        this.quoteUrl = "https://gturnquist-quoters.cfapps.io/api/random";
    }

    private CatFact fetchRandomCatFact() {
        return restTemplate.getForObject(getQueryUrl(catUrl), CatFact.class);
    }

    public CatFact fetchRandomValidCatFact() {
        CatFact catFact = fetchRandomCatFact();

        while(!catFact.getStatus().getVerified()) {
            catFact = fetchRandomCatFact();
        }

        return catFact;
    }

    private String getQueryUrl(String urlType) {
        return UriComponentsBuilder
                .fromUriString(urlType)
                .toUriString();
    }

    public Quote fetchSingleQuote() {

        return restTemplate.getForObject(getQueryUrl(quoteUrl), Quote.class);
    }
}