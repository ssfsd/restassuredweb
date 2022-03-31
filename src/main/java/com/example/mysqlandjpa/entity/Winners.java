package com.example.mysqlandjpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Winners {
    @Id
    int winnerId;
    int[] numbers;
}
