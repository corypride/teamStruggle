package com.example.demo.controllers;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Watchlist;
import com.example.demo.models.data.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class WatchlistController {

    @Autowired
    WatchlistRepository watchlistRepository;

    @GetMapping("watchlist")
    Watchlist getWatchlist(Integer id){
        //TODO: make endpoint for single watchlist available based on an ID
        Optional<Watchlist> o = watchlistRepository.findById(id);
        if(o.isPresent()) {
            return o.get();
        } else {
            return null; //TODO: make this return an error if not found
        }
    }

    @PostMapping("watchlist")
    Watchlist saveNewWatchlist(@RequestBody Watchlist newWatchlist){
        return watchlistRepository.save(newWatchlist);

    }

    // build update watchlist REST API
    @PutMapping("watchlist/{id}")
    public ResponseEntity<Watchlist> updateWatchlist(@PathVariable Integer id, @RequestBody Watchlist watchlistDetails) {
        Watchlist updateWatchlist = watchlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + id));

        //update all fields with new data
        //TODO: May want to change this logic to only update what was changed?
        updateWatchlist.setListType(watchlistDetails.getListType());
        updateWatchlist.setName(watchlistDetails.getName());
        updateWatchlist.setMoviesInList(watchlistDetails.getMoviesInList());
        updateWatchlist.setUser(watchlistDetails.getUser());

        watchlistRepository.save(updateWatchlist);

        return ResponseEntity.ok(updateWatchlist);
    }

    @DeleteMapping("watchlist/{id}")
    public ResponseEntity<String> deleteWatchlist(@PathVariable Integer id){
        Watchlist watchlistToDelete = watchlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + id));

        //deletion of the "saved" list type is not allowed
        if( watchlistToDelete.getListType() == "saved"){
            return ResponseEntity.ok("This list cannot be deleted.");
        }
        watchlistRepository.deleteById(id);

        return ResponseEntity.ok("Watchlist deleted successfully!");
    }


}
