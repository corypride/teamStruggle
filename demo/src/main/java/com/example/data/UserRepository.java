package main.java.com.example.data;

//finding a user by username and saving a user
public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}