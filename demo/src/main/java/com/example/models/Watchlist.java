package com.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
public class Watchlist {

    @Id
    @GeneratedValue
    private Integer id;

    private String listType; //Saved, Want to Watch, Currently Watching, Watched, Custom?
    private String name;

    @ManyToMany
    private List<Movie> moviesInList = new ArrayList<>(); //TODO: test that this is populating correctly

    @ManyToOne
    @JsonBackReference // Add this annotation to break the loop
    @JoinColumn(name = "user_details_id", nullable = false)
    private UserDetails userDetails;

    public void addMovieToWatchlist(Movie movie) {
        moviesInList.add(movie);
    }

    public Watchlist(String listType, String name, List<Movie> moviesInList, UserDetails userDetails) {
        this.listType = listType;
        this.name = name;
        this.moviesInList = moviesInList;
        this.userDetails = userDetails;
    }

    public Watchlist() {}

    public Integer getId() {
        return id;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMoviesInList() {
        return moviesInList;
    }

    public void setMoviesInList(List<Movie> moviesInList) {
        this.moviesInList = moviesInList;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
