package org.kgromov.declarativeClient.imperative;

import org.springframework.web.service.annotation.GetExchange;

public interface TodoClient {
    @GetExchange("https://jsonplaceholder.typicode.com/todos/{id}")
    Todo getTodo(int id);
}
