/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.data.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articulos")
public class Articulo {
   
    @Id
    private String id;
    
    private LocalDateTime created_at;
    private String title;
    private String author;
    private String comment;
    private boolean status;

    public Articulo() {
    }

    public Articulo(String id, LocalDateTime created_at, String title, String author, String comment) {
        this.id = id;
        this.created_at = created_at;
        this.title = title;
        this.author = author;
        this.comment = comment;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the created_at
     */
    public LocalDateTime getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    
    public void setCreated_at(String created_at) {
        this.created_at = LocalDateTime.parse(created_at,DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }    

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
