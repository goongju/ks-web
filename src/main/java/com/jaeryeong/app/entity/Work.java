package com.jaeryeong.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Work {
    @Id
    @GeneratedValue
    private Long id;
    private String position;
    private String company;
    private String time;
    private String desc;

    public Work() {
    }
    public Work(String position, String company, String time, String desc) {
        this.company = company;
        this.desc = desc;
        this.position = position;
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getDesc() {
        return desc;
    }

    public String getPosition() {
        return position;
    }

    public String getTime() {
        return time;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
