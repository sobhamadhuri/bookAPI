package com.example.goodreads;

import java.util.*;
import com.example.goodreads.BookRepository;
import com.example.goodreads.Book;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public class BookService implements BookRepository{
    private HashMap<Integer, Book> hmap = new HashMap<>();

    public BookService(){
        Book b1 = new Book(1, "Harry-potter", "harry_potter.jpg");
        Book b2 = new Book(2, "Lion-The King", "king.jpeg");
        hmap.put(b1.getId(), b1);
        hmap.put(b2.getId(), b2);
    }
    int uniqueId =3;

    @Override
    public ArrayList<Book> getBooks(){
        Collection<Book> bookCollection = hmap.values();
        ArrayList<Book> books = new ArrayList<>(bookCollection);
        return books;
    }

    @Override
    public Book getBook(int bookId){
        Book existingBook = hmap.get(bookId);
        if (existingBook==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return existingBook;
    }

    @Override
    public Book addBook(Book book){
        book.setId(uniqueId);
        hmap.put(uniqueId, book);
        uniqueId+=1;
        return book;

    }

    @Override
    public Book updateBook(int bookId, Book book){
        Book existingBook = hmap.get(bookId);
        if (existingBook==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (book.getName() !=null){
            existingBook.setName(book.getName());
        }
        if (book.getImageUrl()!=null){
            existingBook.setImageUrl(book.getImageUrl());
        }
        return existingBook;
    }

    @Override
    public void deleteBook(int bookId){
        Book existingBook = hmap.get(bookId);
        if (existingBook==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            hmap.remove(bookId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}