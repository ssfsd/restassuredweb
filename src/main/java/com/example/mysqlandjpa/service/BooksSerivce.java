package com.example.mysqlandjpa.service;

import com.example.mysqlandjpa.entity.Books;
import com.example.mysqlandjpa.repository.BooksReposiroty;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Log
@Service
public class BooksSerivce {
    @Autowired
    BooksReposiroty booksReposiroty;

    public List<Books> getAllBooks(){
        List<Books> books=new ArrayList<Books>();
        booksReposiroty.findAll().forEach(books1 -> books.add(books1));
        return  books;
    }
    public Books getBooksById(int id)
    {
        return  booksReposiroty.findById(id).get();
    }

    public void saveOrUpdate(Books books)
    {
        booksReposiroty.save(books);
    }

    public void saveALl(List<Books> books)
    {
        booksReposiroty.saveAll(books);
    }
    public void saveAllBooks(ArrayList<Books> listBook)
    {
        ArrayList<Books> booksList=new ArrayList<>();
        listBook.forEach(books1 -> booksList.add(books1));
        Books books=null;
        if(booksList.size()==0){
            log.info("books size is zero");
        }
        else
        {
            for(int i=0;i<booksList.size();i++)
            {
                books.setBookid(booksList.get(i).getBookid());
                books.setPrice(booksList.get(i).getPrice());
                books.setBookname(booksList.get(i).getBookname());
                books.setAuthor(booksList.get(i).getAuthor());
                booksReposiroty.save(books);
            }
        }

    }
    public void deleteById(int id)
    {
        booksReposiroty.deleteById(id);
    }
    public void updateById(Books books,int id)
    {
       Books book1=booksReposiroty.findById(id).get();
       book1.setBookname(books.getBookname());
       book1.setAuthor(books.getAuthor());
       book1.setPrice(books.getPrice());
       book1.setBookname(books.getBookname());
    }

    public List<Books> findByBookIdAndName(int id,String name)
    {
        List<Books> books =new ArrayList<>();
        books=booksReposiroty.findByBookidAndBookname(id,name);
        return books;
    }
}
