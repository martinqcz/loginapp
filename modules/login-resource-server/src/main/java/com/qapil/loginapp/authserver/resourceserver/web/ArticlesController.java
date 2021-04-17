package com.qapil.loginapp.authserver.resourceserver.web;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticlesController {

    @GetMapping("/articles")
    public String[] getArticles(Principal principal, Authentication authentication) {
        System.out.println("SecurityContextHolder: " + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("principal=" + principal);
        System.out.println("authentication=" + authentication);
        return new String[] { "Article 1", "Article 2", "Article 3" };
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Authentication authentication) {
        System.out.println("SecurityContextHolder: " + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("principal=" + principal);
        System.out.println("authentication=" + authentication);
        return "Hello World!";
    }
}
