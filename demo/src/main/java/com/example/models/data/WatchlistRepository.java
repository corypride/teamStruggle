package com.example.models.data;

import com.example.models.Watchlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer> {

    List<Watchlist> findByUserDetailsId(Integer userDetailsId);

}
