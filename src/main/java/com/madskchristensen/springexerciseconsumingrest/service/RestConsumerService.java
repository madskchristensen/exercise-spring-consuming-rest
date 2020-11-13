package com.madskchristensen.springexerciseconsumingrest.service;

import com.madskchristensen.springexerciseconsumingrest.model.catfact.CatFact;
import com.madskchristensen.springexerciseconsumingrest.model.quote.Quote;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestConsumerService {
    private final RestTemplate restTemplate;
    private final Map<String, URL> urlMap = new HashMap<>();

    public RestConsumerService(RestTemplate restTemplate) throws MalformedURLException {
        this.restTemplate = restTemplate;
        populateUrlMap();
    }

    private void populateUrlMap() throws MalformedURLException {
        this.urlMap.put("catFact", new URL("http://cat-fact.herokuapp.com/facts"));
        this.urlMap.put("quote", new URL("https://gturnquist-quoters.cfapps.io/api"));
    }

    private String getUrlAsStringForModel(String model) {
        return this.urlMap.get(model).toString();
    }

    private CatFact fetchRandomCatFact() {
        return restTemplate.getForObject(getQueryUrl(getUrlAsStringForModel("catFact") + "/random"), CatFact.class);
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

        return restTemplate.getForObject(getQueryUrl(getUrlAsStringForModel("quote") + "/random"), Quote.class);
    }
}