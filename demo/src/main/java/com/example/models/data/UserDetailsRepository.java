package com.example.models.data;

import com.example.models.UserDetails;
import com.example.models.Watchlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Integer> {
}
