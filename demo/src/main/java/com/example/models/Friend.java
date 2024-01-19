package com.example.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "friends")
public class Friend extends AbstractEntity{

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    private Integer friendId;

    public Friend() {
    }

    public Friend(User user, Integer friendId) {
        this.createdDate = java.time.LocalDateTime.now();
        this.user = user;
        this.friendId = friendId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
