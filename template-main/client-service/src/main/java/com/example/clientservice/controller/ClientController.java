package com.example.clientservice.controller;

import com.example.clientservice.feign.BookServiceClient;
import com.example.clientservice.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final BookServiceClient bookServiceClient;

    @GetMapping("/books")
    public List<Book> getAllBooksFromClient() {
        return bookServiceClient.getAllBooks();
    }
}
