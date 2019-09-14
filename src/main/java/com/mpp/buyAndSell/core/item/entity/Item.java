package com.mpp.buyAndSell.core.item.entity;


import com.mpp.buyAndSell.core.item.photouploadcontroller.PhotoUploadController;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "LIKES")
    private int likes;

    @Column(name = "PHOTO_URL")
    private String photosURL= PhotoUploadController.uploadDirectory;

    @Column(name = "CREATED_TIME")
    private Timestamp createdTime;

    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;

    @Column(name = "CATEGORY")
    private ItemCategoryEnum category;

    @Column(name = "ACTIVE")
    private boolean active;

    public Item(){}

    public Item(String itemName, String itemDescription, double price) {
        this.setItemName(itemName);
        this.setItemDescription(itemDescription);
        this.setPrice(price);
        this.setLikes(0);
    }

    public Item(Long id, String itemName, String itemDescription, double price) {
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

    public void setPhotosURL(String photosURL) {
        this.photosURL = photosURL;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public ItemCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ItemCategoryEnum category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //--------------------------toString-----------------------------
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
