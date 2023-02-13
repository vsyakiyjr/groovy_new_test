package com.example.test14.controller

import com.example.test14.model.Note
import com.example.test14.model.User
import com.example.test14.service.NoteService
import com.example.test14.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notes")
class NoteController {

    @Autowired
    private NoteService noteService
    @Autowired
    private UserService userService

    @GetMapping
    List<Note> findAllNotes() {
        return noteService.findAllNotes()
    }

    @GetMapping("/{noteId}")
    Note findNoteById(@PathVariable(name = "noteId") String noteId) {
        noteService.findNoteById(noteId)
    }

    @GetMapping("/sorted")
    List<Note> findAllNotesSortedByDate() {
        noteService.findAllNotesSorted()
    }

    @PostMapping
    Note saveNote(@RequestBody Note note, Authentication authentication) {
        noteService.saveNote(note, authentication)
    }

    @PutMapping("/update")
    Note updateNote(@RequestBody Note note) {
        noteService.updateNote(note)
    }

    @DeleteMapping("/{noteId}")
    void deleteNoteById(@PathVariable(name = "noteId") String noteId) {
        noteService.deleteNoteById(noteId)
    }
}
