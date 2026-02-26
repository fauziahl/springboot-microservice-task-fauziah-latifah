package com.task.microservice.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Setter
@Getter
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "published_date", nullable = false)
    private Date publishedDate;

    @Column(name ="created_date" , nullable = false)
    private Date createdDate = new Date();

    @Column(name = "create_by", nullable = false)
    private Integer createdBy=1;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "modified_by")
    private Integer modifiedBy;
}
