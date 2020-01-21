package com.application.beans;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String text;
    private Integer cost;
    private String filePath;

    public Message() {
    }

    public Message(User author, String text, Integer cost, String filePath) {
        this.author = author;
        this.text = text;
        this.cost = cost;
        this.filePath = filePath;
    }

    public Message(User author, String text, Integer cost) {
        this.author = author;
        this.text = text;
        this.cost = cost;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getUserId() {
        return author.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
