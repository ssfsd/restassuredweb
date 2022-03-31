package com.example.mysqlandjpa.repository;

import com.example.mysqlandjpa.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoReposiroty extends JpaRepository<Lotto,Integer> {
}
