package com.jaeryeong.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SocialMedia {
    @Id
    @GeneratedValue
    private Long id;
    private String instagram;
    private String facebook;
    public SocialMedia(){

    }
    public SocialMedia(String facebook, String instagram){
        this.facebook=facebook;
        this.instagram=instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getInstagram() {
        return instagram;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}