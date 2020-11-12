package com.madskchristensen.springexerciseconsumingrest.controller;

import com.madskchristensen.springexerciseconsumingrest.model.catfact.CatFact;
import com.madskchristensen.springexerciseconsumingrest.model.quote.Quote;
import com.madskchristensen.springexerciseconsumingrest.service.RestConsumerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private final RestConsumerService restConsumerService;

    public HomeController(RestConsumerService restConsumerService) {
        this.restConsumerService = restConsumerService;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() {
        Quote quote = restConsumerService.fetchSingleQuote();
        CatFact catFact = restConsumerService.fetchRandomValidCatFact();

        return quote.getValue().getQuote() + "</br>" + catFact.getText();
    }
}
