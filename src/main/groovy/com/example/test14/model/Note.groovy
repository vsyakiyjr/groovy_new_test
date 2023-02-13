package com.example.test14.model

import com.example.test14.dto.AuthorDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDateTime

@Document
class Note {

    @Id
    private String id
    private String name
    private String text
    private LocalDateTime timestamp
    private AuthorDto author
    private Set<AuthorDto> liked = new HashSet<>()

    Note() {
    }

    Note(String id, String name, String text, LocalDateTime timestamp, User author) {
        this.id = id
        this.name = name
        this.text = text
        this.timestamp = timestamp
        this.author = author
    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getText() {
        return text
    }

    void setText(String text) {
        this.text = text
    }

    LocalDateTime getTimestamp() {
        return timestamp
    }

    void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp
    }

    AuthorDto getAuthor() {
        return author
    }

    void setAuthor(AuthorDto author) {
        this.author = author
    }

    Set<AuthorDto> getLiked() {
        return liked
    }

    void setLiked(Set<AuthorDto> liked) {
        this.liked = liked
    }
}