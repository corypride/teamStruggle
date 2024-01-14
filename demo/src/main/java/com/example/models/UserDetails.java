package com.example.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserDetails extends AbstractEntity{

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    @JsonManagedReference // Add this annotation to break the loop
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
