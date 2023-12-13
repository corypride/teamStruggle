package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Watchlist {

    @Id
    @GeneratedValue
    private Integer id;

    private String listType; //Saved, Want to Watch, Currently Watching, Watched, Custom?
    private String name;
    private List<Movie> moviesInList; //TODO: figure out how to populate this
    private Integer userID; //TODO: link this to a single user ID

    public Watchlist(String listType, String name, List<Movie> moviesInList, Integer userID) {
        this.listType = listType;
        this.name = name;
        this.moviesInList = moviesInList;
        this.userID = userID;
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
}
