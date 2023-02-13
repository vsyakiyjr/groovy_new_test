package com.example.test14.repository

import com.example.test14.model.Note
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByOrderByTimestampDesc();
}