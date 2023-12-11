package main.java.com.example.data;

//finding a user by username and saving a user

import main.java.com.example.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
