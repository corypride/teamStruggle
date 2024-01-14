package com.example.data;

import com.example.models.Review;
import com.example.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Integer> {

    List<Review> findPostByUserOrderById(User user);

    List<Review> findAllByOrderByIdDesc();
}