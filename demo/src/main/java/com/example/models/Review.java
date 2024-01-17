package com.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToMany
    private List<Movie> moviesWithReviews = new ArrayList<>();

    public Review() {
    }
    public Review(List<Movie> moviesWithReviews, User user, String content) {
        this.moviesWithReviews = moviesWithReviews;
        this.user = user;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMoviesWithReviews() {
        return moviesWithReviews;
    }

    public void setMoviesWithReviews(List<Movie> moviesWithReviews) {
        this.moviesWithReviews = moviesWithReviews;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}