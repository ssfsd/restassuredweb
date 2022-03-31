package com.example.mysqlandjpa.repository;

import com.example.mysqlandjpa.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksReposiroty extends JpaRepository<Books,Integer> {
    List<Books> findByBookidAndBookname(int id,String bookname);
}
