package com.example.test14.service

import com.example.test14.model.Note
import org.springframework.security.core.Authentication

interface NoteService {

    List<Note> findAllNotes()

    Note findNoteById(String id)

    List<Note> findAllNotesSorted()

    Note saveNote(Note note)

    Note saveNote(Note note, Authentication authentication)

    Note updateNote(Note note)

    void deleteNoteById(String id)
}