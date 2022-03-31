package com.example.mysqlandjpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Data
public class Lotto {
    @Id
    private int lottoId;
    private String winningNumbers=null;
    private String winners;
}
