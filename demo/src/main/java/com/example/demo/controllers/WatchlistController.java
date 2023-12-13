package com.example.demo.controllers;

import com.example.demo.models.Watchlist;
import com.example.demo.models.data.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchlistController {

    @Autowired
    WatchlistRepository watchlistRepository;

    @GetMapping("watchlist")
    Watchlist getWatchlist(Integer id){
        //TODO: make endpoint for single watchlist available based on an ID
        return watchlistRepository.findById(id);
    }

    //TODO: write Post Mapping, Update Mapping, and Delete Mapping

}
