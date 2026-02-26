package com.task.microservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.microservice.model.Books;


public interface BooksRepo extends JpaRepository<Books, Long>{

}
