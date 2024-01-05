package com.example.models;

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

    @OneToOne
    @NotNull
    @Valid
    private User user; //TODO: test that this relationship is set up correctly

    public Watchlist(String listType, String name, List<Movie> moviesInList, User user) {
        this.listType = listType;
        this.name = name;
        this.moviesInList = moviesInList;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
