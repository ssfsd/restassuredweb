package com.example.mysqlandjpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Table
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ServiceEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//id生成策略自动增长
    //配置uuid，本来jpa是不支持uuid的，但借用hibernate的方法可以实现。
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    String serviceId;
    @Column(nullable = false,unique = false)
    String type;
    @Column(nullable = false,unique = true)
    String url;
    String body;
    String respBody;
    String result;
    @CreatedDate
    Date createDate;
    @LastModifiedDate
    Date lastUpdateDate;
}
