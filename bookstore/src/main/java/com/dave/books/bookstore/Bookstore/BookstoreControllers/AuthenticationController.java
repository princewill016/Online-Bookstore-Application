package com.dave.books.bookstore.Bookstore.BookstoreControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v2/authenticate")
public class AuthenticationController {

@PostMapping("/authenticate")
public String postMethodName(@RequestBody String entity) {
    //TODO: process POST request
    
    return entity;
}

}
