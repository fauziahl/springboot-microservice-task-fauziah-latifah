package com.task.microservice.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.microservice.dto.BooksDTO;
import com.task.microservice.handler.ResponseHandler;
import com.task.microservice.model.Books;
import com.task.microservice.repo.BooksRepo;

@Service
@Transactional
public class BooksService {

    private BooksRepo booksRepo;

    public BooksService(BooksRepo booksRepo){
        this.booksRepo = booksRepo;
    }

    //CREATE
    public Map<String, Object> createBook(BooksDTO requestBooksDTO){
        try {
            Books books = new Books();
            books.setTitle(requestBooksDTO.getTitle());
            books.setAuthor(requestBooksDTO.getAuthor());
            books.setIsbn(requestBooksDTO.getIsbn());
            books.setPublishedDate(requestBooksDTO.getPublishedDate());
            books.setCreatedBy(1);
            books.setCreatedDate(new Date());
            booksRepo.save(books);

            return new ResponseHandler().generateModelAttribut("SUCCESS CREATE DATA",
            HttpStatus.CREATED,
            requestBooksDTO,
            "BE");

        } catch (Exception e) {
            return new ResponseHandler().generateModelAttribut(
                    "Failed to save book: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    requestBooksDTO,
                    "BE"
            );
        }
    }

    //UPDATE ALL
    public Map<String, Object> updateFullBook(Long bookId, BooksDTO requestBooksDTO) {
        try {
            Optional<Books> optionalBook = booksRepo.findById(bookId);
            if (optionalBook.isEmpty()) {
                return new ResponseHandler().generateModelAttribut(
                        "Failed to save book: " + bookId,
                        HttpStatus.NO_CONTENT,
                        null,
                        "BE"
                );
            }

            Books books = optionalBook.get();
            books.setTitle(requestBooksDTO.getTitle());
            books.setAuthor(requestBooksDTO.getAuthor());
            books.setIsbn(requestBooksDTO.getIsbn());
            books.setPublishedDate(requestBooksDTO.getPublishedDate());
            books.setModifiedBy(1);
            books.setModifiedDate(new Date());

            booksRepo.save(books);

            return new ResponseHandler().generateModelAttribut("SUCCESS UPDATE DATA",
            HttpStatus.CREATED,
            books,
            "BE");

        } catch (Exception e) {
            return new ResponseHandler().generateModelAttribut(
                "Failed to update book: " + e.getMessage(),
                HttpStatus.BAD_REQUEST,
                requestBooksDTO,
                "BE"
            );
        }
    }

    //UPDATE PARTIAL
    public Map<String, Object> updatePartialBook(Long bookId, BooksDTO requestBooksDTO) {
        try {
            Optional<Books> optionalBook = booksRepo.findById(bookId);
            if (optionalBook.isEmpty()) {
                return new ResponseHandler().generateModelAttribut(
                        "Failed to save book: " + bookId,
                        HttpStatus.NO_CONTENT,
                        null,
                        "BE"
                );
            }

            Books books = optionalBook.get();
            if (requestBooksDTO.getTitle() != null) {
                books.setTitle(requestBooksDTO.getTitle());
            }
            if (requestBooksDTO.getAuthor() != null) {
                books.setAuthor(requestBooksDTO.getAuthor());
            }
            if (requestBooksDTO.getIsbn() != null) {
                books.setIsbn(requestBooksDTO.getIsbn());
            }
            if (requestBooksDTO.getPublishedDate() != null) {
                books.setPublishedDate(requestBooksDTO.getPublishedDate());
            }

            books.setCreatedBy(1);
            books.setCreatedDate(new Date());
            booksRepo.save(books);

            return new ResponseHandler().generateModelAttribut("SUCCESS UPDATE DATA",
            HttpStatus.CREATED,
            books,
            "BE");

        } catch (Exception e) {
            return new ResponseHandler().generateModelAttribut(
                "Failed to update book: " + e.getMessage(),
                HttpStatus.BAD_REQUEST,
                requestBooksDTO,
                "BE"
            );
        }
    }

    //DELETE
    public Map<String, Object> deleteBookById(Long bookId){
        try {
            Optional<Books> books = booksRepo.findById(bookId);
            if (books.isEmpty()) {
                return new ResponseHandler().generateModelAttribut(
                        "Book not found with id : " + bookId,
                        HttpStatus.NO_CONTENT,
                        null,
                        "BE"
                );
            }

            booksRepo.deleteById(bookId);

            return new ResponseHandler().generateModelAttribut("SUCCESS DELETE DATA",
            HttpStatus.OK,
            books,
            "BE");

        } catch (Exception e) {
            return new ResponseHandler().generateModelAttribut(
                    "Failed delete book: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "BE"
            );
        }
    }
    
    //FIND ALL
    public Map<String, Object> getAllBooks(){
        try {
            List<Books> booksList = booksRepo.findAll();

            return new ResponseHandler().generateModelAttribut("SUCCESS FETCH ALL DATA",
            HttpStatus.OK,
            booksList,
            "BE");

        } catch (Exception e) {
            return new ResponseHandler().generateModelAttribut(
                    "Failed to fetch books: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "BE"
            );
        }
    }

    //FIND BY ID
    public Map<String, Object> getBookById(Long bookId){
        try {
            Optional<Books> books = booksRepo.findById(bookId);
            if (books.isEmpty()) {
                return new ResponseHandler().generateModelAttribut(
                        "Book not found with id : " + bookId,
                        HttpStatus.NO_CONTENT,
                        null,
                        "BE"
                );
            }

            return new ResponseHandler().generateModelAttribut("SUCCESS FETCH DATA",
            HttpStatus.OK,
            books.get(),
            "BE");

        } catch (Exception e) {
            return new ResponseHandler().generateModelAttribut(
                    "Failed to fetch book: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "BE"
            );
        }
    }
}
