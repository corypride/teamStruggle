package com.example.data;

import com.example.models.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends CrudRepository<Friend,Integer> {
    List<Friend> findByFriendId(Integer friendId);

}
