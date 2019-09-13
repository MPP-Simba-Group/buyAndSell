package com.mpp.buyAndSell.core.post.entity;


import com.mpp.buyAndSell.core.post.photouploadcontroller.PhotoUploadController;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition="text")
    private String itemName;
    @Column(columnDefinition="text")
    private String itemDescription;
    private double price;
    private int likes;
    private String photosURL= PhotoUploadController.uploadDirectory;
    public Post(){

    }

    public Post(String itemName, String itemDescription, double price) {
        this.setItemName(itemName);
        this.setItemDescription(itemDescription);
        this.setPrice(price);
        this.setLikes(0);
    }

    public Post(Long id, String itemName, String itemDescription, double price) {
        this.setId(id);
        this.setItemName(itemName);
        this.setItemDescription(itemDescription);
        this.setPrice(price);
        this.setLikes(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPhotosURL() {
        return photosURL;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", price=" + price +
                ", Number of Likes=" + likes+
                ", Photo's URL=" +photosURL+
                '}';//  ", price=" + price +
    }
}
