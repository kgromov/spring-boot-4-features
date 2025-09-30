package org.kgromov.declarativeClient.declarative;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface PostClient {

    @GetExchange("https://jsonplaceholder.typicode.com/posts/{id}")
    Post getPost(int id);

    @GetExchange("https://jsonplaceholder.typicode.com/posts")
    List<Post> getAllPosts();
}
