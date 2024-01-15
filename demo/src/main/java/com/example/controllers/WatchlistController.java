package com.example.controllers;

import com.example.data.UserRepository;
import com.example.exceptions.ResourceNotFoundException;
import com.example.models.Movie;
import com.example.models.User;
import com.example.models.Watchlist;
import com.example.models.data.MovieRepository;
import com.example.models.data.WatchlistRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "localhost:3000")
@RestController
public class WatchlistController {

    @Autowired
    WatchlistRepository watchlistRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("watchlist/{id}")
    @ResponseBody
    public Watchlist getWatchlist(@PathVariable Integer id) {
        Watchlist watchlistToReturn = watchlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + id));
        return watchlistToReturn;
        }

    @GetMapping("watchlist/movies/{watchlistId}")
    @ResponseBody
    public List<Movie> getMoviesInWatchlist(@PathVariable Integer watchlistId) {
        Watchlist moviesToReturn = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + watchlistId));

        return moviesToReturn.getMoviesInList();
    }


    //Returns all watchlists associated with a userDetailsId
    @GetMapping("watchlists/{userId}")
    @ResponseBody
    public ResponseEntity<List<Watchlist>> getAllUserWatchlists(@PathVariable Integer userId) {
        List<Watchlist> watchlists = watchlistRepository.findByUserId(userId);

        if (watchlists.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(watchlists);
        }
    }

    @PostMapping("watchlist/{userId}/{watchlistName}")
    public ResponseEntity<Watchlist> createNewWatchlist(@PathVariable Integer userId,
                                                        @PathVariable String watchlistName){
        //If user exists, associate new watchlist to existing user
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("cannot find user with given id: " + userId));

        Watchlist newWatchlist = new Watchlist("custom", watchlistName, new ArrayList<Movie>(), existingUser);
        watchlistRepository.save(newWatchlist);
        return ResponseEntity.ok(newWatchlist);
    }

    // this updates all the data in a watchlist based on data passed in through Put request
    @PutMapping("api/watchlist/{id}")
    public ResponseEntity<Watchlist> updateWatchlist(@PathVariable Integer id, @RequestBody Watchlist watchlistDetails) {
        try {
            Watchlist existingWatchlist = watchlistRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + id));

            // Update only the fields that are not null in watchlistDetails
            if (watchlistDetails.getListType() != null) {
                existingWatchlist.setListType(watchlistDetails.getListType());
            }
            if (watchlistDetails.getName() != null) {
                existingWatchlist.setName(watchlistDetails.getName());
            }
            if (watchlistDetails.getMoviesInList() != null) {
                existingWatchlist.setMoviesInList(watchlistDetails.getMoviesInList());
            }
            if (watchlistDetails.getUser() != null) {
                existingWatchlist.setUser(watchlistDetails.getUser());
            }

            watchlistRepository.save(existingWatchlist);

            return ResponseEntity.ok(existingWatchlist);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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

    //this removes a movie from a watchlist
    @DeleteMapping("watchlist/{watchlistId}/{movieId}")
    public ResponseEntity<String> removeMovieToWatchlist(@PathVariable Integer watchlistId, @PathVariable Integer movieId) {
        Watchlist updateWatchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new ResourceNotFoundException("No watchlist with given id: " + watchlistId));

        Movie movieToRemove = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("No movie with given id: " + movieId));

        updateWatchlist.removeMovieFromWatchlist(movieToRemove);
        watchlistRepository.save(updateWatchlist);

        return ResponseEntity.ok("Movie removed from watchlist successfully!");
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
