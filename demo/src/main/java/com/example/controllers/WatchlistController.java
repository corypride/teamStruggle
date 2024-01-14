package com.example.controllers;

import com.example.exceptions.ResourceNotFoundException;
import com.example.models.Movie;
import com.example.models.Watchlist;
import com.example.models.data.MovieRepository;
import com.example.models.data.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class WatchlistController {

    @Autowired
    WatchlistRepository watchlistRepository;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("watchlist/{id}")
    @ResponseBody
    public Watchlist getWatchlist(@PathVariable Integer id) {
        Watchlist watchlistToReturn = watchlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + id));
        return watchlistToReturn;
        }


    //TODO: make a getAllUserWatchlists method

    @PostMapping("watchlist")
    public ResponseEntity<Watchlist> saveNewWatchlist(@RequestBody Watchlist newWatchlist){
        watchlistRepository.save(newWatchlist);
        return ResponseEntity.ok(newWatchlist);
    }

    // this updates all the data in a watchlist based on data passed in through Put request
    @PutMapping("watchlist/{id}")
    public ResponseEntity<Watchlist> updateWatchlist(@PathVariable Integer id, @RequestBody Watchlist watchlistDetails) {
        Watchlist updateWatchlist = watchlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + id));

        //update all fields with new data
        //TODO: May want to change this logic to only update what was changed?
        updateWatchlist.setListType(watchlistDetails.getListType());
        updateWatchlist.setName(watchlistDetails.getName());
        updateWatchlist.setMoviesInList(watchlistDetails.getMoviesInList());
        updateWatchlist.setUserDetails(watchlistDetails.getUserDetails());

        watchlistRepository.save(updateWatchlist);

        return ResponseEntity.ok(watchlistDetails);
    }

    //this adds a single movie to a watchlist with the Watchlistid in the path first and then the movie ID TODO: test it
    @PutMapping("watchlist/{watchlistId}/{movieId}")
    public ResponseEntity<String> addNewMovieToWatchlist(@PathVariable Integer watchlistId, @PathVariable Integer movieId) {
        Watchlist updateWatchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + watchlistId));

        Movie movieToAdd = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("No movie with given id: " + movieId));

        updateWatchlist.addMovieToWatchlist(movieToAdd);
        watchlistRepository.save(updateWatchlist);

        return ResponseEntity.ok("Watchlist updated successfully!");
    }

    @DeleteMapping("watchlist/{id}")
    public ResponseEntity<String> deleteWatchlist(@PathVariable Integer id){
        Watchlist watchlistToDelete = watchlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + id));

        //deletion of the "saved" list type is not allowed
        if(Objects.equals(watchlistToDelete.getListType(), "saved")){
            return ResponseEntity.ok("This list cannot be deleted.");

        }
        watchlistRepository.deleteById(id);

        return ResponseEntity.ok("Watchlist deleted successfully!");
    }


}
