package com.example.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_user_id", referencedColumnName = "id")
    private User firstUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    private User secondUser;

    public Friend() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }
}
