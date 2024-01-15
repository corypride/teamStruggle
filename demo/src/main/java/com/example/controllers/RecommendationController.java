package com.example.controllers;

import com.example.models.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@CrossOrigin(origins = "localhost:3000")
@RestController
public class RecommendationController {

    //returns a list of recommended movies based on the movie ID provided
    @GetMapping("recommendation/{id}")
    @ResponseBody
    public Movie[] displayMovieRecommendationResults(@PathVariable Integer id) throws IOException, InterruptedException {
        //example ID -- the Lighthouse -- 503919

        String uriPath = String.format("https://api.themoviedb.org/3/movie/%d/recommendations?language=en-US&page=1", id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriPath))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTc0MjYzNjVhZWZjNzBiY2YyMTY2N2U5Mzc4MGQ3YSIsInN1YiI6IjY1NzEzOWIzN2ViNWYyMDEwYmRjYmI4YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FJ8Aycw5FZL_beGA7v4I3NIks1aEK2lTn1UYckwKks8")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        //Converts the response body JSON into a format that can be saved as a List of Movies
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        JSONObject json = new JSONObject(response.body());
        JSONArray movieArray = json.getJSONArray("results");
        Movie[] pathMovies = gson.fromJson(movieArray.toString(), Movie[].class); //weirdly convoluted but works

        return pathMovies;
    }
}
