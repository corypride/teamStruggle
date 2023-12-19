package com.example.demo.models.data;

import com.example.demo.models.Watchlist;
import org.springframework.data.repository.CrudRepository;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer> {
}
