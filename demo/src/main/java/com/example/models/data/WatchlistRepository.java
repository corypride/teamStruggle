package com.example.models.data;

import com.example.models.Watchlist;
import org.springframework.data.repository.CrudRepository;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer> {
}
