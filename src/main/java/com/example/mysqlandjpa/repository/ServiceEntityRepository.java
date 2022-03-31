package com.example.mysqlandjpa.repository;

import com.example.mysqlandjpa.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity,String> {
    public String findByUrl(String url);
    public List<ServiceEntity> findByUrlOrBody(String url, String body);
}
