package com.qapil.loginapp.clientapp.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class ArticlesController {

    @Autowired
    private WebClient webClient;

    @GetMapping("/articles")
    public String[] getArticles(@RegisteredOAuth2AuthorizedClient("loginapp-client-authorization-code") OAuth2AuthorizedClient authorizedClient, Principal principal, Authentication authentication) {
        System.out.println("principal=" + principal);
        System.out.println("authentication=" + authentication);
        return this.webClient
                .get()
                .uri("http://localhost:8090/articles")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }

    @GetMapping("/")
    public View hello() {
        return new RedirectView("/articles");
    }
}
