package org.kgromov.declarativeClient.imperative;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface TodoClient {

    @GetExchange("https://jsonplaceholder.typicode.com/todos/{id}")
    Todo getTodo(@PathVariable int id);
}
