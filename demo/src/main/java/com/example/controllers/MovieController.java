package com.example.controllers;


import com.example.models.Movie;
import com.example.models.Watchlist;
import com.example.models.data.MovieRepository;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import org.json.JSONObject;


@CrossOrigin(origins = "localhost:3000")
@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    String prepareSearchTermForURI(String searchTerm) {
        return searchTerm.replace(" ","-");
    }

    @PostMapping("movie")
    ResponseEntity<Movie> newMovie(@RequestBody Movie newMovie){
        //this is used to save a movie down to the database; only save movies that are in watchlists
        if (movieRepository.findById(newMovie.getId()).isEmpty()){
            movieRepository.save(newMovie);
        }
        return ResponseEntity.ok(newMovie);
    }

    @GetMapping("movie/{id}")
    @ResponseBody
    public Movie displaySingleMovie(@PathVariable Integer id) throws IOException, InterruptedException {
        //example ID -- the Lighthouse -- 503919

        String uriPath = String.format("https://api.themoviedb.org/3/movie/%d?language=en-US", id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriPath))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTc0MjYzNjVhZWZjNzBiY2YyMTY2N2U5Mzc4MGQ3YSIsInN1YiI6IjY1NzEzOWIzN2ViNWYyMDEwYmRjYmI4YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FJ8Aycw5FZL_beGA7v4I3NIks1aEK2lTn1UYckwKks8")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Movie pathMovie = gson.fromJson(response.body(),Movie.class);

        return pathMovie;
    }

    @GetMapping("movie/search")
    @ResponseBody
    public Movie[] displayMovieSearchResults(@RequestParam String searchTerm) throws IOException, InterruptedException {
        //example search term -- "Interstellar"

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

        //Converts the response body JSON into a format that can be saved as a List of Movies
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        JSONObject json = new JSONObject(response.body());
        JSONArray movieArray = json.getJSONArray("results");
        Movie[] pathMovies = gson.fromJson(movieArray.toString(), Movie[].class); //weirdly convoluted but works

        return pathMovies;
        }

    }
