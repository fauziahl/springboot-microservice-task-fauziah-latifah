package com.task.microservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.microservice.dto.BooksDTO;
import com.task.microservice.service.BooksService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService){
        this.booksService = booksService;
    }
    
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createBook(@Valid @RequestBody BooksDTO booksDTO){
        try {
            Map<String, Object> response = booksService.createBook(booksDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed to create book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateFullBook(@PathVariable("id") Long bookId, @Valid @RequestBody BooksDTO booksDTO){
        try {
            Map<String, Object> response = booksService.updateFullBook(bookId, booksDTO);

            HttpStatus status;
            if ("success".equals(response.get("status"))) {
                status = HttpStatus.OK;
            } else if ("BE_NOT_FOUND".equals(response.get("code"))) {
                status = HttpStatus.NOT_FOUND;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }

            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed to update book: " + e.getMessage());
            errorResponse.put("data", booksDTO);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePartialBook(@PathVariable("id") Long bookId, @RequestBody BooksDTO booksDTO){
        try {
            Map<String, Object> response = booksService.updatePartialBook(bookId, booksDTO);

            HttpStatus status;
            if ("success".equals(response.get("status"))) {
                status = HttpStatus.OK;
            } else if ("BE_NOT_FOUND".equals(response.get("code"))) {
                status = HttpStatus.NOT_FOUND;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }

            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed to update book: " + e.getMessage());
            errorResponse.put("data", booksDTO);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBookById(@PathVariable("id") Long bookId){
        try {
            Map<String, Object> response = booksService.deleteBookById(bookId);

            HttpStatus status;
            if ("success".equals(response.get("status"))) {
                status = HttpStatus.OK;
            } else if ("BE_NOT_FOUND".equals(response.get("code"))) {
                status = HttpStatus.NOT_FOUND;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed delete book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllBooks(){
        try {
            Map<String, Object> response = booksService.getAllBooks();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed fetch all books: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable("id") Long bookId){
        try {
            Map<String, Object> response = booksService.getBookById(bookId);

            HttpStatus status;
            if ("success".equals(response.get("status"))) {
                status = HttpStatus.OK;
            } else if ("BE_NOT_FOUND".equals(response.get("code"))) {
                status = HttpStatus.NOT_FOUND;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed fetch book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}