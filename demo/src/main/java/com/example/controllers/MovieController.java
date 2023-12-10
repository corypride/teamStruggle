package com.example.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping(value = "/movies")
public class MovieController {

    private String search_term = "placeholder name";
/*
    //-------------THIS IS HOW TO GET THE API
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=the%20lighthouse&include_adult=false&language=en-US&page=1"))
            .header("accept", "application/json")
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTc0MjYzNjVhZWZjNzBiY2YyMTY2N2U5Mzc4MGQ3YSIsInN1YiI6IjY1NzEzOWIzN2ViNWYyMDEwYmRjYmI4YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FJ8Aycw5FZL_beGA7v4I3NIks1aEK2lTn1UYckwKks8")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
System.out.println(response.body());
    //-------------
   */
    String prepareSearchTermForAPICall(String search_term) {

        String preparedSearchTerm = "";
        //TODO: Change spaces into %, deal with other special characters as needed

        return preparedSearchTerm;
    }

}
