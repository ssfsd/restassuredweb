package com.example.mysqlandjpa.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mysqlandjpa.entity.Books;
import com.example.mysqlandjpa.service.BooksSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BooksController {
    @Autowired
    BooksSerivce booksSerivce;
    @GetMapping("books")
    private List<Books> getAllBooks()
    {
        return booksSerivce.getAllBooks();
    }
    @GetMapping("/book/{bookid}")
    private Books getBooksByid(@PathVariable("bookid") int bookId)
    {
        return booksSerivce.getBooksById(bookId);
    }
    @PostMapping("/save")
    private Books saveBooks(@RequestBody Books books)
    {
        booksSerivce.saveOrUpdate(books);
        return books;
    }
    @DeleteMapping("/delete/{bookid}")
    private void deleteById(@PathVariable("bookid") int id)
    {
        booksSerivce.deleteById(id);
    }
    @RequestMapping(value="/byparam",params={"id","name"},method = RequestMethod.GET)
    private List<Books> findByBookIdAndName(@RequestParam int id,@RequestParam String name)
    {
       List<Books> books=booksSerivce.findByBookIdAndName(id,name);
       return books;
    }
    @PostMapping("/saveall")
    private void saveAll(@RequestBody String books)
    {
        Books books1;
        List list= JSONObject.parseObject(books,List.class);
        for(int i=0;i<list.size();i++){
           books1= JSONObject.parseObject(JSONObject.toJSONString(list.get(i)),Books.class);
            booksSerivce.saveOrUpdate(books1);
        }
    }

}
