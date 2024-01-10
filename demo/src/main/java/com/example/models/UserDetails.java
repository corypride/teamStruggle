package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserDetails extends AbstractEntity{

    @OneToMany(mappedBy = "userDetails")
    private final List<Watchlist> watchlists = new ArrayList<>();

    public UserDetails() {
    }
}
