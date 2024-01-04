package com.example.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class MovieController {

    String prepareSearchTermForURI(String searchTerm) {
        return searchTerm.replace(" ","-");
    }

    @GetMapping("movie/{id}")
    @ResponseBody
    public String displaySingleMovie(@PathVariable Integer id) throws IOException, InterruptedException {
        //example ID -- the Lighthouse -- 503919
        //TODO: Need to change the response type to be consumable by front end
        // --(convert to JSON? Save response.body() as a Movie type?
        // --https://www.baeldung.com/java-httpclient-map-json-response

        String uriPath = String.format("https://api.themoviedb.org/3/movie/%d?language=en-US", id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriPath))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTc0MjYzNjVhZWZjNzBiY2YyMTY2N2U5Mzc4MGQ3YSIsInN1YiI6IjY1NzEzOWIzN2ViNWYyMDEwYmRjYmI4YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FJ8Aycw5FZL_beGA7v4I3NIks1aEK2lTn1UYckwKks8")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        //TODO: convert the response body JSON into a format that can be saved as a Movie
        //Movie pathMovie = new Movie(response.body());
        return response.body();
    }

    @GetMapping("movie/search")
    @ResponseBody
    public String displayMovieSearchResults(@RequestParam String searchTerm) throws IOException, InterruptedException {
        //example search term -- "Interstellar"
        //TODO: Need to change the response type to be consumable by front end
        // --(convert to JSON? Save response.body() as a Movie type?
        // --https://www.baeldung.com/java-httpclient-map-json-response
        // Known problem: Need to clean search terms with spaces and special characters for the path URI
        // --(replacing with dashes seems to work)

        String cleanSearchTerm = prepareSearchTermForURI(searchTerm);
        String uriPath = String.format("https://api.themoviedb.org/3/search/movie?query=%s&include_adult=false&language=en-US&page=1", cleanSearchTerm);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriPath))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTc0MjYzNjVhZWZjNzBiY2YyMTY2N2U5Mzc4MGQ3YSIsInN1YiI6IjY1NzEzOWIzN2ViNWYyMDEwYmRjYmI4YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FJ8Aycw5FZL_beGA7v4I3NIks1aEK2lTn1UYckwKks8")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        //TODO: convert the response body JSON into a format that can be saved as a Movie
        //Movie pathMovie = new Movie(response.body());
        return response.body();
        }
    }
