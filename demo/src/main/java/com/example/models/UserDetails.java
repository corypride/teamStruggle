package com.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserDetails extends AbstractEntity{

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private List<Watchlist> watchlists = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public UserDetails() {
    }

    public List<Watchlist> getWatchlists() {
        return watchlists;
    }
}
