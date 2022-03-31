package com.example.mysqlandjpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Books {
    @Id
    private int bookid;
    private String bookname;
    private String author;
    private int price;
}
