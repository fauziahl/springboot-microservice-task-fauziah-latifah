package com.task.microservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BooksDTO {
    
    private Long bookId;

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Author is required")
    @NotBlank(message = "Author cannot be empty")
    private String author;

    @NotNull(message = "ISBN is required")
    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;

    @NotNull(message = "Published date is required")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date publishedDate;
}
