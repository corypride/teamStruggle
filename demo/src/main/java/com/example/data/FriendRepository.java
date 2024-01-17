package com.example.data;

import com.example.models.Friend;
import org.springframework.data.repository.CrudRepository;
import com.example.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FriendRepository extends CrudRepository<Friend,Integer> {

    /*boolean existsByFirstUserAndSecondUser(User first,User second);*/

    List<Friend> findByFirstUser(User user);
   /* List<Friend> findBySecondUser(User user);*/

  /* List<Friend> findAllByFirstUser(User first);
    List<Friend> findAllBySecondUser(User second);
    Friend findByFirstUserAndSecondUser(User first, User second);*/

}
