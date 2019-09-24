package com.mpp.buyAndSell.core.comment.entity;

import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.user.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "COMMENT")
public class Comment {

    public Comment(User user, Item item, String description) {
        this.user = user;
        this.item = item;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Column(name = "DESCRIPTION")
    private String description;

    public Comment(User user) {
        this.user = user;
    }

    //-------------------------------------setters and getters-------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
