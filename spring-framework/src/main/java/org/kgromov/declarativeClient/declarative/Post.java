package org.kgromov.declarativeClient.declarative;

//https://jsonplaceholder.typicode.com/posts
public record Post(int id, int userId, String title, String body) {
}
